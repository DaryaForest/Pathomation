package com.pathomation.pages.responseapi;

import com.pathomation.base.BasePage;
import com.pathomation.locators.Locator;
import com.pathomation.locators.XPath;

public class VersionNumberJSONPage extends BasePage {

    Locator versionNumber = new XPath("//pre");

    public void waitVersionNumber() {
        waitForElementVisibility(versionNumber);
    }

    public boolean isVersionNumberVisible() {
        return isElementVisible(versionNumber);
    }

}
