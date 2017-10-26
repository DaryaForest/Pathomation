package com.pathomation.backendapi;

import com.pathomation.base.BaseBackendTest;
import com.pathomation.util.ConstantsPma2;
import com.pathomation.util.data.URLConnection;
import com.pathomation.util.data.XMLparser;
import com.pathomation.util.reporter.Reporter;
import org.apache.commons.io.FileUtils;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.net.URL;


public class TC10010_GetUIDCallTest  extends BaseBackendTest {

    private XMLparser xmlDoc = new XMLparser();

    URLConnection connection = new URLConnection();

    /*
    * contains true if request with incorrect data returns valid data
    * and false if page hasn't content-type and doesn't contains valid data
    * */
    boolean isRequestHasValidData = true;

    String filePath = "brightfield/3DHistech";
    String filePath10010_4_1 = "brightfield%2Fgeneric_jpeg%2Fjpg03_uid_test.jpg";
    String filePath10010_4_2 = "brightfield%2Fjpg03_uid_test.jpg";

    URL authenticUrl;


    @BeforeClass
    public void setUp() throws IOException, ParserConfigurationException, SAXException {
        authenticUrl = new URL(String.format(ConstantsPma2.AUTHENTICATE_API_URL,
                ConstantsPma2.USER1_B_NAME, ConstantsPma2.USER1_PASSWORD));
        System.setProperty("sessionIDb_user1", xmlDoc.parseXML(authenticUrl).getId());
    }

    @Test(testName = "TC 10010.1: Obtain the UID for a slide", priority = 1)
    public void obtainEheUIDForASlide() throws IOException, ParserConfigurationException {
        Reporter.log("Getting session ID for   " + ConstantsPma2.USER1_B_NAME + "/" + ConstantsPma2.USER1_PASSWORD);
        if (System.getProperty("sessionIDb_user1").equals("")) {
            Assert.fail("Can't get session id from " + authenticUrl);
        }

        URL url = new URL(ConstantsPma2.API_XML_URL + ConstantsPma2.GET_UID_URL + "?sessionID="
                + System.getProperty("sessionIDb_user1") + "&path=" + ConstantsPma2.ALIAS + "/" + filePath.toUpperCase() + "/CMU-1.mrxs");
        Reporter.log("Read page " + url);
        try {
            System.setProperty("uid", xmlDoc.getXmlTextByTagNmae(url, "string"));
            Reporter.log("Check xml has 1 item");
            Assert.assertEquals(xmlDoc.getCountNodeElements(url), 1);
        } catch (SAXException e) {
            e.printStackTrace();
            Assert.fail("Invalid or empty .xml file " + url);

        }
    }

    @Test(testName = "TC 10010.2: Case-sensitivity testing", priority = 2)
    public void case_sensitivityTestingUpper() throws IOException, ParserConfigurationException {
        Reporter.log("Uppercase(folder) testing");
        Reporter.log("Getting session ID for   " + ConstantsPma2.USER1_B_NAME + "/" + ConstantsPma2.USER1_PASSWORD);
        if (System.getProperty("sessionIDb_user1").equals("")) {
            Assert.fail("Can't get session id from " + authenticUrl);
        }

        Reporter.log("Chek GetUID returns the same UID irrespective of the casing of the directory name. Uppercase");
        URL url = new URL(ConstantsPma2.API_XML_URL + ConstantsPma2.GET_UID_URL + "?sessionID="
                + System.getProperty("sessionIDb_user1") + "&path=" + ConstantsPma2.ALIAS + "/" + filePath.toUpperCase() + "/CMU-1.mrxs");
        Reporter.log("Read page " + url);
        String uid = null;
        try {
            uid = xmlDoc.getXmlTextByTagNmae(url, "string");
        } catch (SAXException e) {
            e.printStackTrace();
            Assert.fail("Invalid or empty .xml file " + url);
        }
        Reporter.log(uid);

        Assert.assertEquals(System.getProperty("uid"), uid);

        //--------------------------------------------------------------------------------------------------------------
        Reporter.log("Lowercase(folder) testing ");
        Reporter.log("Chek GetUID returns the same UID irrespective of the casing of the directory name. LowerCase");
        url = new URL(ConstantsPma2.API_XML_URL + ConstantsPma2.GET_UID_URL + "?sessionID="
                + System.getProperty("sessionIDb_user1") + "&path=" + ConstantsPma2.ALIAS + "/" +
                filePath.toLowerCase() + "/CMU-1.mrxs"
        );
        Reporter.log("Read page " + url);
        uid = null;
        try {
            uid = xmlDoc.getXmlTextByTagNmae(url, "string");
        } catch (SAXException e) {
            e.printStackTrace();
            Assert.fail("Invalid or empty .xml file " + url);
        }
        Assert.assertEquals(System.getProperty("uid"), uid);

        //--------------------------------------------------------------------------------------------------------------
        Reporter.log("Uppercase(file) testing ");
        filePath = "CMU-1.mrxs";

        Reporter.log("Chek GetUID returns the same UID irrespective of the casing of the directory name. Uppercase");
        url = new URL(ConstantsPma2.API_XML_URL + ConstantsPma2.GET_UID_URL + "?sessionID=" +
                System.getProperty("sessionID") + "&path=" + ConstantsPma2.ALIAS +
                "/brightfield/3DHistech/" + filePath.toUpperCase()
        );
        Reporter.log("Read page " + url);
        uid = null;
        try {
            uid = xmlDoc.getXmlTextByTagNmae(url, "string");
        } catch (SAXException e) {
            e.printStackTrace();
            Assert.fail("Invalid or empty .xml file " + url);
        }
        System.setProperty("uid", uid);
        Assert.assertEquals(System.getProperty("uid"), uid);
        //--------------------------------------------------------------------------------------------------------------
        Reporter.log("Lowercase(file) testing ");

        filePath = "CMU-1.mrxs";
        url = new URL(ConstantsPma2.API_XML_URL + ConstantsPma2.GET_UID_URL + "?sessionID=" +
                System.getProperty("sessionID") + "&path=" + ConstantsPma2.ALIAS +
                "/brightfield/3DHistech/" + filePath.toLowerCase()
        );
        Reporter.log("Session ID for   " + ConstantsPma2.USER1_B_NAME + "/" + ConstantsPma2.USER1_PASSWORD);
        Reporter.log("Read page " + url);
        uid = null;
        try {
            uid = xmlDoc.getXmlTextByTagNmae(url, "string");
        } catch (SAXException e) {
            e.printStackTrace();
            Assert.fail("Invalid or empty .xml file " + url);

        }
        Assert.assertEquals(System.getProperty("uid"), uid);
    }


    @Test(testName = "TC 10010.3: Forward and backward slash testing", priority = 2)
    public void tc10010_3_ForwardAndBackwardSlashTesting() throws
            IOException, ParserConfigurationException {

        Reporter.log("Getting session ID for   " + ConstantsPma2.USER1_B_NAME + "/" + ConstantsPma2.USER1_PASSWORD);
        if (System.getProperty("sessionIDb_user1").equals("")) {
            Assert.fail("Can't get session id from " + authenticUrl);
        }

        URL urlWithForwardSlesh = new URL(System.getProperty("baseurl") + "/api/xml/GetUID?sessionID=" +
                System.getProperty("sessionIDb_user1") + "&path=_sys_ref%2Fbrightfield%2F3DHISTECH%2FCMU-1.mrxs"
        );
        URL urlWithBackwardSlesh = new URL(System.getProperty("baseurl") + "/api/xml/GetUID?sessionID=" +
                System.getProperty("sessionIDb_user1") + "&path=_sys_ref%5Cbrightfield%5C3DHISTECH%5CCMU-1.mrxs"
        );
        URL urlWithBothSlashes = new URL(System.getProperty("baseurl") + "/api/xml/GetUID?sessionID=" +
                System.getProperty("sessionIDb_user1") + "&path=_sys_ref%5Cbrightfield%2F3DHISTECH%5CCMU-1.mrxs"
        );

        Reporter.log("Expected " + urlWithForwardSlesh.toString() + " is running");
        String forwardSlashRequestResult = null;
        try {
            forwardSlashRequestResult = xmlDoc.getXmlTextByTagNmae(urlWithForwardSlesh, "string");

            Reporter.log("Expected " + urlWithBackwardSlesh.toString() + " is running");
            String urlWithBackwardSleshResult = xmlDoc.getXmlTextByTagNmae(urlWithBackwardSlesh, "string");
            Reporter.log("Expected " + urlWithBothSlashes.toString() + " is running");
            String urlWithBothSlashesResult = xmlDoc.getXmlTextByTagNmae(urlWithBothSlashes, "string");

            Reporter.log("Compare returns from links with different tipe of slashes");
            Assert.assertEquals(forwardSlashRequestResult, urlWithBackwardSleshResult);
            Assert.assertEquals(urlWithBackwardSleshResult, urlWithBothSlashesResult);
        } catch (SAXException e) {
            e.printStackTrace();
            Assert.fail("Invalid or empty .xml file ");
        }
    }


    @Test(testName = "TC 10010.4: Interaction with relocated files")
    public void interactionWithRelocatedFiles() throws IOException, ParserConfigurationException {

        Reporter.log("Getting session ID for   " + ConstantsPma2.USER1_B_NAME + "/" + ConstantsPma2.USER1_PASSWORD);
        if (System.getProperty("sessionIDb_user1").equals("")) {
            Assert.fail("Can't get session id from " + authenticUrl);
        }

        File source = new File("D:\\pathomation\\wsi_data\\reference\\brightfield\\Generic_Jpeg\\jpg03_uid_test.jpg");
        File dest = new File("D:\\pathomation\\wsi_data\\reference\\brightfield\\jpg03_uid_test.jpg");

        Reporter.log("Invoke GetUID for  /_sys_ref/brightfield/generic_jpeg/jpg03_uid_test.jpg");
        URL url = new URL(ConstantsPma2.API_XML_URL + ConstantsPma2.GET_UID_URL + "?sessionID="
                + System.getProperty("sessionIDb_user1") + "&path=" + ConstantsPma2.ALIAS + "/" + filePath10010_4_1);
        Reporter.log("Read page " + url);
        Reporter.log("Session ID for   " + ConstantsPma2.USER1_B_NAME + "/" + ConstantsPma2.USER1_PASSWORD);
        String uidBeforeMove = null;
        try {
            uidBeforeMove = xmlDoc.getXmlTextByTagNmae(url, "string");

            Reporter.log("Check xml has 1 item");
            Assert.assertEquals(xmlDoc.getCountNodeElements(url), 1);
        } catch (SAXException e) {
            e.printStackTrace();
            Assert.fail("Invalid or empty .xml file " + url);
        }
        url = new URL(ConstantsPma2.API_XML_URL + ConstantsPma2.GET_UID_URL + "?sessionID="
                + System.getProperty("sessionIDb_user1") + "&path=" + ConstantsPma2.ALIAS + "/" + filePath10010_4_2);
        Reporter.log("Read page " + url);
        try {
            connection.getContent(url);
        } catch (IOException e) {
            Reporter.log("Page hasn't content-type; Request throws exception: " + e.getMessage());
            isRequestHasValidData = false;
        }
        Assert.assertFalse(isRequestHasValidData, "The page contains valid data");
        //TODO must have more understandable error when handle request with incorrect data
        Reporter.log("Copy file from " + source + " to " + dest);
        FileUtils.copyFile(source, dest);
        if (!dest.exists()) {
            Reporter.log("<b>Error copy file </b> from " + source + " to " + dest);
            throw new SkipException("Test Check_Checkbox Is Skipped");
        }
        Reporter.log("Check xml has 1 item");
        String uid2AfterCopy = null;
        try {
            uid2AfterCopy = xmlDoc.getXmlTextByTagNmae(url, "string");
        } catch (SAXException e) {
            e.printStackTrace();
            Assert.fail("Invalid or empty .xml file " + url);
        }
        Assert.assertNotEquals(uidBeforeMove, uid2AfterCopy,
                "UID must be different for one file in different location");


        FileUtils.touch(dest);
        File fileToDelete = new File(dest.getAbsolutePath());
        Reporter.log("Delete file " + dest);
        try {
            boolean success = FileUtils.deleteQuietly(fileToDelete);
            Assert.assertTrue(success, "File is successfully deleted");

        } catch (NullPointerException e) {
            Assert.fail("Error delete file ");
        }
    /*    Assert.assertTrue(!url.openConnection().getContentType().contains("application/xml"),
                "Url shouldn't contains valid xml");*/
        try {
            connection.getContent(url);
        } catch (IOException e) {
            Reporter.log("Page hasn't content-type; Request throws exception: " + e.getMessage());
            isRequestHasValidData = false;
        }
        Assert.assertFalse(isRequestHasValidData, "The page contains valid data");
        //TODO must have more understandable error when handle request with incorrect data

        url = new URL(ConstantsPma2.API_XML_URL + ConstantsPma2.GET_UID_URL + "?sessionID="
                + System.getProperty("sessionIDb_user1") + "&path=" + ConstantsPma2.ALIAS + "/" + filePath10010_4_1);
        Reporter.log("Read page " + url);
        try {
            String uid1 = xmlDoc.getXmlTextByTagNmae(url, "string");

            Reporter.log("Check xml has 1 item");
            Assert.assertEquals(xmlDoc.getCountNodeElements(url), 1);
        } catch (SAXException e) {
            e.printStackTrace();
            Assert.fail("Invalid or empty .xml file " + url);
        }
    }
}