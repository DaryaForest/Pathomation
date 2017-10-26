package com.pathomation.pages.diagnostics;

import com.pathomation.base.BasePage;
import com.pathomation.locators.Locator;
import com.pathomation.locators.XPath;

public class LogFilesPage extends BasePage {

    private Locator logFilesDoc = new XPath("//a[.='%s']");


    /**
     * click the log file generated for today data
     */
    public void clickTodayLogs(String date) {
        click("Click today log doc file", logFilesDoc, date);
    }

    /**
     * return href of today(date) log file
     */
    public String getLogFileElementHrefByName(String logFileName) {
        return getElementAttributeValue("Get href from today's log file element ", "href", logFilesDoc, logFileName);
    }

}
