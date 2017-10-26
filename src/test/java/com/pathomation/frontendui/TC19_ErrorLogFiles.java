package com.pathomation.frontendui;


import com.pathomation.actions.MainActions;
import com.pathomation.actions.Actions;
import com.pathomation.base.BaseTest;
import com.pathomation.pages.Pages;
import com.pathomation.pages.diagnostics.DiagnosticsPages;
import com.pathomation.util.ConstantsPma2;
import com.pathomation.util.reporter.Reporter;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TC19_ErrorLogFiles extends BaseTest {

    long logFileSize;
    long logFileSizeAfterActions;
    String logFilesDocName = "tile_server_log-%s.log";


    //TC 19.1: Error log file presence
    @Test(testName = "TC 19.1: Error log file presence")
    public void tc19_1ErrorLogFilePresence() throws IOException, URISyntaxException, AWTException {

        String dataInClassicFormat;
        Date dateNow = new Date();
        SimpleDateFormat formatForDateNow;

        formatForDateNow = new SimpleDateFormat("yyyy-MM-dd");
        dataInClassicFormat = formatForDateNow.format(dateNow);
        logFilesDocName = String.format(logFilesDocName, dataInClassicFormat);

        Actions.mainActions().openMainPage();
        Actions.mainActions().clearSession();

        Actions.loginActions().login(ConstantsPma2.ADMIN_USER_NAME, ConstantsPma2.ADMIN_PASSWORD);
        Pages.homePage().waitHomePageLounching();
        Pages.leftSideBarPage().clickDiagnosticsButton();
        Pages.leftSideBarPage().clickLogFilesButton();
        String filePath = System.getProperty("user.home") + "/Downloads/" + logFilesDocName;
        //String filePath = System.getProperty("user.home") + "/" + logFilesDocName;
        final File downloadedLogFile = new File(filePath);
        Assert.assertTrue(!downloadedLogFile.exists(),
                "The 'Download' folder doesn't contains " + logFilesDocName + " file");

        DiagnosticsPages.logFilesPage().clickTodayLogs(logFilesDocName);
		Reporter.log("Wait download " + filePath + logFilesDocName);
		MainActions.wait(ConstantsPma2.ELEMENT_EXTRASMALL_TIMEOUT_SECONDS);
		
        MainActions.wait(ConstantsPma2.ELEMENT_MICRO_TIMEOUT_SECONDS);//wait for IE save dialog
        if (System.getProperty("browser").equalsIgnoreCase("InternetExplorer")) {
            //Save file in IE
            Robot robot = new Robot();
            robot.setAutoDelay(1000);
            robot.keyPress(KeyEvent.VK_ALT);
            robot.keyPress(KeyEvent.VK_S);
            robot.keyRelease(KeyEvent.VK_S);
            robot.keyRelease(KeyEvent.VK_ALT);
        }        
        Reporter.log("Check file successfully downloaded and valid " + filePath);
        Assert.assertTrue(downloadedLogFile.exists(), "File successfully downloaded");

        long fileSize = 0;
        try {
            fileSize = Files.size(downloadedLogFile.toPath());
        } catch (IOException ioException) {
            Assert.fail("ERROR: Unable to determine file size for " + filePath
                    + " due to exception " + ioException);
        }
        Assert.assertTrue(fileSize != 0, "File size is > 0");
        Assert.assertTrue(downloadedLogFile.canRead(),
                "Log file exists its path name and the file is allowed to be read by the application ");
        Assert.assertTrue(
                downloadedLogFile.delete(),
                "downloaded file was existed, successfully deleted"
        );

    }

    //TC 19.2: Error log file increase in size
    @Test(testName = "TC 19.2: Error log file increase in size",
            dependsOnMethods = "tc19_1ErrorLogFilePresence")
    public void tc19_2ErrorLogFileIncreaseInSize() throws IOException, URISyntaxException, AWTException {

        String dataInClassicFormat;
        String url;
        Date dateNow = new Date();
        SimpleDateFormat formatForDateNow;


        formatForDateNow = new SimpleDateFormat("yyyy-MM-dd");
        dataInClassicFormat = formatForDateNow.format(dateNow);
        logFilesDocName = String.format(logFilesDocName, dataInClassicFormat);

        Actions.mainActions().openMainPage();
        Actions.mainActions().clearSession();
        Actions.loginActions().login(ConstantsPma2.ADMIN_USER_NAME, ConstantsPma2.ADMIN_PASSWORD);
        Pages.homePage().waitHomePageLounching();

        Pages.leftSideBarPage().clickDiagnosticsButton();
        Pages.leftSideBarPage().clickLogFilesButton();

        //url = DiagnosticsPages.logFilesPage().getLogFileElementHrefByName(logFilesDocName);
        //String filePath = System.getProperty("user.home") + "/" + logFilesDocName;
		String filePath = System.getProperty("user.home") + "/Downloads/" + logFilesDocName;
        File downloadedLogFile = new File(filePath);
        Assert.assertTrue(!downloadedLogFile.exists(),
                "The folder doesn't contains " + logFilesDocName + " file");
        DiagnosticsPages.logFilesPage().clickTodayLogs(logFilesDocName);
		Reporter.log("Wait download " + filePath);
		MainActions.wait(ConstantsPma2.ELEMENT_EXTRASMALL_TIMEOUT_SECONDS);		
        
        if (System.getProperty("browser").equalsIgnoreCase("InternetExplorer")) {
			MainActions.wait(ConstantsPma2.ELEMENT_MICRO_TIMEOUT_SECONDS);//wait for IE save dialog
            //Save file in IE
            Robot robot = new Robot();
            robot.setAutoDelay(1000);
            robot.keyPress(KeyEvent.VK_ALT);
            robot.keyPress(KeyEvent.VK_S);
            robot.keyRelease(KeyEvent.VK_S);
            robot.keyRelease(KeyEvent.VK_ALT);
        }
        Assert.assertTrue(downloadedLogFile.exists(), "File successfully downloaded");

        try {
            logFileSize = Files.size(downloadedLogFile.toPath());
        } catch (IOException ioException) {
            Assert.fail("ERROR: Unable to determine file size for " + filePath
                    + " due to exception " + ioException);
        }
        Assert.assertTrue(
                downloadedLogFile.delete(),
                "downloaded file was existed, successfully deleted"
        );

        Pages.navigationBarPage().clickLogoutButton();
        Pages.loginPage().waitForLoginForm();
        Actions.loginActions().login(ConstantsPma2.ADMIN_USER_NAME, ConstantsPma2.ADMIN_PASSWORD);
        Pages.homePage().waitHomePageLounching();
        Pages.leftSideBarPage().clickDiagnosticsButton();

        Pages.leftSideBarPage().clickLogFilesButton();

        downloadedLogFile = new File(filePath);
        Assert.assertTrue(!downloadedLogFile.exists(),
                "The " + filePath + "  folder doesn't contains " + logFilesDocName + " file");
        DiagnosticsPages.logFilesPage().clickTodayLogs(logFilesDocName);
        Reporter.log("Wait download " + filePath + logFilesDocName);
		MainActions.wait(ConstantsPma2.ELEMENT_EXTRASMALL_TIMEOUT_SECONDS);	

        if (System.getProperty("browser").equalsIgnoreCase("InternetExplorer")) {
			MainActions.wait(ConstantsPma2.ELEMENT_MICRO_TIMEOUT_SECONDS);//wait for IE save dialog
            //Save file in IE
            Robot robot = new Robot();
            robot.setAutoDelay(1000);
            robot.keyPress(KeyEvent.VK_ALT);
            robot.keyPress(KeyEvent.VK_S);
            robot.keyRelease(KeyEvent.VK_S);
            robot.keyRelease(KeyEvent.VK_ALT);
        }

        Assert.assertTrue(downloadedLogFile.exists(), "File successfully downloaded");
        logFileSizeAfterActions = downloadedLogFile.length();

        Reporter.log("Check raising log file size after additional actions");
        Assert.assertTrue(logFileSize < logFileSizeAfterActions,
                "The second log file must be bigger than second first");
        Assert.assertTrue(
                downloadedLogFile.delete(),
                "downloaded file was existed, successfully deleted"
        );
    }


}
