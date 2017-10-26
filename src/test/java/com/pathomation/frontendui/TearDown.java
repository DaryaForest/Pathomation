package com.pathomation.frontendui;

import com.pathomation.actions.Actions;
import com.pathomation.base.BaseTest;
import com.pathomation.pages.Pages;
import com.pathomation.pages.settings.SettingsPages;
import com.pathomation.pages.user.UserPagesProvider;
import com.pathomation.util.ConstantsPma2;
import com.pathomation.util.data.User;
import org.testng.Assert;
import com.pathomation.util.reporter.Reporter;
import org.testng.annotations.Test;


public class TearDown extends BaseTest {

/*    @Test(testName = "setIncorrectLicenseFile")
    public void setIncorrectLicenseFile() {
        Actions.mainActions().clearSession();
        Actions.mainActions().openMainPage();
        Pages.loginPage().waitForLoginForm();
        Actions.loginActions().login(ConstantsPma2.ADMIN_USER_NAME, ConstantsPma2.ADMIN_PASSWORD);
        Pages.homePage().waitHomePageLounching();
        Actions.mainActions().wait(ConstantsPma2.ELEMENT_EXTRASMALL_TIMEOUT_SECONDS);

        Assert.assertEquals(driver.getCurrentUrl(), System.getProperty("baseurl"));
        Assert.assertTrue(Pages.navigationBarPage().isLogoutButtonVisible(),
                "'Logout' button isn't visible");

        Pages.leftSideBarPage().clickSettingsButton();
        Pages.leftSideBarPage().clickLicenseButton();

        SettingsPages.licensePage().clickUpdateLicenseTab();

        if (System.getProperty("browser").equals("InternetExplorer"))
            SettingsPages.licensePage().uploadLicenseFileIE(
                    String.format(ConstantsPma2.PATH_TO_LIC_RANDOM));
        else
            SettingsPages.licensePage().uploadLicenseFile(
                    String.format(ConstantsPma2.PATH_TO_LIC_RANDOM));
        SettingsPages.licensePage().clickApplyLicenseButton();
        Actions.mainActions().wait(ConstantsPma2.ELEMENT_MICRO_TIMEOUT_SECONDS);
        SettingsPages.licensePage().waitForLicensingStatus();
        Assert.assertEquals(
                SettingsPages.licensePage().getLicensingStatusText(),
                "Invalid license: Invalid license file found",
                "Expected text isn't visible"
        );
    }

    @Test
    public void deleteAddedWithApiCallUser() {
        if (System.getProperty("pma.version").equals("2")) {
            User user1 = new User();
            boolean isUserSuccessfullyDeleted = false;

            Actions.mainActions().clearSession();
            Actions.mainActions().openPage("http://localhost:8081/test/pma.core.2");
            Assert.assertTrue(Pages.loginPage().isLoginFormVisible());
            Assert.assertTrue(
                    driver.getCurrentUrl().contains("pma.core."),
                    "Expected page isn't opened.");
            Actions.loginActions().login(ConstantsPma2.ADMIN_USER_NAME, ConstantsPma2.ADMIN_PASSWORD);
            Pages.homePage().waitHomePageLounching();

            Pages.leftSideBarPage().clickUsersButton();
            UserPagesProvider.usersPage().waitForUsersPageLoaded();


            //if count of pages > 1, then do search on all of them; -2 because of 'Previous'  and 'Next' buttons
            int counOfUsersPages = UserPagesProvider.usersPage().countOfPaginateButtons() - 2;
            if (counOfUsersPages > 1) {
                for (int i = 1; i <= counOfUsersPages; i++) {
                    if (UserPagesProvider.usersPage().isUserLoginPresentInTheLoginColumn(ConstantsPma2.ADD_USER_LOGIN)) {
                        UserPagesProvider.usersPage().clickDeleteUserButton(ConstantsPma2.ADD_USER_LOGIN);
                        Actions.mainActions().wait(ConstantsPma2.ELEMENT_MICRO_TIMEOUT_SECONDS);
                        driver.switchTo().alert().accept();
                        isUserSuccessfullyDeleted = true;
                        break;
                    } else if (!UserPagesProvider.usersPage().isNextButtonDisabled()) {
                        UserPagesProvider.usersPage().clickNextButton();
                        isUserSuccessfullyDeleted = true;
                    }

                }
            } else if (UserPagesProvider.usersPage().isUserLoginPresentInTheLoginColumn(ConstantsPma2.ADD_USER_LOGIN)) {
                UserPagesProvider.usersPage().clickDeleteUserButton(ConstantsPma2.ADD_USER_LOGIN);
                driver.switchTo().alert().accept();
                isUserSuccessfullyDeleted = true;

            } else {
                isUserSuccessfullyDeleted = true;
                Reporter.log("Delete was failed. User " + ConstantsPma2.ADD_USER_LOGIN + " isn't in Users table");
                Assert.assertTrue(
                        isUserSuccessfullyDeleted,
                        "Expected user " + ConstantsPma2.ADD_USER_LOGIN + " isn't in Users table"
                );


            }
        }

    }*/

}
