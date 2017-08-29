package tests;

import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.graphene.page.InitialPage;
import org.jboss.arquillian.graphene.page.Page;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.junit.Test;
import org.junit.runner.RunWith;
import pages.ArgusTelecomPage;
import pages.MainPage;
import pages.SearchResultPage;

@RunWith(Arquillian.class)
@RunAsClient
public class SearchModuleTestSet {

    private static String searchTextPart1 = "АРГУС";
    private static String searchTextPart2 = " НТЦ";
    private int resultLinkNumber = 0;
    @Page
    MainPage MailRuPage;

    @Page
    SearchResultPage SearchResultPage;

    @Page
    ArgusTelecomPage ArgusPage;

    @Test
    @InSequence(1)
    public void EmptySearchFieldTest(@InitialPage MainPage MailRuPage) throws Exception{
        MailRuPage.verifyPageElements();
        MailRuPage.clickOnSearchButton();
        MailRuPage.searchFieldIsActive();
    }

    @Test
    @InSequence(2)
    public void SearchForArgusNTC() throws Exception{
        MailRuPage.enterTextInSearchField(searchTextPart1);
        MailRuPage.checkSuggestListForChars(searchTextPart1);
        MailRuPage.enterTextInSearchField(searchTextPart2);
        MailRuPage.clickOnSearchButton();
    }

    @Test
    @InSequence(3)
    public void ResultsOfArgusNTCSearch() throws Exception{
        SearchResultPage.verifyPageElements(searchTextPart1 + searchTextPart2);
        SearchResultPage.chooseResultNumber(resultLinkNumber);
    }

    @Test
    @InSequence(4)
    public void ArgusTelecomSiteCheck() throws Exception{
        ArgusPage.verifyPageElements(searchTextPart1);
    }
}
