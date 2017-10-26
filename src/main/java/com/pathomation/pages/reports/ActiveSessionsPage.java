package com.pathomation.pages.reports;

import com.pathomation.base.BasePage;
import com.pathomation.locators.Locator;
import com.pathomation.locators.XPath;

public class ActiveSessionsPage extends BasePage {

    private Locator terminateButtonByUserName =
            new XPath("//td[@class='sorting_1' and contains(text(), '%s')]/../td/a[@title='Terminate']");
    private Locator enabledTerminateButton =
            new XPath("(//a[contains(@title,'Terminate' ) and not(contains(@class, 'disabled'))])[1]");
    private Locator activeSessionColumn = new XPath("//td[2]");
    private Locator activeSessionContentHeader = new XPath("//div[@id = 'content-header']/h1[contains(text(), 'Session Management')]");

    public void clickTerminateButtonByUserName(String userName) {
        waitForElementVisibility(terminateButtonByUserName, userName);
        //waitForElementToBeClickable(terminateButton, userName);
        click("Click 'Terminate' button for user: " + userName, terminateButtonByUserName, userName);
    }

    public boolean isTerminateButtonByUserNamePresent(String userName) {
        return isElementVisible(terminateButtonByUserName, userName);
    }

    public boolean isTerminateButtonPresent() {
        return isElementPresent(enabledTerminateButton);
    }

    public void clickEnabledTerminateButton() {
         click("Click 'Trminate' user", enabledTerminateButton);
    }
    public void clickEnabledTerminateButtons() {
         click("Click 'Trminate' user", enabledTerminateButton);
    }

    public void waitForSessionManagementPage() {
        waitForElementVisibility(activeSessionContentHeader);
    }

    public String getActiveSession() {
        return getElementText("Get active session Id ", activeSessionColumn);
    }


}
