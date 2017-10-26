package com.pathomation.backendapi;


import com.pathomation.base.BaseBackendTest;
import com.pathomation.util.ConstantsPma2;
import com.pathomation.util.data.URLConnection;
import com.pathomation.util.data.XMLparser;
import com.pathomation.util.reporter.Reporter;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;

public class TC15006_AdminReverseLookupRootDirectory  extends BaseBackendTest {

    XMLparser xmlDoc = new XMLparser();

    URLConnection connection = new URLConnection();


    URL adminAuthenticUrl;
    URL b_userAuthenticURL;

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

        b_userAuthenticURL = new URL(String.format(ConstantsPma2.AUTHENTICATE_API_URL,
                ConstantsPma2.USER1_B_NAME, ConstantsPma2.USER1_PASSWORD));
        System.setProperty("sessionIDb_user1", xmlDoc.parseXML(b_userAuthenticURL).getId());
    }


    @Test(testName = "15006.1 Test for an existing root-directory")
    public void existingRDirTest() throws IOException, ParserConfigurationException {

        Reporter.log("Getting session ID for   " + ConstantsPma2.ADMIN_USER_NAME + "/" + ConstantsPma2.ADMIN_PASSWORD);
        if (System.getProperty("sessionId").equals("")) {
            Assert.fail("Can't get session id from " + adminAuthenticUrl);
        }

        URL url = new URL(ConstantsPma2.ADMIN_XML_URL + ConstantsPma2.REVERSE_LOOKUP_ROOT_DIRECTORY + "?alias="
                + ConstantsPma2.ALIAS + "&SessionID=" + System.getProperty("sessionId"));
        Reporter.log("Open url " + url.toString());
        try {
            System.setProperty("uid", xmlDoc.getXmlTextByTagNmae(url, "string"));
        } catch (SAXException e) {
            e.printStackTrace();
            Assert.fail("Invalid or empty .xml file " + url);
        }
        Assert.assertTrue(
                System.getProperty("uid")
                        .trim()
                        .toUpperCase()
                        .contains((ConstantsPma2._SYS_REF_PATH).toUpperCase())
        );

    }


    @Test(testName = "15006.2 Test for a non-existing root-directory")
    public void nonExistingRDirTest() throws IOException, ParserConfigurationException {
        /**
         * Non-existing root directory alias
         *
         */
        String nonExistingRootDirectory = "_foo_bar";

        Reporter.log("Getting session ID for   " + ConstantsPma2.ADMIN_USER_NAME + "/" + ConstantsPma2.ADMIN_PASSWORD);
        if (System.getProperty("sessionId").equals("")) {
            Assert.fail("Can't get session id from " + adminAuthenticUrl);
        }

        URL url = new URL(ConstantsPma2.ADMIN_XML_URL + ConstantsPma2.REVERSE_LOOKUP_ROOT_DIRECTORY + "?alias="
                + nonExistingRootDirectory + "&SessionID=" + System.getProperty("sessionId"));
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


    @Test(testName = "15006.3 Test for an existing (but non-root-directory) path")
    public void existingButNotRDirTest() throws IOException, ParserConfigurationException {
        /**
         * contains path to existing non-root directory
         *
         */
        String existingNonRootDirectory = "_sys_ref/brightfield/3DHistech";

        Reporter.log("Getting session ID for   " + ConstantsPma2.ADMIN_USER_NAME + "/" + ConstantsPma2.ADMIN_PASSWORD);
        if (System.getProperty("sessionId").equals("")) {
            Assert.fail("Can't get session id from " + adminAuthenticUrl);
        }

        URL url = new URL(ConstantsPma2.ADMIN_XML_URL + ConstantsPma2.REVERSE_LOOKUP_ROOT_DIRECTORY + "?alias="
                + existingNonRootDirectory + "&SessionID=" + System.getProperty("sessionId"));
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


    @Test(testName = "15006.4: For an existing root-directory, requested by a non-administrative user")
    public void nonAdminPrivilegesRDirTest() throws IOException, ParserConfigurationException {

        Reporter.log("Getting session ID for   " + ConstantsPma2.USER1_B_NAME + "/" + ConstantsPma2.USER1_PASSWORD);
        if (System.getProperty("sessionIDb_user1").equals("")) {
            Assert.fail("Can't get session id from " + b_userAuthenticURL);
        }

        URL url = new URL(ConstantsPma2.ADMIN_XML_URL + ConstantsPma2.REVERSE_LOOKUP_ROOT_DIRECTORY + "?alias="
                + ConstantsPma2.ALIAS + "&SessionID=" + System.getProperty("sessionIDb_user1"));
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
}
