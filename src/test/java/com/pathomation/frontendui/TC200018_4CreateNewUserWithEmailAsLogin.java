package com.pathomation.frontendui;


import com.pathomation.actions.Actions;
import com.pathomation.actions.MainActions;
import com.pathomation.actions.userActions.UserActionsProvider;
import com.pathomation.base.BaseTest;
import com.pathomation.pages.Pages;
import com.pathomation.pages.user.UserPagesProvider;
import com.pathomation.util.ConstantsPma2;
import com.pathomation.util.data.User;
import org.testng.Assert;
import com.pathomation.util.reporter.Reporter;
import org.testng.annotations.Test;

public class TC200018_4CreateNewUserWithEmailAsLogin extends BaseTest {

    @Test(testName = "TC 200018.4: Create a new user with an email as login")
    public void checkUserCreate() {

        User user1 = new User();
        user1.setLogin("user@foobar.com");
        user1.setAdmin(true);

        Actions.mainActions().openMainPage();
        Actions.mainActions().clearSession();

        Assert.assertTrue(Pages.loginPage().isLoginFormVisible());
        Assert.assertTrue(
                driver.getCurrentUrl().contains("pma.core."),
                "Expected page isn't opened.");
        Actions.loginActions().login(ConstantsPma2.ADMIN_USER_NAME, ConstantsPma2.ADMIN_PASSWORD);
        Pages.homePage().waitHomePageLounching();

        Pages.leftSideBarPage().clickUsersButton();
        //delete user if such user already exist
        UserPagesProvider.usersPage().waitForUsersPageLoaded();
        if (UserActionsProvider.usersPageActions().checkUserAdded(user1)) {
            Assert.assertTrue(UserActionsProvider.usersPageActions().deleteUser(user1),
                    "Expected user " + ConstantsPma2.ADD_USER_LOGIN + " isn't in Users table");
            MainActions.wait(ConstantsPma2.ELEMENT_SMALL_TIMEOUT_MILLISECONDS);

        }
        //UserPagesProvider.usersPagePma2().waitForUsersPageLoaded();
        UserPagesProvider.usersPagePma2().clickAddButton();
        UserActionsProvider.addUserActionsmaPma2().fillAddUserForm(user1);
        UserPagesProvider.addUserPagePma2().clickCreateButton();
        MainActions.wait(ConstantsPma2.ELEMENT_SMALL_TIMEOUT_MILLISECONDS);


        UserPagesProvider.usersPagePma2().waitForUsersPageLoaded();
        Assert.assertTrue(
                UserActionsProvider.usersPageActions().checkUserAdded(user1),
                "Expected user data visible in the table"
        );
        Reporter.log("<B>Check new admin user can login successfully</B><br>");
        Pages.navigationBarPage().clickLogoutButton();
        Pages.loginPage().waitLoginForm();
        Actions.loginActions().login(user1.getLogin(), user1.getPass());
        Pages.homePage().waitHomePageLounching();
        Assert.assertTrue(Pages.navigationBarPage().isLogoutButtonVisible(),
                "'Logout' button isn't visible");

    }
}
