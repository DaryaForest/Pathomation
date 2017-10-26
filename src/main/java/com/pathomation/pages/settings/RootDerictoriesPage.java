package com.pathomation.pages.settings;


import com.pathomation.base.BasePage;
import com.pathomation.locators.Locator;
import com.pathomation.locators.XPath;

public class RootDerictoriesPage extends BasePage{
    private Locator rootDerictoriesPageTitle = new XPath("//div[@id='content-header']/h1[.='Root directories']");
    public void waitRootDerictoriesPageLounching(){waitForElementVisibility(rootDerictoriesPageTitle);}


}
