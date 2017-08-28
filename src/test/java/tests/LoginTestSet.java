package tests;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.graphene.page.InitialPage;
import org.jboss.arquillian.graphene.page.Page;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import pages.*;

@RunWith(Arquillian.class)
@RunAsClient
public class LoginTestSet {

    String username = "midgardel";
    String password = "simsim29111993";


    @Page
    AccountMailPage AccountMailPage;

    @Page
    EMailBoxPage MailBoxPage;

    @Page
    SearchResultPage SearchResultPage;

    @Page
    ArgusTelecomPage ArgusPage;

    @Test
    public void EmptyFields(@InitialPage MainPage MailRuPage) throws Exception{
        MailRuPage.openAuthorizationForm();
        MailRuPage.clickEnter();
    }

    @Test
    public void WrongPassword(@InitialPage MainPage MailRuPage) throws Exception{
        MailRuPage.enterUsernameField(username);
        MailRuPage.enterPasswordField("123");
        MailRuPage.clickEnter();
    }

    @Test
    public void CorrectUsernamePassword() throws Exception{
        AccountMailPage.enterUsernameField(username);
        AccountMailPage.enterPasswordField(password);
        AccountMailPage.clickEnter();
        MailBoxPage.verifyPageElements(username);
    }
}
