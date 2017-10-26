package com.pathomation.pages.responseapi;

import com.pathomation.base.BasePage;
import com.pathomation.locators.CSS;
import com.pathomation.locators.Locator;

public class AuthenticatePage extends BasePage {

    //TODO
    Locator reasonStatus = new CSS("#collapsible0 > div.expanded > div.collapsible-content > div:nth-child(1) > span.text");
    Locator sessionID = new CSS("#collapsible0 > div.expanded > div.collapsible-content > div:nth-child(2) > span.text");
    Locator successStatus = new CSS("#collapsible0 > div.expanded > div.collapsible-content > div:nth-child(3) > span.text");

    public void waitReasonStatus(){
        waitForElementVisibility(reasonStatus);
    }

    public String getReasonStatus() {
        return getElementText("Get 'Reason' status", reasonStatus);
    }

    public String getSessionID() {
        return getElementText("Get session id", sessionID);
    }

    public String getSuccessStatus() {
        return getElementText("Get 'Success' status", successStatus);
    }

}
