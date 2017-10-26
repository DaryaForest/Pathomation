package com.pathomation.frontendui;

import com.pathomation.actions.Actions;
import com.pathomation.actions.MainActions;
import com.pathomation.base.BaseTest;
import com.pathomation.pages.Pages;
import com.pathomation.pages.content.ContentPage;
import com.pathomation.pages.settings.SettingsPages;
import com.pathomation.util.ConstantsPma2;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC5_EditExistingRootDirectory extends BaseTest {

    @Test(testName = "TC 5.1: Modify ACL properties")
    public void checkEditExistingRootDirectoryTest() {

        Actions.mainActions().openMainPage();
        Actions.mainActions().clearSession();
        Pages.loginPage().waitForLoginForm();
        Actions.loginActions().login(ConstantsPma2.ADMIN_USER_NAME, ConstantsPma2.ADMIN_PASSWORD);
        Pages.homePage().waitHomePageLounching();

        Assert.assertEquals(driver.getCurrentUrl(), System.getProperty("baseurl"));
        Assert.assertTrue(Pages.navigationBarPage().isLogoutButtonVisible(),
                "'Logout' button isn't visible");

        Actions.mainActions().openPage(ConstantsPma2.ROOT_DIRECTORY_URL);

        Assert.assertEquals(
                driver.getCurrentUrl(), ConstantsPma2.ROOT_DIRECTORY_URL
        );

        Assert.assertEquals(
                ContentPage.contentHeaderPage().getContentHeader(),
                ConstantsPma2.ROOT_DIRECTORY_CONTENT_HEADER
        );

        Assert.assertTrue(
                ContentPage.rootDirectoriesPage().isRootDirectoryByAliasCellContentVisible(ConstantsPma2.ALIAS_REF),
                "Row with alias: " + ConstantsPma2.ALIAS_REF + " isn't visible"
        );

        ContentPage.rootDirectoriesPage().clickEditButtonByCellContent(ConstantsPma2.ALIAS_REF);



        ContentPage.addNewRootDirectoryPage().setPathField(ConstantsPma2.REFERENCE_PATH);
        MainActions.wait(ConstantsPma2.ELEMENT_MICRO_TIMEOUT_SECONDS);
        ContentPage.addNewRootDirectoryPage().setVisibilityState(true);
        ContentPage.addNewRootDirectoryPage().clickCreateButton();
        SettingsPages.rootDerictoriesPage().waitRootDerictoriesPageLounching();
        Assert.assertEquals(driver.getCurrentUrl(), ConstantsPma2.ROOT_DIRECTORY_URL);

        Assert.assertEquals(ContentPage.contentHeaderPage().getContentHeader(),
                ConstantsPma2.ROOT_DIRECTORY_CONTENT_HEADER
        );

        Assert.assertTrue(
                ContentPage.rootDirectoriesPage().isRowRootDirectoryByCellContentVisible(ConstantsPma2.REFERENCE_PATH),
                "Row with alias: " + ConstantsPma2.REFERENCE_PATH + " isn't visible"
        );

        Assert.assertEquals(
                ContentPage.rootDirectoriesPage().getVisibilityStateByCellContentVisible(ConstantsPma2.REFERENCE_PATH),
                "Public"
        );

    }

}
