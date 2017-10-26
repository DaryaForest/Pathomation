package com.pathomation.pages.settings;

import com.pathomation.base.BasePage;
import com.pathomation.locators.CSS;
import com.pathomation.locators.Locator;
import com.pathomation.locators.XPath;

public class LicensePage extends BasePage {

    private Locator textActiveTab = new XPath("//ul[contains(@class, 'nav-tabs')]/li[@class='active']//a");
    private Locator lisensingPageHeader = new XPath("//div[@id='content-header']/h1[.='Licensing']");
    private Locator licenseDataTab = new XPath("//a[./span[contains(text(), 'License Data')]]");
    private Locator updateLicenseTab = new XPath("//a[./span[contains(text(), 'Update License')]]");
    private Locator requestCodeTab = new XPath("//a[./span[contains(text(), 'Request Code')]]");
    private XPath chooseFileButton = new XPath("//input[@id='licenseFile']");
    //private XPath filePathFieldForIe = new XPath("//input[@id='licenseFile']");
    private Locator applyLicenseButton = new XPath("//input[@value='Apply License']");
    private Locator licensingStatus = new XPath("//div[contains(@class, 'alert-danger')]/span");
    private Locator userItem = new XPath("//div[@id='tabs-1']//span[text()='User:']");
    private Locator requestCode = new CSS("#tabs-2 code");

    public String getTextActiveTab(){
        return getElementText("Get active tab's text", textActiveTab);
    }

    public void clickUpdateLicenseTab(){
        waitForElementToBeClickable(updateLicenseTab);
        click("Click 'Update License' tab", updateLicenseTab);
    }

    public void uploadLicenseFile(String pathToLicenseFile){
        uploadFile(pathToLicenseFile, chooseFileButton);
    }
    public void uploadLicenseFileIE(String pathToLicenseFile){
        uploadFileIE(pathToLicenseFile, chooseFileButton);
    }

    public void clickApplyLicenseButton(){
        waitForElementToBeClickable(applyLicenseButton);
        click("Click 'Apply License' button", applyLicenseButton);
    }

    public String getLicensingStatusText(){
        return getElementText("Get 'Licensing status' text", licensingStatus);
    }

    public void waitForLicensingStatus(){
        waitForElementVisibility(licensingStatus);
    }
    public void waitForLicensPageLaunching(){
        waitForElementVisibility(lisensingPageHeader);
    }

    public void clickRequestCodeTab(){
        waitForElementToBeClickable(requestCodeTab);
        click("Click 'Request Code' tab", requestCodeTab);
    }

    public String getRequestCode(){
        return getElementText("Get 'Request code'", requestCode);
    }

    public void waitForLicenseDataTab(){
        waitForElementVisibility(licenseDataTab);
    }

    public boolean isUserItemPresent(){
        return isElementPresent(userItem);
    }
}
