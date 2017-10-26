package com.pathomation.actions;

import com.pathomation.base.BaseActions;
import com.pathomation.pages.Pages;


public class LoginActions extends BaseActions {

    public void login(String userName, String password) {
        Pages.loginPage().waitUserNameField();
        Pages.loginPage().setUserName(userName);

        Pages.loginPage().waitPasswordField();
        Pages.loginPage().setPassword(password);

        Pages.loginPage().waitLoginButton();
        Pages.loginPage().clickLoginButton();
    }

}
