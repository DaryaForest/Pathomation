package com.pathomation.pages.settings;


import com.pathomation.base.BasePage;
import com.pathomation.locators.CSS;
import com.pathomation.locators.Locator;
import com.pathomation.locators.XPath;

public class ImageSettingsPage extends BasePage {

    private Locator tileFormatDropdown = new CSS("select#Format");
    private Locator saveButt = new XPath("//input[@value = 'Save']");
    private Locator tileFormatDropDownText = new CSS("#Format");
    private Locator tileSize = new CSS("input#TileSize");
    private Locator imageSettingsPaheHeader = new XPath("//div[@id='content-header']/h1[.='Image Settings']");

    public void waitImageSettingsPage() {
        waitForElementVisibility(imageSettingsPaheHeader);
    }

    public String getImageDefaultFormat() {
        return getDropDownListSelectedValueText("Get selected(default) value from 'Format' dropdown", tileFormatDropdown);
    }

    public String getTileSize() {
        String size = getElementAttributeValue("Getting default tile size", "value", tileSize);
        return size + "*" + size;
    }

    public void setImageFormat(String format) {
        selectDropDownListOption("Select option " + format, format, tileFormatDropdown);
    }

    public void setTileSize(String size) {
        type("Set size " + size, size, tileSize);
    }

    public void clickSaveButton() {
        click("Click save button ", saveButt);
    }

}
