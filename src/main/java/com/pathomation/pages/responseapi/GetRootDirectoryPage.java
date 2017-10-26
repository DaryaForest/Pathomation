package com.pathomation.pages.responseapi;

import com.pathomation.base.BasePage;
import com.pathomation.locators.CSS;
import com.pathomation.locators.Locator;

public class GetRootDirectoryPage extends BasePage {

    Locator rootDirectoryAlias = new CSS(".collapsible-content span.text");

    public String getRootDirectoryAlias() {
        return getElementText("Get root directory alias", rootDirectoryAlias);
    }

}
