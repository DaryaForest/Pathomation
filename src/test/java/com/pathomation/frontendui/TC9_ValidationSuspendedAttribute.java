package com.pathomation.frontendui;

import com.pathomation.actions.Actions;
import com.pathomation.base.BaseTest;
import com.pathomation.pages.Pages;
import com.pathomation.pages.user.UserPagesProvider;
import com.pathomation.util.ConstantsPma2;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC9_ValidationSuspendedAttribute extends BaseTest {

    @Test(testName = "TC 9: Validation of the Suspended attribute")
    public void checkSuspendedAttribute() {
        Actions.mainActions().openMainPage();
        Actions.mainActions().clearSession();

        Actions.loginActions().login(ConstantsPma2.ADMIN_USER_NAME, ConstantsPma2.ADMIN_PASSWORD);
        Pages.homePage().waitHomePageLounching();

        Assert.assertEquals(driver.getCurrentUrl(), System.getProperty("baseurl"));
        Assert.assertTrue(Pages.navigationBarPage().isLogoutButtonVisible(),
                "'Logout' button isn't visible");

        Pages.leftSideBarPage().clickUsersButton();

        UserPagesProvider.usersPage().waitForUsersPageLoaded();
        UserPagesProvider.usersPage().clickEditButtonByLogin(ConstantsPma2.USER1_NAME);

        UserPagesProvider.editUserPage().waitForPageLoaded();

        UserPagesProvider.editUserPage().setCheckedSuspendedCheckBoxState(true);
        UserPagesProvider.editUserPage().clickSaveButton();
        UserPagesProvider.summaryPage().waitUsersSummaryPageLoaded(ConstantsPma2.USER1_NAME);

        Pages.navigationBarPage().clickLogoutButton();
        Pages.loginPage().waitForLoginForm();

        Actions.loginActions().login(ConstantsPma2.USER1_NAME, ConstantsPma2.USER1_PASSWORD);
        Actions.mainActions().wait(ConstantsPma2.ELEMENT_EXTRASMALL_TIMEOUT_SECONDS);

        Assert.assertEquals(driver.getCurrentUrl(), ConstantsPma2.LOGIN_URL);
        Assert.assertTrue(Pages.loginPage().isErrorMessageVisible(),
                "Error message  isn't visible");
    }
}
