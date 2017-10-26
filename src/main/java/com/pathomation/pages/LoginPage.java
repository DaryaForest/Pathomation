package com.pathomation.pages;


import com.pathomation.base.BasePage;
import com.pathomation.locators.CSS;
import com.pathomation.locators.ID;
import com.pathomation.locators.Locator;
import com.pathomation.util.reporter.Reporter;

public class LoginPage extends BasePage {

    private Locator loginForm = new ID("loginform");
    private Locator usernameField = new ID("username");
    private Locator passwordField = new ID("password");
    private Locator loginButton = new CSS(".form-actions .btn");
    private Locator errorMessage = new CSS(".alert-danger p");

    public void waitLoginForm(){
        waitForElementVisibility(loginForm);
    }

    public boolean isLoginFormVisible(){
        return isElementVisible(loginForm);
    }
    public void waitForLoginForm(){
        Reporter.log("Expected login form is shown");
         waitForElementVisibility(loginForm);
    }
    public void waitForLoginButtonClickableForm(){
         waitForElementToBeClickable(loginButton);
    }

    public void waitUserNameField(){
        waitForElementVisibility(usernameField);
    }

    public void setUserName(String username){
        type("Set user name: " + username, username, usernameField);
    }

    public void waitPasswordField(){
        waitForElementVisibility(passwordField);
    }

    public void setPassword(String password){
        type("Set password: " + password, password, passwordField);
    }

    public void waitLoginButton(){
        waitForElementToBeClickable(loginButton);
    }
    public void clickLoginButton(){
        click("Click 'Login' button", loginButton);
    }

    public boolean isErrorMessageVisible(){
        Reporter.log("Expected error message");
        return isElementVisible(errorMessage);
    }

}
