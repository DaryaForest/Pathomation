package com.pathomation.pages.responseapi;

import com.pathomation.base.BasePage;
import com.pathomation.locators.CSS;
import com.pathomation.locators.Locator;

public class GetUIDPage extends BasePage {

    Locator uid = new CSS(".line span.text");

    public String getUID() {
        return getElementText("Get UID", uid);
    }

}
