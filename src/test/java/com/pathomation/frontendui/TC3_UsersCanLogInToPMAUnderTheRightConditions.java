package com.pathomation.frontendui;

import com.pathomation.actions.Actions;
import com.pathomation.base.BaseTest;
import com.pathomation.pages.Pages;
import com.pathomation.util.ConstantsPma2;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC3_UsersCanLogInToPMAUnderTheRightConditions extends BaseTest {

    //TC 3.1: users can log in with the right username and password
    @Test(description = "Confirm that you can log in at the website with username = 'admin', password = 'admin'",
            testName = "TC 3.1: users can log in with the right username and password")
    public void checkUsersCanLoginWithRightUsernamePassword() {
        Actions.mainActions().openMainPage();
        Actions.mainActions().clearSession();
        Pages.loginPage().waitForLoginForm();

        Actions.loginActions().login(ConstantsPma2.ADMIN_USER_NAME, ConstantsPma2.ADMIN_PASSWORD);
        Pages.homePage().waitHomePageLounching();

        Assert.assertEquals(driver.getCurrentUrl(), System.getProperty("baseurl"));
        Assert.assertTrue(Pages.navigationBarPage().isLogoutButtonVisible(),
                "'Logout' button isn't visible");
    }

    //TC 3.2: usernames are case-insensitive
    @Test(description = "Confirm that you can log in at the website with username = 'AdMin', password = 'admin'",
            testName = "3.2: usernames are case-insensitive")
    public void checkUserNamesAreCaseInsensitive() {

        Actions.mainActions().openMainPage();
        Actions.mainActions().clearSession();
        Actions.loginActions().login("AdMin", ConstantsPma2.ADMIN_PASSWORD);
        Pages.homePage().waitHomePageLounching();
        Assert.assertEquals(driver.getCurrentUrl(), System.getProperty("baseurl"));
        Assert.assertTrue(Pages.navigationBarPage().isLogoutButtonVisible(),
                "'Logout' button isn't visible");

    }
}
