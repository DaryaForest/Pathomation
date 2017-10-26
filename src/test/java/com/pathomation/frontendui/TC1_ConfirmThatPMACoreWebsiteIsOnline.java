package com.pathomation.frontendui;

import com.pathomation.actions.Actions;
import com.pathomation.base.BaseTest;
import com.pathomation.util.reporter.Reporter;
import org.testng.Assert;
import org.testng.annotations.Test;


public class TC1_ConfirmThatPMACoreWebsiteIsOnline extends BaseTest {

    @Test(testName = "TC 1: Confirm that the PMA.core website is online")
    public void checkThatPMACoreWebsiteIsOnline() {
        Actions.mainActions().openMainPage();
        Actions.mainActions().clearSession();
        com.pathomation.pages.Pages.loginPage().waitForLoginForm();
        Reporter.log("Confirm that PMA.core is running and accessible via a web browser");
        Assert.assertTrue(com.pathomation.pages.Pages.loginPage().isLoginFormVisible(), "Expected login form is visible");
        Assert.assertTrue(
                driver.getCurrentUrl().contains("pma.core."),
                "Expected page opened."
        );
    }

}
