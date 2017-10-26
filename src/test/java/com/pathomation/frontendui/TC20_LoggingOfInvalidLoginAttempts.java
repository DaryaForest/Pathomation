package com.pathomation.frontendui;


import com.pathomation.actions.MainActions;
import com.pathomation.actions.Actions;
import com.pathomation.base.BaseTest;
import com.pathomation.pages.Pages;
import com.pathomation.pages.user.UserPagesProvider;
import com.pathomation.util.ConstantsPma2;
import com.pathomation.util.reporter.Reporter;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URISyntaxException;

public class TC20_LoggingOfInvalidLoginAttempts extends BaseTest {

    @Test(testName = "TC 20: Logging of invalid login attempts")
    public void tc20_LoggingOfInvalidLoginAttempt() throws IOException, URISyntaxException {

        Actions.mainActions().openMainPage();
        Actions.mainActions().clearSession();
        Actions.loginActions().login("admin", "ADMIN");
        Assert.assertTrue(Pages.loginPage().isErrorMessageVisible(),
                "Error message  isn't visible");

        Actions.loginActions().login(ConstantsPma2.ADMIN_USER_NAME, ConstantsPma2.ADMIN_PASSWORD);
        Pages.homePage().waitHomePageLounching();
        Pages.leftSideBarPage().clickUsersButton();
        UserPagesProvider.usersPage().waitForUsersPageLoaded();

        UserPagesProvider.usersPage().clickLoginLink(ConstantsPma2.ADMIN_USER_NAME);
       UserPagesProvider.userLogPage().clickTab("Activity log");
        UserPagesProvider.userLogPage().waitForUserLogPageLoaded(ConstantsPma2.ADMIN_USER_NAME);
        Reporter.log("Check current url " + driver.getCurrentUrl());
        Assert.assertEquals(driver.getCurrentUrl(),
                System.getProperty("baseurl")
                        + "User/Log/"
                        + ConstantsPma2.ADMIN_USER_NAME
        );
        Reporter.log("Check invalid attempt is shown");
        MainActions.wait(ConstantsPma2.ELEMENT_MICRO_TIMEOUT_SECONDS);
        Assert.assertTrue(UserPagesProvider.userLogPage().isInvalidLoginAttemptShown());
    }
}
