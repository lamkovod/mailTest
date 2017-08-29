package pages;

import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.graphene.page.Location;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.jboss.arquillian.graphene.Graphene.waitGui;

@Location("https://go.mail.ru/")
public class SearchResultPage {
    @Drone
    private WebDriver driver;

    @FindBy(id = "q") // SearchField id...
    private WebElement searchField;

    @FindBy(xpath = "//li[@class='result__li']")
    private List<WebElement> resultList;

    public void verifyPageElements(String search){
        waitGui().withTimeout(5, TimeUnit.SECONDS).until().element(searchField).is().visible();
        Assert.assertTrue(searchField.getAttribute("value").equals(search));
    }

    public void chooseResultNumber(int number){                 //Open link number "number" from all results
        resultList.get(number).findElement(By.cssSelector("h3 > a")).click();
    }
}
