package pages;

import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.graphene.page.Location;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.jboss.arquillian.graphene.Graphene.waitGui;


@Location("https://www.mail.ru")
public class MainPage {
    @Drone
    private WebDriver driver;

    @FindBy(id = "PH_authLink")
    private WebElement authFormButton;

    @FindBy(css = "#frame > form > div.b-form-row.b-form-row_login.b-form-row_in-row.b-form-row_submit_btn.b-form-row_hide-message.b-form-row_.b-form-row_popup > div > div.b-form-field__widget > div.b-form-field__input > div > button:nth-child(1)")
    private WebElement enterButton; // Using xPath to get an element. No unique attributes.

    @FindBy(name = "Username")
    private WebElement usernameField;

    @FindBy(name = "Password")
    private WebElement passwordField;

    @FindBy(id = "q") // SearchField id...
    private WebElement searchField;

    @FindBy(id = "search__button")
    private WebElement searchButton;

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
    public void clickEnter(){
        enterButton.click();
    }
    public void clickOnSearchButton(){
        searchButton.click();
    }
    public void enterTextInSearchField(String search){
        searchField.sendKeys(search);
    }
}
