package com.pathomation.pages.user;


import com.pathomation.base.BasePage;
import com.pathomation.locators.Locator;
import com.pathomation.locators.XPath;
import com.pathomation.util.reporter.Reporter;

/**
 * http://localhost:8081/test/pma.core.2/User/Details/admin page definition
 */
public class SummaryPage extends BasePage{
    public Locator summaryPageHeader = new XPath("//h1[text()=\"User '%s' Details\"]");


    public void waitUsersSummaryPageLoaded(String userLogin) {
        Reporter.log("Expected 'user's summary' page is launched");
        waitForElementVisibility(summaryPageHeader, userLogin);
    }
}
