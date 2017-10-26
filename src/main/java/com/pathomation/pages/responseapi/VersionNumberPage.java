package com.pathomation.pages.responseapi;

import com.pathomation.base.BasePage;
import com.pathomation.locators.CSS;
import com.pathomation.locators.Locator;

public class VersionNumberPage extends BasePage {

    Locator versionNumber = new CSS(".text");

    public void waitVersionNumber() {
        waitForElementVisibility(versionNumber);
    }

    public boolean isVersionNumberVisible() {
        return isElementVisible(versionNumber);
    }

}
