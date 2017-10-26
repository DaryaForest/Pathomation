package com.pathomation.frontendui;

import com.pathomation.actions.Actions;
import com.pathomation.actions.MainActions;
import com.pathomation.base.BaseTest;
import com.pathomation.pages.Pages;
import com.pathomation.pages.user.UserPagesProvider;
import com.pathomation.util.ConstantsPma2;
import com.pathomation.util.reporter.Reporter;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC2_AccessIsDeniedForUsersThatHaveNoAccess extends BaseTest {

    @Test(testName = "TC 2.1: Non-aministrative users cannot log into PMA.core")
    public void checkIsUser1IsNotAdministrator() {
        Actions.mainActions().openMainPage();
        Actions.mainActions().clearSession();
        Pages.loginPage().waitForLoginButtonClickableForm();
        Actions.loginActions().login(ConstantsPma2.ADMIN_USER_NAME, ConstantsPma2.ADMIN_PASSWORD);
		        

        Pages.homePage().waitHomePageLounching();
        Assert.assertEquals(driver.getCurrentUrl(), System.getProperty("baseurl"));

        Pages.leftSideBarPage().clickUsersButton();
        UserPagesProvider.usersPage().waitForUsersPageLoaded();
        Assert.assertTrue(UserPagesProvider.usersPage().isUserExist(ConstantsPma2.USER1_NAME),
                "User 'User1' isn't exist in database");

        UserPagesProvider.usersPage().clickEditButtonByLogin(ConstantsPma2.USER1_NAME);
        UserPagesProvider.editUserPage().waitForPageLoaded();
        UserPagesProvider.editUserPage().setCheckedAdminCheckBoxState(false);
        UserPagesProvider.editUserPage().setCheckedSuspendedCheckBoxState(false);
        UserPagesProvider.editUserPage().clickSaveButton();
        UserPagesProvider.summaryPage().waitUsersSummaryPageLoaded(ConstantsPma2.USER1_NAME);
        Assert.assertEquals(
                driver.getCurrentUrl(), String.format(
                        ConstantsPma2.USER_DETAILS_HREF,
                        ConstantsPma2.USER1_NAME
                )
        );
        UserPagesProvider.editUserPage().clickBreadcramb("Users");
        UserPagesProvider.usersPagePma2().waitForUsersPageLoaded();
        Assert.assertFalse(UserPagesProvider.usersPage().isAdminByLogin(ConstantsPma2.USER1_NAME),
                "Attribute IsAdministrator isn't set to false for user 'User1'");

        Pages.navigationBarPage().clickLogoutButton();
        Pages.loginPage().waitForLoginForm();

        Actions.loginActions().login(ConstantsPma2.USER1_NAME, ConstantsPma2.USER1_PASSWORD);
        MainActions.wait(ConstantsPma2.ELEMENT_EXTRASMALL_TIMEOUT_SECONDS);
        Pages.loginPage().waitForLoginButtonClickableForm();
        Assert.assertTrue(Pages.loginPage().isErrorMessageVisible(),
                "Error message  isn't visible");
    }


    @Test(testName = "TC 2.2: non-existing users cannot log in")
    public void checkIsFooUserNotExist() {
        Actions.mainActions().openMainPage();
        Actions.mainActions().clearSession();
        Reporter.log("<B>Confirm that 'foo' doesn't exist in the database</B>");
        Pages.loginPage().waitForLoginForm();
        Actions.loginActions().login(ConstantsPma2.ADMIN_USER_NAME, ConstantsPma2.ADMIN_PASSWORD);
		Pages.homePage().waitHomePageLounching();
        Pages.leftSideBarPage().clickUsersButton();
        UserPagesProvider.usersPage().waitForUsersPageLoaded();
        Reporter.log("Expected 'foo' user doesn't exist");
        Assert.assertTrue(!UserPagesProvider.usersPage().isUserExist("foo"),
                "User 'Foo' doesn't exist in database");

        Pages.navigationBarPage().clickLogoutButton();
        Pages.loginPage().waitForLoginForm();
        Reporter.log("<B>Confirm that non-existing users cannot log in</B>");
        Actions.loginActions().login("foo", "bar");
        Actions.mainActions().wait(ConstantsPma2.ELEMENT_EXTRASMALL_TIMEOUT_SECONDS);
        Assert.assertEquals(driver.getCurrentUrl(), System.getProperty("baseurl") + "LogOn");
        Assert.assertTrue(Pages.loginPage().isErrorMessageVisible(),
                "Error message  isn't visible");


    }

    @Test(testName = "TC 2.3: Password are case-sensitive")
    public void checkIsAdminIsAdministrator() {
        Actions.mainActions().openMainPage();
        Actions.mainActions().clearSession();
        Pages.loginPage().waitForLoginButtonClickableForm();
        Actions.loginActions().login(ConstantsPma2.ADMIN_USER_NAME, ConstantsPma2.ADMIN_PASSWORD);
		Pages.homePage().waitHomePageLounching();
        Assert.assertEquals(driver.getCurrentUrl(), System.getProperty("baseurl"));
        Assert.assertTrue(Pages.navigationBarPage().isLogoutButtonVisible(),
                "'Logout' button isn't visible");
        Pages.leftSideBarPage().clickUsersButton();
        UserPagesProvider.usersPage().waitForUsersPageLoaded();
        Assert.assertTrue(UserPagesProvider.usersPage().isUserExist(ConstantsPma2.ADMIN_USER_NAME),
                "User 'User1' isn't exist in database");
        Assert.assertTrue(UserPagesProvider.usersPage().isAdminByLogin(ConstantsPma2.ADMIN_USER_NAME),
                "Attribute IsAdministrator set to true for user 'admin'");
        Pages.navigationBarPage().clickLogoutButton();
        Pages.loginPage().waitForLoginForm();
        Actions.loginActions().login(ConstantsPma2.ADMIN_USER_NAME, ConstantsPma2.ADMIN_PASSWORD.toUpperCase());
        MainActions.wait(ConstantsPma2.ELEMENT_EXTRASMALL_TIMEOUT_SECONDS);
        Assert.assertTrue(Pages.loginPage().isErrorMessageVisible(),
                "Error message  isn't visible");


    }
}
