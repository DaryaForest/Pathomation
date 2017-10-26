package com.pathomation.frontendui;


import com.pathomation.actions.Actions;
import com.pathomation.actions.MainActions;
import com.pathomation.base.BaseTest;
import com.pathomation.pages.Pages;
import com.pathomation.util.ConstantsPma2;
import com.pathomation.util.data.URLConnection;
import com.pathomation.util.reporter.Reporter;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;

public class TC21_InstructionsAndManualsAvailability extends BaseTest {


    @Test(testName = "TC 21.1: PMA.core manual availability")
    public void tc21_InstructionsAndManualsAvailability() throws IOException, URISyntaxException, ParserConfigurationException, AWTException {

        //TC 21.1: PMA.core manual availability
        Actions.mainActions().openMainPage();
        Actions.mainActions().clearSession();
        Actions.loginActions().login(ConstantsPma2.ADMIN_USER_NAME, ConstantsPma2.ADMIN_PASSWORD);
        Pages.homePage().waitHomePageLounching();


        if (!System.getProperty("browser").equalsIgnoreCase("InternetExplorer")) {
            Pages.leftSideBarPage().clickManualButton();
            MainActions.wait(ConstantsPma2.ELEMENT_MICRO_TIMEOUT_SECONDS);

            if (!System.getProperty("browser").equalsIgnoreCase("firefox")) {
                Pages.imagesPage().switchToNewTab(); // for Chrome and and Edge
            }
            if (System.getProperty("browser").equalsIgnoreCase("firefox")) {
                Pages.imagesPage().switchToJustOpenedWindow();
            }
            MainActions.wait(ConstantsPma2.ELEMENT_EXTRASMALL_TIMEOUT_SECONDS);
            Reporter.log("Compare url");
            Assert.assertEquals(
                    driver.getCurrentUrl(),
                    ConstantsPma2.BASE_URL + "Content/" + ConstantsPma2.MANUAL_NAME
            );
            Reporter.log("Check content type");
            Assert.assertEquals(
                    URLConnection.getContentType(new URL(driver.getCurrentUrl())), "application/pdf"
            );

        } else {
            Reporter.log("Test " + Pages.leftSideBarPage().getManualButtonHref());
            Actions.mainActions().openPage(Pages.leftSideBarPage().getManualButtonHref());
            MainActions.wait(ConstantsPma2.ELEMENT_MICRO_TIMEOUT_SECONDS);

            String filePath = System.getProperty("user.home") + "/Downloads/" + ConstantsPma2.MANUAL_NAME;
            final File downloadedLogFile = new File(filePath);
            if (downloadedLogFile.exists()) {
                downloadedLogFile.delete();
            }

            //Save file in IE
            Robot robot = new Robot();
            robot.setAutoDelay(1000);
            robot.keyPress(KeyEvent.VK_ALT);
            robot.keyPress(KeyEvent.VK_S);
            robot.keyRelease(KeyEvent.VK_S);
            robot.keyRelease(KeyEvent.VK_ALT);

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
                    "Log file exists its path name and the file is allowed to be read by the application");
            Assert.assertTrue(
                    downloadedLogFile.delete(),
                    "downloaded file was existed, successfully deleted"
            );
        }

    }
}