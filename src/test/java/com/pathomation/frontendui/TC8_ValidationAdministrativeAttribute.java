package com.pathomation.frontendui;

import com.pathomation.actions.Actions;
import com.pathomation.base.BaseTest;
import com.pathomation.pages.Pages;
import com.pathomation.pages.user.UserPagesProvider;
import com.pathomation.util.ConstantsPma2;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC8_ValidationAdministrativeAttribute extends BaseTest {

    @Test(testName = "TC 8: Validation of the Administrative attribute")
    public void checkAdministrativeAttribute() {
        Actions.mainActions().openMainPage();
        Actions.mainActions().clearSession();
        Actions.loginActions().login(ConstantsPma2.ADMIN_USER_NAME, ConstantsPma2.ADMIN_PASSWORD);
        Pages.homePage().waitHomePageLounching();
        Assert.assertEquals(driver.getCurrentUrl(), System.getProperty("baseurl"));
        Pages.leftSideBarPage().clickUsersButton();

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
        Assert.assertEquals(driver.getCurrentUrl(), ConstantsPma2.BASE_URL);
        Assert.assertTrue(Pages.navigationBarPage().isLogoutButtonVisible(),
                "'Logout' button isn't visible");
    }
}
