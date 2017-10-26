package com.pathomation.pages.content;

import com.pathomation.base.BasePage;
import com.pathomation.locators.CSS;
import com.pathomation.locators.Locator;

public class ContentHeaderPage extends BasePage {

    private Locator contentRegionHeader = new CSS("#content-header h1");
    private Locator currentBreadCrumb = new CSS(".current");

    public String getContentHeader(){
        return getElementText("Get content header", contentRegionHeader);
    }

    public String getCurrentBreadCrumb() {
        return getElementText("Get current bread crumb", currentBreadCrumb);
    }

}
