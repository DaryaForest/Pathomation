package com.pathomation.pages;


import com.pathomation.base.BasePage;
import com.pathomation.locators.Locator;
import com.pathomation.locators.XPath;
import com.pathomation.util.reporter.Reporter;

public class HomePage extends BasePage{
    private Locator homePageTitle = new XPath("//div[@id='content-header']/h1[.='Home']");
    public void waitHomePageLounching(){
        Reporter.log("Expected 'home' page is launched");
       waitForElementVisibility(homePageTitle);
    }
    public boolean isHomePagePresent(){return isElementPresent(homePageTitle);}
}
