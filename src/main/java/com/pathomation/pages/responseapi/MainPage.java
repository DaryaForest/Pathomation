package com.pathomation.pages.responseapi;

import com.pathomation.base.BasePage;
import com.pathomation.locators.CSS;
import com.pathomation.locators.Locator;

public class MainPage extends BasePage{

    Locator wsdlTypes = new CSS("#collapsible1.collapsible");

    public void waitWsdlTypes(){
        waitForElementVisibility(wsdlTypes);
    }

    public boolean isWsdlTypesVisible(){
        return isElementVisible(wsdlTypes);
    }

}
