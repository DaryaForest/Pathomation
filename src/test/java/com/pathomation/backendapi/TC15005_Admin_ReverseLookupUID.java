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


public class TC15005_Admin_ReverseLookupUID  extends BaseBackendTest {

    XMLparser xmlDoc = new XMLparser();

    URLConnection connection = new URLConnection();

    URL adminAuthenticUrl;

    /*
    * contains true if request with incorrect data returns valid data
    * and false if page hasn't content-type and doesn't contains valid data
    * */
    boolean isRequestHasValidData = true;

    @BeforeClass
    public void setUp() throws IOException, ParserConfigurationException, SAXException {
        adminAuthenticUrl = new URL(String.format(ConstantsPma2.ADMIN_AUTHENTICATE_API_URL,
                ConstantsPma2.ADMIN_USER_NAME, ConstantsPma2.ADMIN_PASSWORD));
        System.setProperty("sessionId", xmlDoc.parseXML(adminAuthenticUrl).getId());
    }

    @Test(testName = "1505.1 Test for an existing UID")
    public void existingUIDTest() throws IOException, ParserConfigurationException {
       /*
     * contains path to file consist of
     * alias(_sys_ref = D:\pathomation\wsi_data\reference) and path to file after _sys_ref
     */
        String filePath = ConstantsPma2.ALIAS + "/brightfield/3DHistech/CMU-1.mrxs";

     /*
     * Contains UID from xml page, value by definition have 6 characters
     */
        String uid = null;
        /*
     * Contains response from xml page after invoke ReverseLookupUID
     */
        String reverseLookupResponse = null;


        Reporter.log("Getting session ID for   " + ConstantsPma2.ADMIN_USER_NAME + "/" + ConstantsPma2.ADMIN_PASSWORD);
        if (System.getProperty("sessionId").equals("")) {
            Assert.fail("Can't get session id from " + adminAuthenticUrl);
        }

        URL url = new URL(ConstantsPma2.API_XML_URL + ConstantsPma2.GET_UID_URL + "?sessionID="
                + System.getProperty("sessionId") + "&path=" + filePath
        );
        Reporter.log("Open url " + url.toString());
        try {
            uid = xmlDoc.getXmlTextByTagNmae(url, "string");
        } catch (SAXException e) {
            e.printStackTrace();
            Assert.fail("Invalid or empty .xml file " + url);
        }

        Assert.assertTrue(
                uid != null
                        && uid.length() >= 6,
                "UID doesn't equal 'null' and contains of more than 6 symbols"
        );
        url = new URL(ConstantsPma2.ADMIN_XML_URL + ConstantsPma2.REVERSE_LOOKUP_ID + "?UID="
                + uid + "&SessionID=" + System.getProperty("sessionId"));
        Reporter.log("Open url " + url.toString());

        try {
            reverseLookupResponse = xmlDoc.getXmlTextByTagNmae(url, "string");
        } catch (SAXException e) {
            e.printStackTrace();
            Assert.fail("Invalid or empty .xml file " + url);

        }
        Assert.assertTrue(reverseLookupResponse.equalsIgnoreCase(filePath),
                "Expected the original path to the referenced slide(see filePath) is returned");

    }

    @Test(testName = "1505.2 Test for an non-existing UID")
    public void nonExistingUIDTest() throws IOException, ParserConfigurationException {
        /**
         * contains path to file consist of
         * alias(_sys_ref = D:\pathomation\wsi_data\reference) and path to file after _sys_ref
         */
        String filePath = ConstantsPma2.ALIAS + "%2Fbrightfield%2F3DHistech%2FCMU-1.mrxs";

        /*
     * Incorrect uid for uid request failed;
     * TheID is guaranteed not to exist, as UIDs by definition have 6 characters
     */
        final String incorrectUID = "12345";

        Reporter.log("Getting session ID for   " + ConstantsPma2.ADMIN_USER_NAME + "/" + ConstantsPma2.ADMIN_PASSWORD);
        if (System.getProperty("sessionId").equals("")) {
            Assert.fail("Can't get session id from " + adminAuthenticUrl);
        }
        URL url = new URL(ConstantsPma2.ADMIN_XML_URL + ConstantsPma2.REVERSE_LOOKUP_ID + "?UID="
                + incorrectUID + "&SessionID=" + System.getProperty("sessionId"));
        Reporter.log("Open url " + url.toString());

        Reporter.log("Expected the requested page has no type, doesn't contains right xml");
        try {
            connection.getContent(url);
        } catch (IOException e) {
            Reporter.log("Page hasn't content-type; Request throws exception: " + e.getMessage());
            isRequestHasValidData = false;
        }
        Assert.assertFalse(isRequestHasValidData, "The page contains valid data");
        //TODO must have more understandable error when handle request with incorrect data


    }

    @Test(testName = "1505.3 Test for an existing file")
    public void existingFileTest() throws IOException, ParserConfigurationException {

        /**
         * contains path to file consist of
         * alias(_sys_ref = D:\pathomation\wsi_data\reference) and path to file after _sys_ref
         */
        String filePath = "%2Fbrightfield%2F3DHistech%2FCMU-1.mrxs";

        Reporter.log("Getting session ID for   " + ConstantsPma2.ADMIN_USER_NAME + "/" + ConstantsPma2.ADMIN_PASSWORD);
        if (System.getProperty("sessionId").equals("")) {
            Assert.fail("Can't get session id from " + adminAuthenticUrl);
        }

        URL url = new URL(ConstantsPma2.ADMIN_XML_URL + ConstantsPma2.REVERSE_LOOKUP_ID + "?sessionID="
                + System.getProperty("sessionId") + "&UID=" + ConstantsPma2.ALIAS + filePath);
        Reporter.log("Open url " + url.toString());
        Reporter.log("Expected the requested page has no type, doesn't contains right xml");
        try {
            connection.getContent(url);
        } catch (IOException e) {
            Reporter.log("Page hasn't content-type; Request throws exception: " + e.getMessage());
            isRequestHasValidData = false;
        }
        Assert.assertFalse(isRequestHasValidData, "The page contains valid data");
        //TODO must have more understandable error when handle request with incorrect data

    }

    @Test(testName = "1505.4 Test for an existing UID, requested by a non-administrative user")
    public void existingUID_NonAdminUserTest() throws IOException, ParserConfigurationException {
        /**
         * contains path to file consist of
         * alias(_sys_ref = D:\pathomation\wsi_data\reference) and path to file after _sys_ref
         */
        String filePath = "%2Fbrightfield%2F3DHistech%2FCMU-1.mrxs";


        /**
         * Contains UID from xml page, value by definition have 6 characters
         */
        String uid = null;

        Reporter.log("Getting session ID for   " + ConstantsPma2.ADMIN_USER_NAME + "/" + ConstantsPma2.ADMIN_PASSWORD);
        if (System.getProperty("sessionIDb_user1").equals("")) {
            Assert.fail("Can't get session id from " + adminAuthenticUrl);
        }

        URL url = new URL(ConstantsPma2.API_XML_URL + ConstantsPma2.GET_UID_URL + "?sessionID="
                + System.getProperty("sessionIDb_user1") + "&path=" + ConstantsPma2.ALIAS + filePath
        );
        Reporter.log("Open url " + url.toString());
        try {
            uid = xmlDoc.getXmlTextByTagNmae(url, "string");

        } catch (SAXException e) {
            e.printStackTrace();
            Assert.fail("Invalid or empty .xml file " + url);
        }

        Assert.assertTrue(
                uid != null
                        && uid.length() >= 6,
                "UID doesn't equal 'null' and contains of more than 6 symbols"
        );
        url = new URL(ConstantsPma2.ADMIN_XML_URL + ConstantsPma2.REVERSE_LOOKUP_ID + "?sessionID="
                + System.getProperty("sessionIDb_user1") + "&UID=" + uid);
        Reporter.log("Open url " + url.toString());
        Reporter.log("Expected the requested page has no type, doesn't contains right xml");
        try {
            connection.getContent(url);
        } catch (IOException e) {
            Reporter.log("Page hasn't content-type; Request throws exception: " + e.getMessage());
            isRequestHasValidData = false;
        }
        Assert.assertFalse(isRequestHasValidData, "The page contains valid data");
        //TODO must have more understandable error when handle request with incorrect data
    }

    @Test(testName = "TC 15005.5: Interaction with relocated files")
    public void interactionWithRelocatedFiles() throws IOException, ParserConfigurationException {

        String filePath1 = "brightfield%2Fgeneric_jpeg%2Fjpg03_uid_test.jpg";

        String filePath2 = "brightfield/jpg03_uid_test.jpg";

        /**
         * contains path to file consist of
         * alias(_sys_ref = D:\pathomation\wsi_data\reference) and path to file after _sys_ref
         */
        String filePath = "%2Fbrightfield%2F3DHistech%2FCMU-1.mrxs";

        /**
         * Contains UID from xml page, value by definition have 6 characters
         */
        String uid;

        /**
         * Contains response from xml page after invoke ReverseLookupUID
         */
        String reverseLookupResponse;


        Reporter.log("Getting session ID for   " + ConstantsPma2.ADMIN_USER_NAME + "/" + ConstantsPma2.ADMIN_PASSWORD);
        if (System.getProperty("sessionId").equals("")) {
            Assert.fail("Can't get session id from " + adminAuthenticUrl);
        }

        File source = new File(
                "D:/pathomation/wsi_data/reference/brightfield/Generic_Jpeg/jpg03_uid_test.jpg");

        File dest = new File(
                "D:/pathomation/wsi_data/reference/brightfield/jpg03_uid_test.jpg");

        Reporter.log("Invoke GetUID for  /_sys_ref/brightfield/generic_jpeg/jpg03_uid_test.jpg");
        URL url = new URL(ConstantsPma2.API_XML_URL + ConstantsPma2.GET_UID_URL + "?sessionID="
                + System.getProperty("sessionId") + "&path=" + ConstantsPma2.ALIAS + "/" + filePath1);
        Reporter.log("Read page " + url);
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
                + System.getProperty("sessionId") + "&path=" + ConstantsPma2.ALIAS + "/" + filePath2);
        Reporter.log("Read page " + url);
        System.out.println(url.openConnection().getContentType());
        //Assert.assertTrue(!url.openConnection().getContentType().contains("application/xml"));
        Reporter.log("Copy file from " + source + " to " + dest);
        FileUtils.copyFile(source, dest);
        if (!dest.exists()) {
            Assert.fail("Error copy file from" + source + " to " + dest);
        }

        String uid2AfterCopy = null;
        try {
            uid2AfterCopy = xmlDoc.getXmlTextByTagNmae(url, "string");
        } catch (SAXException e) {
            e.printStackTrace();
            Assert.fail("Invalid or empty .xml file " + url);
        }
        Assert.assertNotEquals(uidBeforeMove, uid2AfterCopy,
                "UID must be different for one file in different location");


        url = new URL(ConstantsPma2.ADMIN_XML_URL + ConstantsPma2.REVERSE_LOOKUP_ID + "?UID="
                + uid2AfterCopy + "&SessionID=" + System.getProperty("sessionId"));
        Reporter.log("Open url " + url.toString());
        try {
            reverseLookupResponse = xmlDoc.getXmlTextByTagNmae(url, "string");
            Assert.assertTrue(reverseLookupResponse.equalsIgnoreCase(ConstantsPma2.ALIAS + "/" + filePath2),
                    "Expected the original path to the referenced slide(see filePath) is returned");
        } catch (SAXException e) {
            e.printStackTrace();
            Assert.fail("Invalid or empty .xml file " + url);
        }


        FileUtils.touch(dest);
        File fileToDelete = new File(dest.getAbsolutePath());
        Reporter.log("Delete file " + dest);
        try {
            boolean success = FileUtils.deleteQuietly(fileToDelete);
            Assert.assertTrue(success, "File is successfully deleted");

        } catch (NullPointerException e) {
            Assert.fail("Error delete file");
        }
        try {
            connection.getContent(url);
        } catch (IOException e) {
            Reporter.log("Page hasn't content-type; Request throws exception: " + e.getMessage());
            isRequestHasValidData = false;
        }
        Assert.assertFalse(isRequestHasValidData, "The page contains valid data");
        //TODO must have more understandable error when handle request with incorrect data
/*        Assert.assertTrue(!url.openConnection().getContentType().contains("application/xml"),
                "Url shouldn't contains valid xml");*/
        url = new URL(ConstantsPma2.API_XML_URL + ConstantsPma2.GET_UID_URL + "?sessionID="
                + System.getProperty("sessionId") + "&path=" + ConstantsPma2.ALIAS + "/" + filePath2);
        Reporter.log("Read page " + url);
        try {
            connection.getContent(url);
        } catch (IOException e) {
            Reporter.log("Page hasn't content-type; Request throws exception: " + e.getMessage());
            isRequestHasValidData = false;
        }
        Assert.assertFalse(isRequestHasValidData, "The page contains valid data");
        //TODO must have more understandable error when handle request with incorrect data

    }


}
