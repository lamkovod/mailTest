package pages;

import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.graphene.page.Location;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Location("http://argustelecom.ru/")
public class ArgusTelecomPage {
    @Drone
    private WebDriver driver;

    @FindBy(className = "footerBottomCopyrights")
    private WebElement companyName;

    public void verifyPageElements(String company){
        Assert.assertTrue(companyName.getText().contains(company));
    }
}
