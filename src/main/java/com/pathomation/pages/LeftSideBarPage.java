package com.pathomation.pages;

import com.pathomation.base.BasePage;
import com.pathomation.locators.CSS;
import com.pathomation.locators.ID;
import com.pathomation.locators.Locator;
import com.pathomation.locators.XPath;

public class LeftSideBarPage extends BasePage {

    private Locator usersButton = new CSS("#main-menu-users a");

    private Locator diagnosticsButton = new XPath("//li[@id='main-sub-menu-diagnostics']/a");
    private Locator settingsButton = new XPath("//li[@id='main-sub-menu-settings']/a");
    private Locator imageButton = new XPath("//span[contains(text(), 'Images')]");
    private Locator imageSettingsButton = new XPath("//a[contains(text(),'Image Settings')]");
    private Locator reportsButton = new XPath("//li[@id='main-sub-menu-reports']/a");

    private Locator installationChecksButton = new ID("main-sub-menu-installation");
    private Locator licenseButton = new ID("main-sub-menu-license");
    private Locator activeSessionButton = new ID("main-sub-menu-sessions");
    private Locator logFilesButton = new XPath("//li/a[.='Log files']");
    private Locator manualButton = new CSS("li#main-menu-manual a");

    public void clickManualButton(){
        waitForElementToBeClickable(manualButton);
        click("Click 'Manual' button", manualButton);
    }

    public String getManualButtonHref(){
        return getElementAttributeValue("Get Manual button href","href", manualButton);
    }

    public void clickUsersButton(){
        waitForElementToBeClickable(usersButton);
        click("Click 'Users' button", usersButton);
    }

    public void clickLogFilesButton(){
        waitForElementToBeClickable(logFilesButton);
        click("Click 'Log files' button", logFilesButton);
    }

    public void clickDiagnosticsButton(){
        waitForElementToBeClickable(diagnosticsButton);
        click("Click 'Diagnostics' button", diagnosticsButton);
    }

    public void clickInstallationChecksButton(){
        waitForElementVisibility(installationChecksButton);
        waitForElementToBeClickable(installationChecksButton);
        click("Click 'Installation Checks' button", installationChecksButton);
    }

    public void clickSettingsButton(){
        waitForElementToBeClickable(settingsButton);
        click("Click 'Settings' button", settingsButton);
    }
    public void clickImageSettingsButton(){
        waitForElementToBeClickable(imageSettingsButton);
        click("Click 'imageSettings' button", imageSettingsButton);
    }
    public void clickImagesButton(){
        waitForElementToBeClickable(imageButton);
        click("Click 'Image' button", imageButton);
    }


    public void clickLicenseButton(){
        waitForElementVisibility(licenseButton);
        waitForElementToBeClickable(licenseButton);
        click("Click 'Installation Checks' button", licenseButton);
    }

    public void clickReportsButton(){
        waitForElementToBeClickable(reportsButton);
        click("Click 'Reports' button", reportsButton);
    }

    public void clickActiveSessionButton(){
        waitForElementVisibility(activeSessionButton);
        waitForElementToBeClickable(activeSessionButton);
        click("Click 'Active session' button", activeSessionButton);
    }

}
