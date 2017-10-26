package com.pathomation.frontendui;


import com.pathomation.actions.Actions;
import com.pathomation.base.BaseTest;
import com.pathomation.pages.Pages;
import com.pathomation.pages.reports.ReportsPages;
import com.pathomation.util.ConstantsPma2;
import com.pathomation.util.ImageRGBComorator;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class TC200100_CheckTileCustomizationParameters extends BaseTest {
    private String tileHrefQuality90;
    private String tileHrefQuality10;
    private String tileHrefStandart;
    private String tileHrefJpg;
    private String tileHrefPng;
    private String fileImgStandart;
    private String fileImgQuality90;
    private String fileImgQuality10;
    private String fileImgJpg;
    private String fileImgPng;

    @Test(testName = "TC200100_CheckTileCustomizationParameters")
    public void checkDefaultTileFormat()
            throws IOException, ParserConfigurationException, SAXException {
        Actions.mainActions().openMainPage();
        Actions.mainActions().clearSession();
        Assert.assertTrue(Pages.loginPage().isLoginFormVisible());
        Assert.assertTrue(
                driver.getCurrentUrl().contains("pma.core."),
                "Expected page isn't opened.");
        Actions.loginActions().login(ConstantsPma2.ADMIN_USER_NAME, ConstantsPma2.ADMIN_PASSWORD);
        Actions.mainActions().wait(ConstantsPma2.ELEMENT_EXTRASMALL_TIMEOUT_SECONDS);


        Pages.leftSideBarPage().clickReportsButton();
        Pages.leftSideBarPage().clickActiveSessionButton();
        Actions.mainActions().wait(ConstantsPma2.ELEMENT_EXTRASMALL_TIMEOUT_SECONDS);
        System.setProperty("sessionID", ReportsPages.activeSessionsPage().getActiveSession());

        tileHrefQuality90 = ConstantsPma2.BASE_URL + "tile?sessionID=" + System.getProperty("sessionID")
                + ConstantsPma2.IMAGE_TILE_HREF_QUALITY_90;
        tileHrefQuality10 = ConstantsPma2.BASE_URL + "tile?sessionID=" + System.getProperty("sessionID")
                + ConstantsPma2.IMAGE_TILE_HREF_QUALITY_10;
        tileHrefStandart = ConstantsPma2.BASE_URL + "tile?sessionID=" + System.getProperty("sessionID")
                + ConstantsPma2.IMAGE_TILE_STANDART;
        tileHrefJpg = ConstantsPma2.BASE_URL + "tile?sessionID=" + System.getProperty("sessionID")
                + ConstantsPma2.IMAGE_TILE_HREF_FORMAT_JPG;
        tileHrefPng = ConstantsPma2.BASE_URL + "tile?sessionID=" + System.getProperty("sessionID")
                + ConstantsPma2.IMAGE_TILE_HREF_FORMAT_PNG;


        //Images in project folder
        fileImgQuality90 = ConstantsPma2.CONTENSUS_DATA_PATH + "tile_jpg_quality90.jpg";
        fileImgQuality10 = ConstantsPma2.CONTENSUS_DATA_PATH + "tile_jpg_quality10.jpg";
        fileImgStandart = ConstantsPma2.CONTENSUS_DATA_PATH + "tile_standard.jpg";
        fileImgJpg = ConstantsPma2.CONTENSUS_DATA_PATH + "tile_standard.jpg";
        //fileImgJpg =  "D:/tile_standard.jpg";

        fileImgPng = ConstantsPma2.CONTENSUS_DATA_PATH + "tile_standard.png";

        Assert.assertTrue(ImageRGBComorator
                .compare("Compare tile.quality = 90(" + tileHrefQuality90 +") and tile_jpg_quality90.jpg(" + fileImgQuality90 +")",
                        tileHrefQuality90, fileImgQuality90));




        Assert.assertTrue(ImageRGBComorator
                .compare("Compare tile.qualit = 10 (" + tileHrefQuality10 +")and tile_jpg_quality10.jpg(" + fileImgQuality10 +")",
                        tileHrefQuality10, fileImgQuality10));
        Assert.assertTrue(ImageRGBComorator
                .compare("Compare tile.format = png(" + tileHrefPng +") and tile_standard.png(" + fileImgPng +")",
                        tileHrefPng, fileImgPng));
        Assert.assertTrue(ImageRGBComorator
                .compare("Compare tile without options(" + tileHrefStandart +") and tile_standard.jpg(" + fileImgStandart +")",
                        tileHrefStandart, fileImgStandart));
        Assert.assertTrue(ImageRGBComorator
                .compare("Compare tile without options(" + tileHrefJpg +") and tile_standard.jpg(" + fileImgJpg +")",
                        tileHrefJpg, fileImgJpg));


    }
}