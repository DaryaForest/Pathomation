package com.pathomation.frontendui;

import com.pathomation.actions.Actions;
import com.pathomation.base.BaseTest;
import com.pathomation.pages.Pages;
import com.pathomation.pages.diagnostics.DiagnosticsPages;
import com.pathomation.util.ConstantsPma2;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC11_DiagnosticInstallationChecksAreOk extends BaseTest {

    @Test(testName = "TC 11: Diagnostic Installation Checks are ok")
    public void checkTestAllSystemChecksAreGo() {
        Actions.mainActions().openMainPage();
        Actions.mainActions().clearSession();

        Actions.loginActions().login(ConstantsPma2.ADMIN_USER_NAME, ConstantsPma2.ADMIN_PASSWORD);
        Pages.homePage().waitHomePageLounching();

        Assert.assertEquals(driver.getCurrentUrl(), System.getProperty("baseurl"));
        Assert.assertTrue(Pages.navigationBarPage().isLogoutButtonVisible(),
                "'Logout' button isn't visible");

        Pages.leftSideBarPage().clickDiagnosticsButton();
        Pages.leftSideBarPage().clickInstallationChecksButton();

        Assert.assertTrue(DiagnosticsPages.installationChecksPage().isAllCheckStatusPassed(),
                "Some check items isn't passed.");

    }
}
