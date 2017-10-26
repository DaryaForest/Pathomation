package com.pathomation.frontendui;

import com.pathomation.actions.Actions;
import com.pathomation.base.BaseTest;
import com.pathomation.pages.Pages;
import com.pathomation.util.ConstantsPma2;
import com.pathomation.util.data.XMLparser;
import com.pathomation.util.reporter.Reporter;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;


public class TC16_ViewingSlidesWithinPMAcore extends BaseTest {

    XMLparser xmlDoc = new XMLparser();

    @BeforeClass
    public void setUp() throws IOException, ParserConfigurationException, SAXException {
        URL url = new URL(String.format(ConstantsPma2.ADMIN_AUTHENTICATE_API_URL,
                ConstantsPma2.ADMIN_USER_NAME, ConstantsPma2.ADMIN_PASSWORD));
        Reporter.log("Open url " + url);
        System.setProperty("sessionId", xmlDoc.getXmlTextByTagNmae(url, "SessionId"));
    }

    @Test(testName = "TC 16: Viewing slides within PMA.core")
    public void checkDefaultTileFormat() {
        Actions.mainActions().openMainPage();
        Actions.mainActions().clearSession();
        Assert.assertTrue(Pages.loginPage().isLoginFormVisible());
        Actions.loginActions().login(ConstantsPma2.ADMIN_USER_NAME, ConstantsPma2.ADMIN_PASSWORD);
        Pages.homePage().waitHomePageLounching();
        Pages.leftSideBarPage().clickImagesButton();
        Pages.imagesPage().waitImagesPage();
        Pages.imagesPage().selectFolder("_sys_ref");
        Actions.mainActions().wait(ConstantsPma2.ELEMENT_MICRO_TIMEOUT_SECONDS);
        Pages.imagesPage().selectSubFolder("brightfield");
        Actions.mainActions().wait(ConstantsPma2.ELEMENT_MICRO_TIMEOUT_SECONDS);
        Pages.imagesPage().selectContainingSubFolder("3DHistech");
        Pages.imagesPage().selectImage("CMU-1.mrxs");
        Actions.mainActions().wait(ConstantsPma2.ELEMENT_MICRO_TIMEOUT_SECONDS);
        Reporter.log("Expected opening new tab with image");

        if (!System.getProperty("browser").equalsIgnoreCase("InternetExplorer")) {
            Pages.imagesPage().clickThumbnail();
            Actions.mainActions().wait(ConstantsPma2.ELEMENT_MICRO_TIMEOUT_SECONDS);
            Pages.imagesPage().switchToNewTab(); // for Chrome and FF
            Assert.assertEquals(
                    String.format(
                            ConstantsPma2.VIEWER_URL, System.getProperty("sessionId"),
                            "_sys_ref", "brightfield", "3DHistech", "CMU-1.mrxs"),
                    getCurrentURLWithFormating(driver.getCurrentUrl())
            );

        } else {
            Actions.mainActions().openPage(Pages.imagesPage().getThumbnailElementHref());
            Assert.assertEquals(
                    String.format(
                            ConstantsPma2.VIEWER_URL, System.getProperty("sessionId"),
                            "_sys_ref", "brightfield", "3DHistech", "CMU-1.mrxs"),
                    getCurrentURLWithFormating(driver.getCurrentUrl())
            );
        }
    }
}
