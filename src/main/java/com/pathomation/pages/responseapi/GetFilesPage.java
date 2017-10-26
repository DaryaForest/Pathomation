package com.pathomation.pages.responseapi;

import com.pathomation.base.BasePage;
import com.pathomation.locators.CSS;
import com.pathomation.locators.Locator;

public class GetFilesPage extends BasePage {

    Locator filesContent = new CSS(".collapsible-content span.text");

    public String getFilesContent() {
        return getElementText("Get files content", filesContent);
    }

}
