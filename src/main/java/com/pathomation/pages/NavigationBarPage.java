package com.pathomation.pages;

import com.pathomation.base.BasePage;
import com.pathomation.locators.Locator;
import com.pathomation.locators.XPath;

public class NavigationBarPage extends BasePage {

    private Locator logoutButton = new XPath("//div[@id='user-nav']//i[contains(@class, 'fa-share')]/..");
    //private Locator logoutButton = new XPath("//a[contains(@href,'LogOff')]");


    public void waitLogoutButton() {
        waitForElementVisibility(logoutButton);
    }

    public boolean isLogoutButtonVisible() {
        return isElementVisible(logoutButton);
    }

    public void clickLogoutButton() {
        //waitForElementToBeClickable(logoutButton);//doesn't work for FF
        click("Click 'Log out' button", logoutButton);
    }

}
