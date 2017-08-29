package pages;

import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.graphene.page.Location;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.jboss.arquillian.graphene.Graphene.waitGui;


@Location("https://www.mail.ru")
public class MainPage {
    @Drone
    private WebDriver driver;

    @FindBy(id = "PH_authLink")
    private WebElement authFormButton;

    @FindBy(css = "#frame > form " +
            "> div.b-form-row.b-form-row_login.b-form-row_in-row.b-form-row_submit_btn.b-form-row_hide-message.b-form-row_.b-form-row_popup " +
            "> div > div.b-form-field__widget > div.b-form-field__input > div > button:nth-child(1)")
    private WebElement enterButton; // Using css to get an element. No unique attributes.

    @FindBy(name = "Username")
    private WebElement usernameField;

    @FindBy(name = "Password")
    private WebElement passwordField;

    @FindBy(id = "q") // SearchField id...
    private WebElement searchField;

    @FindBy(id = "search__button")
    private WebElement searchButton;

    @FindBy(className = "go-suggests__item")
    private List<WebElement> suggestList;

    @FindBy(css = ".b-form-field__errors__error.js-required.js-error.b-form-field__errors__error_visible")    // Using CSS. This classname appear if empty field error called.
    private WebElement emptyFieldMessage;                                                                     // Local solution only for this test.

    public void enterUsernameField(String username){
        usernameField.sendKeys(username);
    }
    public void enterPasswordField(String password){
        passwordField.sendKeys(password);
    }
    public void openAuthorizationForm(){
        authFormButton.click();
        WebElement authForm = driver.findElement(By.cssSelector(".ag-popup__frame__layout__iframe"));
        driver.switchTo().frame(authForm);
        waitGui().withTimeout(5, TimeUnit.SECONDS).until().element(enterButton).is().visible();
    }
    public void checkErrorVisible(){
        Assert.assertTrue(emptyFieldMessage.isDisplayed());
    }

    public void clickEnter(){
        enterButton.click();
    }

    public void clickOnSearchButton(){
        searchButton.click();
    }

    public void enterTextInSearchField(String search){
        searchField.sendKeys(search);
    }

    public void verifyPageElements() throws Exception{
        waitGui().withTimeout(5, TimeUnit.SECONDS).until().element(searchField).is().visible();
        Assert.assertTrue(searchButton.isDisplayed());
    }

    public void searchFieldIsActive(){
        Assert.assertTrue(searchField.isEnabled());
    }

    public void checkSuggestListForChars(String searchText){
        waitGui().withTimeout(5, TimeUnit.SECONDS).until().element(By.cssSelector(".go-suggests__items")).is().visible();
        if(!suggestList.isEmpty()){
            for (WebElement suggestItem : suggestList){
                Assert.assertTrue(suggestItem.getText().contains(searchText.toLowerCase()));
            }
        }
    }
}
