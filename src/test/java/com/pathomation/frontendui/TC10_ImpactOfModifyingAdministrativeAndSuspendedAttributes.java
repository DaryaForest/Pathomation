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

public class TC10_ImpactOfModifyingAdministrativeAndSuspendedAttributes extends BaseTest {
    @Test(testName = "TC 10.1: Suspended attribute")
    public void checkNegationAdministrativeAttributes() {
        Actions.mainActions().openMainPage();
        Actions.mainActions().clearSession();
        Pages.loginPage().waitForLoginForm();

        Reporter.log("<B>Try to login us suspended and administrative properties</B>");
        Actions.loginActions().login(ConstantsPma2.USER1_NAME, ConstantsPma2.USER1_PASSWORD);
        MainActions.wait(ConstantsPma2.ELEMENT_MICRO_TIMEOUT_SECONDS);
        Assert.assertTrue(driver.getCurrentUrl().contains(ConstantsPma2.LOGIN_URL),
                "Url contains " + ConstantsPma2.LOGIN_URL
        );
        Assert.assertTrue(Pages.loginPage().isErrorMessageVisible(),
                "Error message visible");

        Reporter.log("<B>Disable 'suspended' property</B>");
        Actions.loginActions().login(ConstantsPma2.ADMIN_USER_NAME, ConstantsPma2.ADMIN_PASSWORD);
        Pages.homePage().waitHomePageLounching();
        Assert.assertEquals(driver.getCurrentUrl(), System.getProperty("baseurl"));

        Pages.leftSideBarPage().clickUsersButton();
        UserPagesProvider.usersPage().waitForUsersPageLoaded();
        UserPagesProvider.usersPage().clickEditButtonByLogin(ConstantsPma2.USER1_NAME);
        UserPagesProvider.editUserPage().waitForPageLoaded();


        UserPagesProvider.editUserPage().setCheckedAdminCheckBoxState(true);
        UserPagesProvider.editUserPage().setCheckedSuspendedCheckBoxState(false);

        UserPagesProvider.editUserPage().clickSaveButton();
        UserPagesProvider.summaryPage().waitUsersSummaryPageLoaded(ConstantsPma2.USER1_NAME);
        Pages.navigationBarPage().clickLogoutButton();
        Pages.loginPage().waitForLoginForm();
        Actions.loginActions().login(ConstantsPma2.USER1_NAME, ConstantsPma2.USER1_PASSWORD);
        Pages.homePage().waitHomePageLounching();
        Assert.assertEquals(driver.getCurrentUrl(), System.getProperty("baseurl"));
        Pages.navigationBarPage().clickLogoutButton();

        Actions.loginActions().login(ConstantsPma2.ADMIN_USER_NAME, ConstantsPma2.ADMIN_PASSWORD);
        Pages.homePage().waitHomePageLounching();
        Assert.assertEquals(driver.getCurrentUrl(), System.getProperty("baseurl"));

    }

    @Test(testName = "TC 10.2: The Administrative attribute")
    public void checkNegationSuspendedAttributes() {

        Actions.mainActions().openMainPage();
        Actions.mainActions().clearSession();
        Pages.loginPage().waitForLoginForm();

        Actions.loginActions().login(ConstantsPma2.ADMIN_USER_NAME, ConstantsPma2.ADMIN_PASSWORD);
        Pages.homePage().waitHomePageLounching();

        Reporter.log("<B>Disable 'administrative' property, enable 'suspended' property</B>");
        Pages.leftSideBarPage().clickUsersButton();
        UserPagesProvider.usersPage().waitForUsersPageLoaded();
        UserPagesProvider.usersPage().clickEditButtonByLogin(ConstantsPma2.USER1_NAME);
        UserPagesProvider.editUserPage().waitForPageLoaded();
        UserPagesProvider.editUserPage().setCheckedAdminCheckBoxState(false);
        UserPagesProvider.editUserPage().setCheckedSuspendedCheckBoxState(true);
        UserPagesProvider.editUserPage().clickSaveButton();
        UserPagesProvider.summaryPage().waitUsersSummaryPageLoaded(ConstantsPma2.USER1_NAME);
        Pages.navigationBarPage().clickLogoutButton();

        Reporter.log("Try to login");
        Actions.loginActions().login(ConstantsPma2.USER1_NAME, ConstantsPma2.USER1_PASSWORD);
        MainActions.wait(ConstantsPma2.ELEMENT_EXTRASMALL_TIMEOUT_SECONDS);
        Assert.assertEquals(driver.getCurrentUrl(), ConstantsPma2.LOGIN_URL);
        Assert.assertTrue(Pages.loginPage().isErrorMessageVisible(),
                "Error message  isn't visible");
    }
}
