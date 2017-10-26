package com.pathomation.pages.user;


import com.pathomation.base.BasePage;
import com.pathomation.locators.ID;
import com.pathomation.locators.Locator;
import com.pathomation.locators.XPath;

public class AddUserPagePma2 extends BasePage {
    private Locator firstName = new ID("FirstName");
    private Locator lastName = new ID("LastName");
    private Locator login = new ID("Login");
    private Locator password = new ID("Password");
    private Locator confirmPassword = new ID("ConfirmPassword");
    private Locator email = new ID("Email");
    private Locator administrator = new ID("Administrator");
    private Locator canAnnotate = new XPath("//input[@id='CanAnotate']/following-sibling::ins");
    private Locator suspended = new XPath("//input[@id='Suspended']/following-sibling::ins");
    private Locator create = new XPath("//input[@type='submit']");


    public void setFirstName(String firstName) {
        type("Set '" + firstName + "' in 'First name' field", firstName, this.firstName);
    }

    public void waitFirstNameField() {
        waitForElementPresent(firstName);
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

    public void setAdministrator() {
        click("Click 'administrator' checkbox ", administrator);

    }

    public void setCanAnnotate() {
        click("Click 'canAnnotate' checkbox ", canAnnotate);
    }

    public void setSuspended() {
        click("Click 'suspended' checkbox ", suspended);

    }

    public void clickCreateButton() {
        click("Click 'create' button ", create);
    }



}
