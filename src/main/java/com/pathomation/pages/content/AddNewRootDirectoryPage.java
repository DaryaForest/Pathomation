package com.pathomation.pages.content;

import com.pathomation.base.BasePage;
import com.pathomation.locators.CSS;
import com.pathomation.locators.ID;
import com.pathomation.locators.Locator;
import com.pathomation.locators.XPath;

public class AddNewRootDirectoryPage extends BasePage {

    Locator pathField = new ID("Path");
    Locator aliasField = new ID("Alias");
    Locator userNameField = new ID("Username");
    Locator passwordField = new ID("Password");
    Locator domainNameField = new ID("DomainName");
    Locator createButton = new CSS(".form-actions input");
    Locator errorMessage = new CSS(".alert-danger p");
    Locator visibilityRadioButton =
            new XPath("//div[@id='root-dir-visibility']//label//input[@value='%s']/../..");
    private Locator addRootDirectoryPageHeader = new XPath("//h1[text()='Add Root Directory']");

    public void waitForAddRootDirectoryPageLoaded() {
        waitForElementPresent(addRootDirectoryPageHeader);
    }
    public void waitForPathField() {
        waitForElementVisibility(pathField);
    }

    public void setPathField(String path) {
        wait(2);
        type("Set 'Path' field: ", path, pathField);
    }


    public void setAliasField(String alias) {
        type("Set 'Alias' field: ", alias, aliasField);
    }

    public void setUserNameField(String userName) {
        type("Set 'Impersonation username' field: ", userName, userNameField);
    }

    public void setPasswordField(String password) {
        type("Set 'Impersonation password' field: ", password, passwordField);
    }

    public void setDomainField(String domain) {
        type("Set 'Impersonation domain name' field: ", domain, domainNameField);
    }

    public void clickCreateButton() {
        click("Click 'Create' button", createButton);
    }

    public boolean isErrorMessageVisible() {
        return isElementVisible(errorMessage);
    }

    public String getErrorMessage() {
        return getElementText("Get error message", errorMessage);
    }

    public void setVisibilityState(boolean visibilityState) {
        click("Set visibility state", visibilityRadioButton, visibilityState);
    }


}
