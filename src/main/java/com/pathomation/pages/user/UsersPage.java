package com.pathomation.pages.user;

import com.pathomation.base.BasePage;
import com.pathomation.locators.Locator;
import com.pathomation.locators.XPath;
import com.pathomation.util.reporter.Reporter;

public class UsersPage extends BasePage {

    public Locator userPageHeader = new XPath("//h1[text()='Users']");
    public Locator userLoginInTheLoginColumn =
            new XPath(
                    "//div[@id='users-index-table_wrapper']//td[contains(@class, 'col-login')]/a[contains(text(), '%s')]");
    public Locator userFNameInTheFNmaeColumn =
            new XPath(
                    "//div[@id='users-index-table_wrapper']//td[contains(@class, 'col-firstname') and contains(text(), '%s')]");
    public Locator userLNameInTheLNmaeColumn =
            new XPath(
                    "//div[@id='users-index-table_wrapper']//td[contains(@class, 'col-lastname') and contains(text(), '%s')]");
    public Locator userEmailInTheEmailColumn =
            new XPath(
                    "//div[@id='users-index-table_wrapper']//td[contains(@class, 'col-email')]/a[contains(text(), '%s')]");
    //col-email
    public Locator addUserButton = new XPath("//span[contains(text(), 'Add')]");

    public Locator userLogin = new XPath("//td[contains(@class,'login')]/a[text()='%s']");


    public Locator adminColumnBoxByLogin = new XPath(
            "//a[.='%s']/../following-sibling::td[@class='col-administrator']"
    );

    public Locator canAnnotateCheckBoxByLogin =
            new XPath("//a[.='%s']/../following-sibling::td[@class='col-can-annotate']");

    public Locator suspendedCheckBoxByLogin =
            new XPath("//a[.='%s']/../following-sibling::td[@class='col-suspended']");

    public Locator editUserButtonByLogin =
            new XPath("//td/a[.='user1']/../following-sibling::td//a[@title='Edit']");
    //TODO
	public Locator deleteUserButtonByLogin =
            new XPath("//td[contains(@class,'login')]/a[text()='%s']/../../td//a[@title='Delete']");
    //pma2
    public Locator paginateButtons =
            new XPath("//ul[@class='pagination']//a[@href='#']");
    public Locator disabledNextButton =
            new XPath("//li[contains(@class, 'next') and contains(@class, 'disabled')]");
    public Locator nextButton =
            new XPath("//a[contains(text(),'Next')]");
    public Locator adminCheckBoxByLogin =
            new XPath("//td[contains(@class,'login')]/a[text()='%s']/../../td[@class='col-administrator']//input");

    public void waitForUsersPageLoaded() {
        Reporter.log("Expected 'user' page is launched");
        waitForElementVisibility(userPageHeader);
    }


    /*    public boolean isAdminCheckBoxByLoginSelected(String login) {
            return (getElementAttributeValue("Check is admin check box selected",
                    "checked", adminCheckBoxByLogin, login).contains("checked"));

        }*/
    public boolean isAdminByLogin(String login) {
        return (getElementText(adminColumnBoxByLogin, login).trim().equals("True"));

    }

    public boolean isCanAnnotateByLogin(String login) {
        return (isCheckboxChecked(canAnnotateCheckBoxByLogin, login));

    }

    public boolean isSuspendedByLogin(String login) {
        return (isCheckboxChecked(suspendedCheckBoxByLogin, login));

    }

    public boolean isUserLoginPresentInTheLoginColumn(String userLogin) {
        return isElementPresent("Check user login presence in the Login column  ",
                userLoginInTheLoginColumn, userLogin
        );
    }

    public boolean isUserFNamePresentInTheFNameColumn(String firstName) {
        return isElementPresent("Check user FName presence in the 'FirstName' column  ",
                userFNameInTheFNmaeColumn, firstName
        );
    }

    public boolean isUserLNamePresentInTheLNameColumn(String lastName) {
        return isElementPresent("Check user LName presence in the 'LastName' column  ",
                userLNameInTheLNmaeColumn, lastName
        );
    }

    public boolean isUserEmailPresentInTheEmailColumn(String email) {
        return isElementPresent("Check user Email presence in the 'Email' column  ",
                userEmailInTheEmailColumn, email
        );
    }

    public void clickAddButton() {
        click("Click 'Add' button ", addUserButton);
    }

    public void clickDeleteUserButton(String userLogin) {
        click("Click 'Delete' button ", deleteUserButtonByLogin, userLogin);
    }


    public void clickNextButton() {
        click("Click 'Next' button ", nextButton);
    }

    public void clickEditButtonByLogin(String login) {
        //waitForElementToBeClickable(editUserButtonByLogin, login);
        click("Click 'Edit' button user: " + login, editUserButtonByLogin, login);
    }

    public void clickLoginLink(String login) {
        click("Click login link by login " + login, userLoginInTheLoginColumn, login);
    }

    public boolean isAdminCheckBoxByLoginChecked(String login) {
        return (isCheckboxChecked(adminCheckBoxByLogin, login));

    }


    public boolean isUserExist(String loginName) {
        return isElementPresent(userLogin, loginName);
    }

    public boolean isNextButtonDisabled() {
        return isElementPresent(disabledNextButton);
    }

    public int countOfPaginateButtons() {
        return getElementsCount(paginateButtons);
    }
}
