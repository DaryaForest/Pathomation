package com.pathomation.pages;


import com.pathomation.base.BasePage;
import com.pathomation.locators.CSS;
import com.pathomation.locators.Locator;
import com.pathomation.locators.XPath;

public class ImagesPage extends BasePage {
    private Locator folderWithImg = new XPath("//li[@path='%s']/ins");
    private Locator subFolder = new XPath("//a[contains(text(),'%s')]/../ins");
    private Locator subFolderWithImg = new XPath("//a[contains(text(),'%s')]");
    private Locator imageFile = new XPath("//a[contains(text(),'%s')]");
    private Locator imageThubnail = new CSS("a#lnkViewer");
    private Locator imagePageHeader = new XPath("//h1[contains(text(), 'Image Management')]");

    public void selectFolder(String folder) {
        waitForElementVisibility(folderWithImg, folder);
        click("Click folder", folderWithImg, folder);
    }

    public void selectSubFolder(String subFolderName) {
        waitForElementVisibility(subFolder, subFolderName);
        click("Click subFolder", subFolder, subFolderName);
    }

    public void selectContainingSubFolder(String subFolder) {
        waitForElementVisibility(subFolderWithImg, subFolder);
        click("Click subFolder", subFolderWithImg, subFolder);
    }

    public void selectImage(String fileName) {
        waitForElementVisibility(imageFile, fileName);
        click("Click file", imageFile, fileName);
    }

    public void clickThumbnail() {
        waitForElementVisibility(imageThubnail);
        click("Click imageThubnail", imageThubnail);
    }

    public String getThumbnailElementHref() {
        waitForElementVisibility(imageThubnail);
        return getElementAttributeValue("Get imageThubnail href ", "href", imageThubnail);
    }

    public void switchToNewTab() {
        switchToJustOpenedTab();
    }

    public void waitImagesPage() {
        waitForElementPresent(imagePageHeader);
    }


}
