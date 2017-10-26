package com.pathomation.frontendui;


import com.pathomation.actions.Actions;
import com.pathomation.actions.userActions.UserActionsProvider;
import com.pathomation.base.BaseTest;
import com.pathomation.pages.Pages;
import com.pathomation.pages.user.UserPagesProvider;
import com.pathomation.util.ConstantsPma2;
import com.pathomation.util.data.User;
import org.testng.Assert;
import com.pathomation.util.reporter.Reporter;
import org.testng.annotations.Test;


public class TC18_UserCreation extends BaseTest {

    @Test(testName = "TC 18.1: Create a new administrative user")
    public void checkAdminUserCreate() {
        User user1 = new User();
        user1.setAdmin(true);
        Actions.mainActions().openMainPage();
        Actions.mainActions().clearSession();
        Assert.assertTrue(Pages.loginPage().isLoginFormVisible());
        Actions.loginActions().login(ConstantsPma2.ADMIN_USER_NAME, ConstantsPma2.ADMIN_PASSWORD);
        Pages.homePage().waitHomePageLounching();

        Pages.leftSideBarPage().clickUsersButton();

        UserPagesProvider.usersPage().waitForUsersPageLoaded();
        if (UserActionsProvider.usersPageActions().checkUserAdded(user1)) {
            Assert.assertTrue(UserActionsProvider.usersPageActions().deleteUser(user1),
                    "Expected user " + ConstantsPma2.ADD_USER_LOGIN + " isn't in Users table");
        }
        UserPagesProvider.usersPage().clickAddButton();

        UserActionsProvider.addUserActions().fillAddUserForm(user1);
        UserPagesProvider.addUserPage().clickCreateButton();


        UserPagesProvider.usersPage().waitForUsersPageLoaded();
        Assert.assertTrue(
                UserActionsProvider.usersPageActions().checkUserAdded(user1),
                "Expected user data visible in the table"
        );
        Reporter.log("<B>Check new admin user can login successfully</B>");
        Pages.navigationBarPage().clickLogoutButton();
        Pages.loginPage().waitLoginForm();
        Actions.loginActions().login(user1.getLogin(), user1.getPass());
        Pages.homePage().waitHomePageLounching();
        Assert.assertTrue(Pages.navigationBarPage().isLogoutButtonVisible(),
                "'Logout' button isn't visible");
    }


    @Test(testName = "TC 18.2: Create a new non-administrative user")
    public void checkNonAdminUserCreate() {
        User user1 = new User();
        Actions.mainActions().openMainPage();
        Actions.mainActions().clearSession();
        Assert.assertTrue(Pages.loginPage().isLoginFormVisible());

        Actions.loginActions().login(ConstantsPma2.ADMIN_USER_NAME, ConstantsPma2.ADMIN_PASSWORD);
        Pages.homePage().waitHomePageLounching();

        Pages.leftSideBarPage().clickUsersButton();
        UserPagesProvider.usersPage().waitForUsersPageLoaded();
        if (UserActionsProvider.usersPageActions().checkUserAdded(user1)) {
            Assert.assertTrue(UserActionsProvider.usersPageActions().deleteUser(user1),
                    "Expected user " + ConstantsPma2.ADD_USER_LOGIN + " isn't in Users table");
        }
        UserPagesProvider.usersPage().waitForUsersPageLoaded();

        UserPagesProvider.usersPage().clickAddButton();
        UserActionsProvider.addUserActions().fillAddUserForm(user1);
        UserPagesProvider.addUserPage().clickCreateButton();
        UserPagesProvider.usersPage().waitForUsersPageLoaded();

        Assert.assertTrue(
                UserActionsProvider.usersPageActions().checkUserAdded(user1),
                "Expected user data visible in the table"
        );
        Reporter.log("<B>Check new non-administrative user can't login</B>");
        Pages.navigationBarPage().clickLogoutButton();
        Pages.loginPage().waitLoginForm();
        Actions.loginActions().login(user1.getLogin(), user1.getPass());
        Pages.loginPage().waitForLoginForm();
        Assert.assertTrue(Pages.loginPage().isErrorMessageVisible(),
                "Error message  isn't visible");



    }

    @Test(testName = "TC 18.3: Create a new suspended user")
    public void checkSuspendedUserCreate() {
        User user1 = new User();
        user1.setAdmin(true);
        user1.setSuspended(true);
        Actions.mainActions().openMainPage();
        Actions.mainActions().clearSession();
        Assert.assertTrue(Pages.loginPage().isLoginFormVisible());
        Assert.assertTrue(
                driver.getCurrentUrl().contains("pma.core."),
                "Expected page isn't opened.");
        Actions.loginActions().login(ConstantsPma2.ADMIN_USER_NAME, ConstantsPma2.ADMIN_PASSWORD);
        Pages.homePage().waitHomePageLounching();

        Pages.leftSideBarPage().clickUsersButton();
        UserPagesProvider.usersPage().waitForUsersPageLoaded();
        if (UserActionsProvider.usersPageActions().checkUserAdded(user1)) {
            Assert.assertTrue(UserActionsProvider.usersPageActions().deleteUser(user1),
                    "Expected user " + ConstantsPma2.ADD_USER_LOGIN + " isn't in Users table");
        }
        UserPagesProvider.usersPage().waitForUsersPageLoaded();
        UserPagesProvider.usersPage().clickAddButton();
        UserActionsProvider.addUserActions().fillAddUserForm(user1);
        UserPagesProvider.addUserPage().clickCreateButton();
        UserPagesProvider.usersPage().waitForUsersPageLoaded();

        Assert.assertTrue(
                UserActionsProvider.usersPageActions().checkUserAdded(user1),
                "Expected user data visible in the table"
        );
        Reporter.log("<B>Check new non-administrative user can't login</B>");
        Pages.navigationBarPage().clickLogoutButton();
        Pages.loginPage().waitLoginForm();
        Actions.loginActions().login(user1.getLogin(), user1.getPass());
        //Pages.leftSideBarPage().clickUsersButton();
        Pages.loginPage().waitForLoginForm();
        Assert.assertTrue(Pages.loginPage().isErrorMessageVisible(),
                "Error message  isn't visible"
        );


    }


}