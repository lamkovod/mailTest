package pages;

import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.graphene.page.Location;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@Location("https://go.mail.ru/")
public class SearchResultPage {
    @Drone
    private WebDriver driver;

    @FindBy(id = "q") // SearchField id...
    private WebElement searchField;

    @FindAll({@FindBy(className = "className")})
    private List<WebElement> resultList;

    public void verifyPageElements(String search){
        Assert.assertEquals(searchField.getText(), search);
    }

    public void chooseResultNumber(int number){                 //Open link number "number" from all results
        resultList.get(number).click();
    }
}
