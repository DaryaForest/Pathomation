package com.pathomation.backendapi;


import com.pathomation.util.ConstantsPma2;
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
import java.util.ArrayList;
import java.util.List;

public class TC10009_GetFilesCallTest {

    XMLparser xmlDoc = new XMLparser();
    URL authenticUrl;
    URL url;

    @BeforeClass
    public void setUp() throws IOException, ParserConfigurationException, SAXException {
        authenticUrl = new URL(String.format(ConstantsPma2.AUTHENTICATE_API_URL,
                ConstantsPma2.USER1_B_NAME, ConstantsPma2.USER1_PASSWORD));
        System.setProperty("sessionIDb_user1", xmlDoc.parseXML(authenticUrl).getId());
    }


    @Test(testName = "TC 10009.1: non-empty fileset retrieval")
    public void tc10009_1_GetFilesNonEmptyFileSetRetrieval() throws IOException, ParserConfigurationException {
        //Reporter.log("Check that GetRootDirectories and GetDirectories return the same list of folders");
        Reporter.log("Get sessionId for   " + ConstantsPma2.USER1_B_NAME + "/" + ConstantsPma2.USER1_PASSWORD);
        if (System.getProperty("sessionIDb_user1").equals("")) {
            Assert.fail("Can't get session id from " + authenticUrl);
        }
        try {
            //2
            Reporter.log("'GetFiles' request <u>without</u> slash at the end");
            String folderName = "brightfield%2Faperio";
            String urlStr = ConstantsPma2.API_XML_URL + ConstantsPma2.GET_FILES_DIRECTORY_URL + "?sessionId="
                    + System.getProperty("sessionIDb_user1") + "&path=" + ConstantsPma2.ALIAS + "%2F" + folderName;
            Reporter.log("Open url " + urlStr + "\n");
            url = new URL(urlStr);

            Assert.assertTrue(
                    xmlDoc.isTagPresentAndHasChildNodes(url, "ArrayOfstring"),
                    "xml contains non-empty list of files "
            );
            List<String> listOfFilesForwardsSlesh = new ArrayList<>();
            listOfFilesForwardsSlesh = xmlDoc.getListOfElementsByTag(url, listOfFilesForwardsSlesh, "string");
            Assert.assertTrue(listOfFilesForwardsSlesh.size() > 0);

            //3
            Reporter.log("'GetFiles' request <u>with</u> slash at the end");
            urlStr = ConstantsPma2.API_XML_URL + ConstantsPma2.GET_FILES_DIRECTORY_URL + "?sessionId="
                    + System.getProperty("sessionIDb_user1") + "&path=" + ConstantsPma2.ALIAS + "%2F" + folderName + "%2F";
            url = new URL(urlStr);
            Assert.assertTrue(
                    xmlDoc.isTagPresentAndHasChildNodes(url, "ArrayOfstring")
            );
            List<String> listOfFilesForwardsSleshAtTheEnd = new ArrayList<>();
            listOfFilesForwardsSleshAtTheEnd = xmlDoc.getListOfElementsByTag(url, listOfFilesForwardsSlesh, "string");
            Assert.assertTrue(listOfFilesForwardsSlesh.size() > 0);

            Reporter.log("Expected url with and without a trailing slash return the same result");
            Assert.assertTrue(listOfFilesForwardsSleshAtTheEnd.equals(listOfFilesForwardsSlesh));
            //4
            ///api/xml/GetFiles?path=_sys_ref%5Cbrightfield%5Caperio
            Reporter.log("'GetFiles' request with <u>backward slash  </u>");
            folderName = "brightfield%5Caperio";
            urlStr = ConstantsPma2.API_XML_URL + ConstantsPma2.GET_FILES_DIRECTORY_URL + "?sessionId="
                    + System.getProperty("sessionIDb_user1") + "&path=" + ConstantsPma2.ALIAS + "%5C" + folderName + "%5C";
            url = new URL(urlStr);
            Assert.assertTrue(
                    xmlDoc.isTagPresentAndHasChildNodes(url, "ArrayOfstring")
            );
            List<String> listOfFilesWithBackwardForwardSlesh = new ArrayList<>();
            listOfFilesWithBackwardForwardSlesh = xmlDoc.getListOfElementsByTag(url, listOfFilesForwardsSlesh, "string");
            Assert.assertTrue(listOfFilesForwardsSlesh.size() > 0);
            Reporter.log("Expected urls with backward and forward slashes return the same result");
            Assert.assertTrue(listOfFilesWithBackwardForwardSlesh.equals(listOfFilesForwardsSlesh));
        } catch (SAXException e) {
            e.printStackTrace();
            Assert.fail("Invalid or empty .xml file " + url);
        }
    }

    @Test(testName = "TC 10009.2: empty fileset retrieval")
    public void tc10009_2_GetFilesEmptyFileSetRetrieval() throws IOException, ParserConfigurationException {


        Reporter.log("Getting session ID for   " + ConstantsPma2.USER1_B_NAME + "/" + ConstantsPma2.USER1_PASSWORD);
        if (System.getProperty("sessionIDb_user1").equals("")) {
            Assert.fail("Can't get session id from " + authenticUrl);
        }
        //2
        try {
            String folderName = "_sys_ref";
            Reporter.log("'GetFiles' request with <u>_sys_ref</u>");
            String urlStr = ConstantsPma2.API_XML_URL + ConstantsPma2.GET_FILES_DIRECTORY_URL + "?sessionId="
                    + System.getProperty("sessionIDb_user1") + "&path=" + ConstantsPma2.ALIAS + "%2F" + folderName;
            Reporter.log("Open url " + urlStr + "\n");
            url = new URL(urlStr);

            Assert.assertTrue(xmlDoc.getCountNodeElements(url) == 1, "Expected empty list of entries");
            //3
            folderName = "does_not_exist";
            Reporter.log("'GetFiles' request with <u>does_not_exist</u>");
            urlStr = ConstantsPma2.API_XML_URL + ConstantsPma2.GET_FILES_DIRECTORY_URL + "?sessionId="
                    + System.getProperty("sessionIDb_user1") + "&path=" + folderName;

            Reporter.log("Open url " + urlStr + "\n");
            url = new URL(urlStr);
            Assert.assertTrue(xmlDoc.getCountNodeElements(url) == 1, "Expected empty list of entries");
            //4
            folderName = "sub_directory_does_not_exist";
            Reporter.log("'GetFiles' request with <u>sub-directory_does_not_exist in the existing root-directory _sys_ref</u>");
            urlStr = ConstantsPma2.API_XML_URL + ConstantsPma2.GET_FILES_DIRECTORY_URL + "?sessionId="
                    + System.getProperty("sessionIDb_user1") + "&path=" + ConstantsPma2.ALIAS + "%2F" + folderName;

            Reporter.log("Open url " + urlStr + "\n");
            url = new URL(urlStr);
            Assert.assertTrue(xmlDoc.getCountNodeElements(url) == 1, "Expected empty list of entries");
        } catch (SAXException e) {
            e.printStackTrace();
            Assert.fail("Invalid or empty .xml file " + url);
        }
    }


}
