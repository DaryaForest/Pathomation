package com.pathomation.frontendui;

import com.pathomation.actions.Actions;
import com.pathomation.base.BaseActions;
import com.pathomation.base.BaseTest;
import com.pathomation.pages.Pages;
import com.pathomation.pages.reports.ReportsPages;
import com.pathomation.pages.user.UserPagesProvider;
import com.pathomation.util.ConstantsPma2;
import com.pathomation.util.data.XMLparser;
import com.pathomation.util.reporter.Reporter;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;

public class TC15_HandlingOfTooManyConcurrentUsers extends BaseTest {

    XMLparser xmlDoc = new XMLparser();

    @Test(testName = "TC 15: Handling of too many concurrent users")
    public void check() throws IOException, InterruptedException {
        Actions.mainActions().openMainPage();
        Actions.mainActions().clearSession();
        Actions.loginActions().login(ConstantsPma2.ADMIN_USER_NAME, ConstantsPma2.ADMIN_PASSWORD);
        Pages.homePage().waitHomePageLounching();
        Actions.mainActions().openPage(ConstantsPma2.USERS_TABLE);
        UserPagesProvider.usersPage().clickEditButtonByLogin(ConstantsPma2.USER1_NAME);
        UserPagesProvider.editUserPage().setCheckedSuspendedCheckBoxState(false);
        UserPagesProvider.editUserPage().clickSaveButton();
        UserPagesProvider.summaryPage().waitUsersSummaryPageLoaded(ConstantsPma2.USER1_NAME);
        //UserPagesProvider.usersPage().waitForUsersPageLoaded();
        Pages.navigationBarPage().clickLogoutButton();
        Pages.loginPage().waitForLoginForm();
        try {
            URL urlUser1 = new URL(String.format(ConstantsPma2.AUTHENTICATE_API_URL,
                    "user1", "user1"));
            Assert.assertTrue(xmlDoc.getXmlTextByTagNmae(urlUser1,
                    "Success").trim().equalsIgnoreCase("True"),
                    "Expected user1 successfully authorized");

            URL urlUser2 = new URL(String.format(ConstantsPma2.AUTHENTICATE_API_URL,
                    "user2", "user2"));
            Assert.assertTrue(xmlDoc.getXmlTextByTagNmae(urlUser2,
                    "Success").trim().equalsIgnoreCase("True"),
                    "Expected user2 successfully authorized");

            URL urlUser3 = new URL(String.format(ConstantsPma2.AUTHENTICATE_API_URL,
                    "user3", "user3"));
            Assert.assertTrue(xmlDoc.getXmlTextByTagNmae(urlUser3,
                    "Success").trim().equalsIgnoreCase("True"),
                    "Expected user3 successfully authorized");
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            Reporter.log("Invalid or empty xml file");
            e.printStackTrace();
        }
        Actions.loginActions().login(ConstantsPma2.ADMIN_USER_NAME, ConstantsPma2.ADMIN_PASSWORD);
        Pages.homePage().waitHomePageLounching();
        Assert.assertEquals(driver.getCurrentUrl(), System.getProperty("baseurl"));
        Pages.leftSideBarPage().clickReportsButton();
        Pages.leftSideBarPage().clickActiveSessionButton();
        ReportsPages.activeSessionsPage().waitForSessionManagementPage();
        ReportsPages.activeSessionsPage().clickTerminateButtonByUserName("user1");
        BaseActions.acceptAlert();

        ReportsPages.activeSessionsPage().clickTerminateButtonByUserName("user2");
        BaseActions.acceptAlert();

        ReportsPages.activeSessionsPage().clickTerminateButtonByUserName("user3");
        BaseActions.acceptAlert();

        Pages.navigationBarPage().clickLogoutButton();
        try {
            URL urlUser4 = new URL(String.format(ConstantsPma2.AUTHENTICATE_API_URL,
                    "user3", "user3"));

            Assert.assertTrue(xmlDoc.getXmlTextByTagNmae(urlUser4,
                    "Success").trim().equalsIgnoreCase("True"),
                    "Expected user3 successfully authorized");


            URL urlUser5 = new URL(String.format(ConstantsPma2.AUTHENTICATE_API_URL,
                    "user4", "user4"));
            Assert.assertTrue(xmlDoc.getXmlTextByTagNmae(urlUser5,
                    "Success").trim().equalsIgnoreCase("True"),
                    "Expecteduser4 successfully authorized");

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            Reporter.log("Invalid or empty xml file");
            e.printStackTrace();
        }
        Actions.loginActions().login(ConstantsPma2.ADMIN_USER_NAME, ConstantsPma2.ADMIN_PASSWORD);
        Pages.homePage().waitHomePageLounching();


        Assert.assertEquals(driver.getCurrentUrl(), System.getProperty("baseurl"));
        Assert.assertTrue(Pages.navigationBarPage().isLogoutButtonVisible(),
                "'Logout' button isn't visible");

        Pages.leftSideBarPage().clickReportsButton();
        Pages.leftSideBarPage().clickActiveSessionButton();
        ReportsPages.activeSessionsPage().waitForSessionManagementPage();


        Assert.assertTrue(ReportsPages.activeSessionsPage().isTerminateButtonByUserNamePresent("user3"),
                "xmlDoc for user: 'user3' is present.");

        Assert.assertTrue(ReportsPages.activeSessionsPage().isTerminateButtonByUserNamePresent("user4"),
                "xmlDoc for user: 'user4' is present.");

        ReportsPages.activeSessionsPage().clickTerminateButtonByUserName("user3");
        BaseActions.acceptAlert();
        ReportsPages.activeSessionsPage().clickTerminateButtonByUserName("user4");
        BaseActions.acceptAlert();
    }
}
