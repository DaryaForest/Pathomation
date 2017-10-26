package com.pathomation.pages.content;

import com.pathomation.base.BasePage;
import com.pathomation.locators.Locator;
import com.pathomation.locators.XPath;

public class RootDirectoriesPage extends BasePage {

    Locator rootDirectoryPageHeader = new XPath("//h1[text()='Root directories']");


    private Locator addDirectoryButton = new XPath("//button[contains(text(), 'Add')]");

    private Locator addDropDownMenu = new XPath("//ul[contains(@class, 'pull-right')]");

    private Locator addDropDownElement = new XPath(
            "//ul[contains(@class, 'pull-right')]/li/a[.='File system']"
    );

    private Locator rootDirectoryAliasCellContent =
            new XPath("//table[@id='root-directories-index-table']//td/following-sibling::td/a[.='%s']");
    private Locator rowRootDirectoryCellContent =
            new XPath("//td[contains(text(), '%s')]");

    private Locator deleteRowRootDirectoryByCellContentButton =
            new XPath("//table[@id='root-directories-index-table']//td/a[.='%s']/../following-sibling::td/a[@title='Delete']");

    private Locator editRowRootDirectoryByCellContentButton =
            new XPath("//table[@id='root-directories-index-table']//td/a[text()= '%s']/../following-sibling::td/a[@title='Edit']");
    private Locator cellContentByAliasById =
            new XPath("//table[@id='root-directories-index-table']//td[.='%s']/../td[%s]");

    private Locator visibilityStateByPath =
            new XPath("//table[@id='root-directories-index-table']//td[contains(text(), '%s')]/../td[@class='col-visibility']");

    public void clickAddDirectoryButton() {
        waitForElementToBeClickable(addDirectoryButton);
        click("Click 'Add' (directory) button", addDirectoryButton);
    }

    public boolean isRootDirectoryByAliasCellContentVisible(String cellContent) {
        return isElementPresent(rootDirectoryAliasCellContent, cellContent);
    }

    public boolean isRowRootDirectoryByCellContentVisible(String cellContent) {
        return isElementVisible(rowRootDirectoryCellContent, cellContent);
    }

    public String getVisibilityStateByCellContentVisible(String path) {
        return getElementText("Get 'Visibility' state", visibilityStateByPath, path).trim();
    }

    public void clickDeleteButtonByCellContent(String cellContent) {
        //waitForElementToBeClickable(deleteRowRootDirectoryByCellContentButton, cellContent);
        click("Click 'Delete' row button by cell content: " + cellContent, deleteRowRootDirectoryByCellContentButton, cellContent);
    }

    public void clickEditButtonByCellContent(String cellContent) {
        waitForElementToBeClickable(editRowRootDirectoryByCellContentButton, cellContent);
        click("Click 'Edit' row button by cell content: " + cellContent, editRowRootDirectoryByCellContentButton, cellContent);
    }

    public String getCellContentByAliasById(String alias, String id) {
        return getElementText("Get cell content by alias and by id", cellContentByAliasById, alias, id);
    }

    public void waitForRootDirectoriesPageLoaded() {
        waitForElementPresent(rootDirectoryPageHeader);
    }

    public void clickAddDropDownElement(String dropdownElementName) {
        waitForElementPresent(addDropDownElement, dropdownElementName);
        click("click dropdown element " + dropdownElementName, addDropDownElement, dropdownElementName);
    }


}
