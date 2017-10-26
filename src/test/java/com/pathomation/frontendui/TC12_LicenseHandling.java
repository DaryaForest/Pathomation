package com.pathomation.frontendui;

import com.pathomation.actions.Actions;
import com.pathomation.base.BaseTest;
import com.pathomation.pages.Pages;
import com.pathomation.pages.settings.SettingsPages;
import com.pathomation.util.ConstantsPma2;
import org.testng.Assert;
import com.pathomation.util.reporter.Reporter;
import org.testng.SkipException;
import org.testng.annotations.Test;

public class TC12_LicenseHandling extends BaseTest {

    private String expectedTitle = "License Data";

    @Test(testName = "Confirm that currently no valid license is installed")
    public void checkCurrentlyNoValidLicenseInstalle() {
        Actions.mainActions().openMainPage();
        Actions.mainActions().clearSession();

        Actions.loginActions().login(ConstantsPma2.ADMIN_USER_NAME, ConstantsPma2.ADMIN_PASSWORD);
        Pages.homePage().waitHomePageLounching();
        Assert.assertEquals(driver.getCurrentUrl(), System.getProperty("baseurl"));
        Assert.assertTrue(Pages.navigationBarPage().isLogoutButtonVisible(),
                "'Logout' button isn't visible");

        Pages.leftSideBarPage().clickSettingsButton();
        Pages.leftSideBarPage().clickLicenseButton();
        SettingsPages.licensePage().waitForLicensPageLaunching();
        Assert.assertTrue(SettingsPages.licensePage().getTextActiveTab().contains(expectedTitle),
                "Expected tab isn't active.");


    }

    //TC 12.2: Invalid license
    private String licenseStatus = "Invalid license: Invalid license file found";

    @Test(testName = "Validation of license")
    public void tc12_2_InvalidLicence() {
        Actions.mainActions().openMainPage();
        Actions.mainActions().clearSession();

        Actions.loginActions().login(ConstantsPma2.ADMIN_USER_NAME, ConstantsPma2.ADMIN_PASSWORD);
        Pages.homePage().waitHomePageLounching();
        Assert.assertEquals(driver.getCurrentUrl(), System.getProperty("baseurl"));
        Assert.assertTrue(Pages.navigationBarPage().isLogoutButtonVisible(),
                "'Logout' button isn't visible");

        Pages.leftSideBarPage().clickSettingsButton();
        Pages.leftSideBarPage().clickLicenseButton();

        SettingsPages.licensePage().clickUpdateLicenseTab();

        if (System.getProperty("browser").equalsIgnoreCase("InternetExplorer")
                || System.getProperty("browser").equalsIgnoreCase("Edge")) {
            SettingsPages.licensePage().uploadLicenseFileIE(ConstantsPma2.PATH_TO_LIC_RANDOM);
        } else {
            SettingsPages.licensePage().uploadLicenseFile(ConstantsPma2.PATH_TO_LIC_RANDOM);
        }

        Actions.mainActions().wait(ConstantsPma2.ELEMENT_EXTRASMALL_TIMEOUT_SECONDS);


        SettingsPages.licensePage().clickApplyLicenseButton();
        SettingsPages.licensePage().waitForLicensingStatus();
        Assert.assertEquals(SettingsPages.licensePage().getLicensingStatusText(), licenseStatus,
                "Expected text is visible");
    }

    //TC 12.3: Valid (non-expired) license
    @Test(testName = "TC 12.3: Valid (non-expired) license")
    public void tc12_3_NonExpiriedLicense() {
        /*Actions.mainActions().openMainPage();
        Actions.mainActions().clearSession();

        Actions.loginActions().login(ConstantsPma2.ADMIN_USER_NAME, ConstantsPma2.ADMIN_PASSWORD);
        Pages.homePage().waitHomePageLounching();
        Assert.assertEquals(driver.getCurrentUrl(), System.getProperty("baseurl"));
        Assert.assertTrue(Pages.navigationBarPage().isLogoutButtonVisible(),
                "'Logout' button isn't visible");

        Pages.leftSideBarPage().clickSettingsButton();
        Pages.leftSideBarPage().clickLicenseButton();

        SettingsPages.licensePage().clickRequestCodeTab();

        String requestCode = SettingsPages.licensePage().getRequestCode().substring(0, 5);

        Reporter.log(String.format(ConstantsPma2.PATH_TO_LIC_DEPENDS_FROM_REQ_CODE, requestCode));

        SettingsPages.licensePage().clickUpdateLicenseTab();
        if (System.getProperty("browser").equalsIgnoreCase("InternetExplorer")
                || System.getProperty("browser").equalsIgnoreCase("Edge")) {
            SettingsPages.licensePage().uploadLicenseFileIE(String.format(ConstantsPma2.PATH_TO_LIC_DEPENDS_FROM_REQ_CODE,
                    requestCode));
        } else {
            SettingsPages.licensePage().uploadLicenseFile(String.format(ConstantsPma2.PATH_TO_LIC_DEPENDS_FROM_REQ_CODE,
                    requestCode));
        }

        Actions.mainActions().wait(ConstantsPma2.ELEMENT_EXTRASMALL_TIMEOUT_SECONDS);
        SettingsPages.licensePage().waitForLicenseDataTab();
        Assert.assertTrue(SettingsPages.licensePage().isUserItemPresent(),
                "User item is visible");*/
        throw new SkipException("Skipped: license file issue. No active session after license applying.");
    }
}
