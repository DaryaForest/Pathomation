package com.pathomation.frontendui;

import com.pathomation.actions.Actions;
import com.pathomation.base.BaseTest;
import com.pathomation.pages.Pages;
import com.pathomation.pages.content.ContentPage;
import com.pathomation.pages.settings.SettingsPages;
import com.pathomation.util.ConstantsPma2;
import com.pathomation.util.data.RootDirectory;
import com.pathomation.util.reporter.Reporter;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TC4_CreateNewRootDirectory extends BaseTest {

    private RootDirectory rootDirectory;

    private String incorrectPath = "d:/pathomation/no_wsi_data/reference/";

    private String correctPath = "d:/pathomation/";

    private String alias = ConstantsPma2.ALIAS_REF;

    private String user = "someone";

    private String password = "";

    private String domainName = "somewhere";

    private boolean visibilityState = false;

    private String errorNetworkNameCannotBeFound = "The network name cannot be found";

    private String errorCouldNotFindPartOfThePath = "Path does not exist";

    private String errorAliasMustBeUnique = "Alias must be unique";

    @BeforeMethod
    public void setUp() {
        rootDirectory = new RootDirectory(
                incorrectPath,
                correctPath,
                alias,
                user,
                password,
                domainName,
                visibilityState
        );
    }

    @Test(testName = "TC 4.2: create Public root-directory", priority = 1)
    public void checkCreatingNewRootDirectoryWithCorrectData() {
        Actions.mainActions().openMainPage();
        Actions.mainActions().clearSession();
        Pages.loginPage().waitForLoginForm();

        Actions.loginActions().login(ConstantsPma2.ADMIN_USER_NAME, ConstantsPma2.ADMIN_PASSWORD);
        Pages.homePage().waitHomePageLounching();
        Actions.mainActions().openPage(ConstantsPma2.ROOT_DIRECTORY_URL);
        if (ContentPage.rootDirectoriesPage().isRootDirectoryByAliasCellContentVisible(ConstantsPma2.ALIAS_REF)) {
            ContentPage.rootDirectoriesPage().clickDeleteButtonByCellContent(ConstantsPma2.ALIAS_REF);
            Actions.mainActions().wait(ConstantsPma2.ELEMENT_MICRO_TIMEOUT_SECONDS);
            driver.switchTo().alert().accept();
            Actions.mainActions().wait(ConstantsPma2.ELEMENT_EXTRASMALL_TIMEOUT_SECONDS);

        }
        ContentPage.rootDirectoriesPage().clickAddDirectoryButton();
        ContentPage.rootDirectoriesPage().clickAddDropDownElement("File system");
        ContentPage.addNewRootDirectoryPage().waitForAddRootDirectoryPageLoaded();
        rootDirectory.setUsername("");
        rootDirectory.setDomainName("");


        Actions.rootDirectoryActions().addNewRootDirectory(rootDirectory, true);
        ContentPage.rootDirectoriesPage().waitForRootDirectoriesPageLoaded();
        Assert.assertEquals(driver.getCurrentUrl(), ConstantsPma2.ROOT_DIRECTORY_URL);
        Assert.assertEquals(
                ContentPage.contentHeaderPage().getContentHeader(),
                ConstantsPma2.ROOT_DIRECTORY_CONTENT_HEADER
        );

        Assert.assertTrue(
                ContentPage.rootDirectoriesPage().isRootDirectoryByAliasCellContentVisible(ConstantsPma2.ALIAS_REF),
                "Row with alias: " + ConstantsPma2.ALIAS_REF + " is visible"
        );
    }

    @Test(testName = "TC 4.1: conditions under which root-directoriy creation fails")
    public void checkCreatingNewRootDirectoryWithIncorrectData() {
        {
            Actions.mainActions().openMainPage();
            Actions.mainActions().clearSession();

            Pages.loginPage().waitForLoginForm();
            Actions.loginActions().login(ConstantsPma2.ADMIN_USER_NAME, ConstantsPma2.ADMIN_PASSWORD);
            Pages.homePage().waitHomePageLounching();
            Actions.mainActions().openPage(ConstantsPma2.ROOT_DIRECTORY_URL);
            Assert.assertEquals(driver.getCurrentUrl(), ConstantsPma2.ROOT_DIRECTORY_URL);
            SettingsPages.rootDerictoriesPage().waitRootDerictoriesPageLounching();
            if (ContentPage.rootDirectoriesPage().isRootDirectoryByAliasCellContentVisible(ConstantsPma2.ALIAS_REF)) {
                ContentPage.rootDirectoriesPage().clickDeleteButtonByCellContent(ConstantsPma2.ALIAS_REF);
                Actions.mainActions().wait(ConstantsPma2.ELEMENT_MICRO_TIMEOUT_SECONDS);
                driver.switchTo().alert().accept();
                Actions.mainActions().wait(ConstantsPma2.ELEMENT_EXTRASMALL_TIMEOUT_SECONDS);

            }
            Assert.assertEquals(
                    ContentPage.contentHeaderPage().getContentHeader(),
                    ConstantsPma2.ROOT_DIRECTORY_CONTENT_HEADER
            );

            ContentPage.rootDirectoriesPage().clickAddDirectoryButton();
            ContentPage.rootDirectoriesPage().clickAddDropDownElement("File system");

            ContentPage.addNewRootDirectoryPage().waitForAddRootDirectoryPageLoaded();
            Actions.rootDirectoryActions().addNewRootDirectory(rootDirectory, false);
            Assert.assertTrue(ContentPage.addNewRootDirectoryPage().isErrorMessageVisible(),
                    "Error message isn't visible");
            Assert.assertEquals(ContentPage.addNewRootDirectoryPage().getErrorMessage(),
                    errorNetworkNameCannotBeFound);

            Assert.assertEquals(driver.getCurrentUrl(), ConstantsPma2.CREATE_ROOT_DIRECTORY_URL);

            rootDirectory.setUsername("");
            rootDirectory.setDomainName("");

            Actions.rootDirectoryActions().addNewRootDirectory(rootDirectory, false);

            Actions.mainActions().wait(ConstantsPma2.ELEMENT_MICRO_TIMEOUT_SECONDS);

            Assert.assertTrue(ContentPage.addNewRootDirectoryPage().isErrorMessageVisible(),
                    "Error message isn't visible");

            Assert.assertEquals(ContentPage.addNewRootDirectoryPage().getErrorMessage(),
                    errorCouldNotFindPartOfThePath);

            Assert.assertEquals(driver.getCurrentUrl(), ConstantsPma2.CREATE_ROOT_DIRECTORY_URL);

            rootDirectory.setAlias(ConstantsPma2.ALIAS);
            Actions.rootDirectoryActions().addNewRootDirectory(rootDirectory, true);
            Assert.assertTrue(ContentPage.addNewRootDirectoryPage().isErrorMessageVisible(),
                    "Error message isn't visible");
            Assert.assertEquals(ContentPage.addNewRootDirectoryPage().getErrorMessage(),
                    errorAliasMustBeUnique);
        }
    }
}
