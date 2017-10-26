package com.pathomation.pages.diagnostics;

import com.pathomation.base.BasePage;
import com.pathomation.locators.Locator;
import com.pathomation.locators.XPath;
import org.openqa.selenium.WebElement;

public class InstallationChecksPage extends BasePage {

    private Locator checkItems = new XPath("//div[@class='widget-box' and .//h5[text()='Checks']]//table//tr//span");

    public boolean isAllCheckStatusPassed(){
        for (WebElement webElement: getElements(checkItems)){
            if (!webElement.getAttribute("class").contains("label-success")){
                return false;
            }
        }
        return true;
    }

}
