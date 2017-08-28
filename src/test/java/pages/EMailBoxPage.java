package pages;

import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.graphene.page.Location;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@Location("https://e.mail.ru/")
public class EMailBoxPage {
    @Drone
    private WebDriver driver;

    @FindBy(id = "b-letters")
    private WebElement userLetters;

    @FindBy(id = "PH_user-email")
    private WebElement usernameBox;

    @FindBy(id = "PH_logoutLink")
    private WebElement logoutButton;

    public void clickExit(){
        logoutButton.click();
    }
    public void verifyPageElements(String username){
        Assert.assertEquals(usernameBox.getText(), username);
    }
}
