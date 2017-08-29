package tests;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.graphene.page.InitialPage;
import org.jboss.arquillian.graphene.page.Page;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import pages.*;

@RunWith(Arquillian.class)
@RunAsClient
public class LoginTestSet {

    private String username = "midgardel";
    private String password = "simsim29111993";
    private String wrongUsernameOrPasswordMistake = "Неверное имя пользователя или пароль. Проверьте правильность введенных данных.";

    @Page
    MainPage MailRuPage;
    @Page
    AccountMailPage AccountMailPage;

    @Page
    EMailBoxPage MailBoxPage;

    @Test
    @InSequence(1)
    public void EmptyFields(@InitialPage MainPage MailRuPage) throws Exception{
        MailRuPage.openAuthorizationForm();
        MailRuPage.clickEnter();
        MailRuPage.checkErrorVisible();
    }

    @Test
    @InSequence(2)
    public void WrongPassword() throws Exception{
        MailRuPage.enterUsernameField(username);
        MailRuPage.enterPasswordField("123");
        MailRuPage.clickEnter();
    }

    @Test
    @InSequence(3)
    public void CorrectUsernamePassword() throws Exception{
        AccountMailPage.verifyPageElements();
        AccountMailPage.getAndCompareMistake(wrongUsernameOrPasswordMistake);
        AccountMailPage.enterUsernameField(username);
        AccountMailPage.enterPasswordField(password);
        AccountMailPage.clickEnter();
        MailBoxPage.verifyPageElements(username);
    }

    @Test
    @InSequence(4)
    public void ExitToMainPage() throws Exception{
        MailBoxPage.clickExit();
    }
}
