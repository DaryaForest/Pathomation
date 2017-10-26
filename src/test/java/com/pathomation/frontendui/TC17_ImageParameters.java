package com.pathomation.frontendui;


import com.pathomation.actions.Actions;
import com.pathomation.actions.settings.SettingsActions;
import com.pathomation.actions.userActions.UserActionsProvider;
import com.pathomation.base.BaseTest;
import com.pathomation.pages.Pages;
import com.pathomation.pages.settings.SettingsPages;
import com.pathomation.pages.user.UserPagesProvider;
import com.pathomation.util.ConstantsPma2;
import com.pathomation.util.data.User;
import com.pathomation.util.data.XMLparser;
import com.pathomation.util.reporter.Reporter;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;


public class TC17_ImageParameters extends BaseTest {

    private String tileHref1;
    private String tileHref2;
    XMLparser xmlDoc = new XMLparser();

    @BeforeClass
    public void setUp() throws IOException, ParserConfigurationException, SAXException {
        URL url = new URL(String.format(ConstantsPma2.ADMIN_AUTHENTICATE_API_URL,
                ConstantsPma2.ADMIN_USER_NAME, ConstantsPma2.ADMIN_PASSWORD));
        Reporter.log("Open url " + url);
        System.setProperty("sessionId", xmlDoc.getXmlTextByTagNmae(url, "SessionId"));
        tileHref1 = System.getProperty("baseurl") + "tile?sessionID=" + System.getProperty("sessionId")
                + ConstantsPma2.IMAGE_TILE_HREF1;
        tileHref2 = System.getProperty("baseurl") + "tile?sessionID=" + System.getProperty("sessionId")
                + ConstantsPma2.IMAGE_TILE_HREF2;

    }
    @Test(testName = "TC 17.1 Tile format")
    public void checkDefaultTileFormat() {
        Actions.mainActions().openMainPage();
        Actions.mainActions().clearSession();
        Pages.loginPage().waitForLoginForm();
        Actions.loginActions().login(ConstantsPma2.ADMIN_USER_NAME, ConstantsPma2.ADMIN_PASSWORD);
        Pages.homePage().waitHomePageLounching();

        Pages.leftSideBarPage().clickSettingsButton();
        Pages.leftSideBarPage().clickImageSettingsButton();
        SettingsPages.imageSettingsPage().waitImageSettingsPage();
        Assert.assertTrue(SettingsPages.imageSettingsPage().getImageDefaultFormat()
                .contains(ConstantsPma2.IMAGE_DEFAULT_FORMAT));


        Reporter.log("Checking tile format ");
        try {
            Assert.assertEquals(SettingsActions.imageSettingsActions().getContentFormat(tileHref1),
                    ConstantsPma2.IMAGE_JPEG_FORMAT);
        } catch (IOException e) {
            e.printStackTrace();
        }

        SettingsPages.imageSettingsPage().setImageFormat("Png");
        SettingsPages.imageSettingsPage().clickSaveButton();
        Actions.mainActions().wait(ConstantsPma2.ELEMENT_MICRO_TIMEOUT_SECONDS);

        Reporter.log("Checking image format after changing to png");
        try {
            Assert.assertEquals(
                    SettingsActions.imageSettingsActions().getContentFormat(tileHref1), ConstantsPma2.IMAGE_PNG_FORMAT
            );
        } catch (IOException e) {
            e.printStackTrace();
        }

        Reporter.log("Checking another tile format after changing to png");
        try {
            Assert.assertEquals(
                    SettingsActions.imageSettingsActions().getContentFormat(tileHref2), ConstantsPma2.IMAGE_PNG_FORMAT
            );
        } catch (IOException e) {
            e.printStackTrace();
        }


        SettingsPages.imageSettingsPage().setImageFormat("Jpeg");
        SettingsPages.imageSettingsPage().clickSaveButton();
        Actions.mainActions().wait(ConstantsPma2.ELEMENT_MICRO_TIMEOUT_SECONDS);
        Reporter.log("Checking image format after changing back to jpeg");
        Reporter.log("Href" + tileHref1);

        try {
            Assert.assertEquals(
                    SettingsActions.imageSettingsActions().getContentFormat(tileHref1), ConstantsPma2.IMAGE_JPEG_FORMAT
            );
        } catch (IOException e) {
            e.printStackTrace();
        }

        Reporter.log("Checking another tile format after changing back to jpeg");
        try {
            Assert.assertEquals(
                    SettingsActions.imageSettingsActions().getContentFormat(tileHref1), ConstantsPma2.IMAGE_JPEG_FORMAT
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(testName = "TC 17.2 Tile quality")
    public void checkDefaultTileSize() {
        Actions.mainActions().openMainPage();
        Actions.mainActions().clearSession();
        Pages.loginPage().waitForLoginForm();

        Actions.loginActions().login(ConstantsPma2.ADMIN_USER_NAME, ConstantsPma2.ADMIN_PASSWORD);
        Pages.homePage().waitHomePageLounching();

        Pages.leftSideBarPage().clickSettingsButton();
        Pages.leftSideBarPage().clickImageSettingsButton();
        SettingsPages.imageSettingsPage().waitImageSettingsPage();
        Reporter.log("Checking default tile size in the settings");
        Assert.assertEquals(SettingsPages.imageSettingsPage().getTileSize()
                , (ConstantsPma2.IMAGE_DEFAULT_SIZE));

        Reporter.log("Checking tile size");
        Assert.assertEquals(SettingsPages.imageSettingsPage().getTileSize(), ConstantsPma2.IMAGE_DEFAULT_SIZE);
        try {
            Assert.assertEquals(
                    SettingsActions.imageSettingsActions().getContentSize(tileHref1), ConstantsPma2.IMAGE_DEFAULT_SIZE
            );
        } catch (IOException e) {
            e.printStackTrace();
        }

        Reporter.log("Checking tile size after change to 1024");
        SettingsPages.imageSettingsPage().setTileSize("1024");
        SettingsPages.imageSettingsPage().clickSaveButton();
        Actions.mainActions().wait(ConstantsPma2.ELEMENT_MICRO_TIMEOUT_SECONDS);
        try {
            Assert.assertEquals(SettingsActions.imageSettingsActions().getContentSize(tileHref1), "1024*1024");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Reporter.log("Checking another tile size after change to 1024");
        try {
            Assert.assertEquals(SettingsActions.imageSettingsActions().getContentSize(tileHref2), "1024*1024");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Reporter.log("Checking tile size after change back to 512");
        SettingsPages.imageSettingsPage().setTileSize("512");
        SettingsPages.imageSettingsPage().clickSaveButton();
        Actions.mainActions().wait(ConstantsPma2.ELEMENT_MICRO_TIMEOUT_SECONDS);
        try {
            Assert.assertEquals(SettingsActions.imageSettingsActions().getContentSize(tileHref1),
                    "512*512");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Reporter.log("Checking another tile size after change back to 512");
        try {
            Assert.assertEquals(SettingsActions.imageSettingsActions().getContentSize(tileHref2), "512*512");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}



