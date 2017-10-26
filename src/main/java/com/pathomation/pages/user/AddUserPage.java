package com.pathomation.pages.user;


import com.pathomation.base.BasePage;
import com.pathomation.locators.ID;
import com.pathomation.locators.Locator;
import com.pathomation.locators.XPath;

public class AddUserPage extends BasePage {
    private Locator addUserPageHeader = new XPath("//h1[text()='Add user']");
    private Locator firstName = new ID("FirstName");
    private Locator lastName = new ID("LastName");
    private Locator login = new ID("Login");
    private Locator password = new ID("Password");
    private Locator confirmPassword = new ID("ConfirmPassword");
    private Locator email = new ID("Email");

    private Locator create = new XPath("//input[@type='submit']");
    private Locator adminCheckBox = new XPath("//div//input[@id='Administrator']");

    private Locator suspendedCheckBox = new XPath("//div//input[@id='Suspended']");

    private Locator canAnnotateCheckBox = new XPath("//div//input[@id='CanAnnotate']");

    public void setFirstName(String firstName) {
        type("Set '" + firstName + "' in 'First name' field", firstName, this.firstName);
    }

    public void waitFirstNameField() {
        waitForElementPresent(firstName);
    }
    public void waitForAddUserField() {
        waitForElementPresent(addUserPageHeader);
    }

    public void setLastName(String lastName) {
        type("Set '" + lastName + "' in 'Last name' field", lastName, this.lastName);

    }

    public void setLogin(String login) {
        type("Set '" + login + "' in 'Login ' field", login, this.login);

    }

    public void setPassword(String password) {
        type("Set '" + password + "' in 'Password' field", password, this.password);

    }

    public void setConfirmPassword(String confirmPassword) {
        type("Set '" + confirmPassword + "' in 'Confirm password' field", confirmPassword, this.confirmPassword);

    }

    public void setEmail(String email) {
        type("Set '" + email + "' in 'Email' field", email, this.email);

    }


    public void setCheckedAdminCheckBoxState (boolean checked) {
        setCheckboxState("Set 'admin' checkbox " + checked, checked, adminCheckBox);
    }
    public void setCheckedSuspendedCheckBoxState (boolean checked) {
        setCheckboxState("Set 'suspended' checkbox " + checked, checked, suspendedCheckBox);

    }
    public void setCheckedCanAnnotateCheckBoxState (boolean checked) {
        setCheckboxState("Set 'can annotate' checkbox " + checked, checked, canAnnotateCheckBox);

    }

    public void clickCreateButton() {
        click("Click 'create' button ", create);
    }



}
