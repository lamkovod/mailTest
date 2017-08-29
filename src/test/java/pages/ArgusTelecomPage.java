package pages;

import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.graphene.page.Location;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static org.jboss.arquillian.graphene.Graphene.waitGui;

@Location("http://argustelecom.ru")
public class ArgusTelecomPage {
    @Drone
    private WebDriver driver;
    @FindBy(className = "footerBottomCopyrights")
    private WebElement companyName;

    @FindBy(css = ".headerTop") // Header element of page
    private WebElement headerName;

    private void closeOldTabAndOpenNew(){
        ArrayList<String> tabs = new ArrayList<> (driver.getWindowHandles());
        driver.switchTo().window(tabs.get(0));
        driver.close();
        driver.switchTo().window(tabs.get(1));
    }

    public void verifyPageElements(String company){
        closeOldTabAndOpenNew();
        waitGui().withTimeout(10, TimeUnit.SECONDS).until().element(headerName).is().visible();  //
        Assert.assertTrue(companyName.getText().contains(company));
    }
}
