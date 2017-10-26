package com.pathomation.pages.user;

import com.pathomation.base.BasePage;
import com.pathomation.locators.Locator;
import com.pathomation.locators.XPath;

public class UserLogPage extends BasePage{

    private Locator editUserPageHeader = new XPath("//h1[text()=\"User '%s' Log\"]");
    private Locator previousInvalidLoginAttempt = new XPath("//table[@id='systemAccessTable']//tr[2]/td[.='false']");

    public void waitForUserLogPageLoaded(String userLogin) {
        waitForElementVisibility(editUserPageHeader, userLogin);
    }

    public boolean isInvalidLoginAttemptShown() {
        return isElementPresent(previousInvalidLoginAttempt);
    }

    private Locator navTab = new XPath("//ul[contains(@class, 'nav-tabs')]//a[.='%s']");

    public void clickTab(String tabName) {
        waitForElementToBeClickable(navTab, tabName);
        click("Click '" + tabName + "' tab", navTab, tabName);
    }
}
