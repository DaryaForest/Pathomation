package com.pathomation.pages.user;

import com.pathomation.base.BasePage;
import com.pathomation.locators.Locator;
import com.pathomation.locators.XPath;
import com.pathomation.util.reporter.Reporter;

public class EditUserPage extends BasePage {

    //breadcrumbs
    private Locator breadcrumb = new XPath("//div[@id='breadcrumb']/a[@title='%s']");


    private Locator editUserPageHeader = new XPath("//h1[text()='Edit user']");

    private Locator adminCheckBox = new XPath("//div//input[@id='Administrator']");
    //private Locator adminCheckBox2 = new XPath("//div//input[@id='Administrator']/following-sibling::ins");

    private Locator suspendedCheckBox = new XPath("//div//input[@id='Suspended']");
    //private Locator suspendedCheckBox2 = new XPath("//div//input[@id='Suspended']/following-sibling::ins");

    private Locator canAnnotateCheckBox = new XPath("//div//input[@id='Suspended']");
    //private Locator canAnnotateCheckBox2 = new XPath("//div//input[@id='Suspended']/following-sibling::ins");

    private Locator saveButton = new XPath("//input[@value='Save']");


    public void waitForPageLoaded() {
        Reporter.log("Expected 'edit user' page is launched");
        waitForElementVisibility(editUserPageHeader);
    }

    public void clickAdminCheckBoxState() {
        waitForElementToBeClickable(adminCheckBox);
        click("Click administrator check box", adminCheckBox);
    }

    public void clickSuspendedCheckBoxState() {
        waitForElementToBeClickable(suspendedCheckBox);
        click("Click suspended check box", suspendedCheckBox);
    }

    public void clickSaveButton() {
        waitForElementToBeClickable(saveButton);
        click("Click 'Save' button", saveButton);
    }
    public void clickBreadcramb(String breadcrumbName) {
        waitForElementToBeClickable(breadcrumb, breadcrumbName);
        click("Click breadcramb " + breadcrumbName, breadcrumb, breadcrumbName);
    }

    public boolean isAdministratorPropertyChecked() {
        return getElementAttributeValue("Check is administrator property checked",
                "class", adminCheckBox).contains("checked");
    }

    public boolean isSuspendedPropertyChecked() {
        return getElementAttributeValue("Check is suspended property checked",
                "class", adminCheckBox).contains("checked");
    }

    public void setCheckedAdminCheckBoxState(boolean checked) {
        setCheckboxState("Set 'admin' checkbox " + checked, checked, adminCheckBox);
    }

    public void setCheckedSuspendedCheckBoxState(boolean checked) {
        setCheckboxState("Set 'suspended' checkbox " + checked, checked, suspendedCheckBox);

    }

    public void setCheckedCanAnnotateCheckBoxState(boolean checked) {
        setCheckboxState("Set 'can annotate' checkbox " + checked, checked, canAnnotateCheckBox);

    }

    public void waitForEditUserPageLoaded() {
        Reporter.log("Expected 'edit user' page is launched");
        waitForElementVisibility(editUserPageHeader);
    }



}
