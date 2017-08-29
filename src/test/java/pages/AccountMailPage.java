package pages;

import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.graphene.page.Location;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

import static org.jboss.arquillian.graphene.Graphene.waitGui;

@Location("https://account.mail.ru/")
public class AccountMailPage {
    @Drone
    private WebDriver driver;

    @FindBy(name = "Username")
    private WebElement usernameField;

    @FindBy(name = "Password")
    private WebElement passwordField;

    @FindBy(className = "btn__text")
    private WebElement enterButton; // Using XPath to get an element. No unique attributes.

    @FindBy(className = "b-login__errors")
    private WebElement errorMessage;

    public void enterUsernameField(String username){
        usernameField.clear();
        usernameField.sendKeys(username);
    }
    public void enterPasswordField(String password){
        passwordField.sendKeys(password);
    }
    public void clickEnter(){
        enterButton.click();
    }
    public void getAndCompareMistake(String mistake){
        Assert.assertEquals(errorMessage.getText(), mistake);
    }
    public void verifyPageElements(){
        waitGui().withTimeout(5, TimeUnit.SECONDS).until().element(usernameField).is().visible();
    }

}
