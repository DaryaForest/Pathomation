package com.pathomation.backendapi;

import com.pathomation.base.BaseBackendTest;
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
import java.util.ArrayList;
import java.util.List;

public class TC10007_GetRootDirectoriesCallTest  extends BaseBackendTest {

    XMLparser xmlDoc = new XMLparser();
    URL authenticUrl;

    @BeforeClass
    public void setUp() throws IOException, ParserConfigurationException, SAXException {
        authenticUrl = new URL(String.format(ConstantsPma2.AUTHENTICATE_API_URL,
                ConstantsPma2.USER1_B_NAME, ConstantsPma2.USER1_PASSWORD));
        System.setProperty("sessionIDb_user1", xmlDoc.parseXML(authenticUrl).getId());

    }

    @Test(testName = "TC10007 Check response api authenticate page with correct data with ID")
    public void checkResponseApiAuthenticatePageWithCorrectDataWithID() throws
            ParserConfigurationException, IOException {

        Reporter.log("Getting session ID for   " + ConstantsPma2.USER1_B_NAME + "/" + ConstantsPma2.USER1_PASSWORD);
        if (System.getProperty("sessionIDb_user1").equals("")) {
            Assert.fail("Can't get session id from " + authenticUrl);
        }

        Reporter.log("<B>Check 'GetRootDirectories' request WITH id</b>");
        String urlGetRootDirectoriesWithSessionId = ConstantsPma2.API_XML_URL +
                ConstantsPma2.GET_ROOT_DIRECTORY_URL +
                "?sessionID=" + System.getProperty("sessionIDb_user1");
        URL url = new URL(urlGetRootDirectoriesWithSessionId);

        Reporter.log("Open url " + urlGetRootDirectoriesWithSessionId + "\n");
        //Assert.assertTrue(xmlDoc.getCountNodeElements(url) == 1);
        List<String> listOfDerectories = new ArrayList<>();
        try {
            listOfDerectories = xmlDoc.getListOfElementsByTag(url, listOfDerectories, "string");
        } catch (SAXException e) {
            e.printStackTrace();
            Assert.fail("Invalid or empty .xml file");
        }
        Reporter.log("Check that url provide a non-empty list with directories");
        Assert.assertTrue(!listOfDerectories.isEmpty());

        Reporter.log("<B>Check 'GetRootDirectories' request WITHOUT id</b>");
        String urlStr = ConstantsPma2.API_XML_URL + ConstantsPma2.GET_ROOT_DIRECTORY_URL;
        url = new URL(urlStr);
        Reporter.log("Open url " + urlStr);
        try {
            Assert.assertTrue(!url.openConnection().getContentType().contains("application/xml"),
                    "The request doesn't return valid xml ");
        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail("Can't open url connection");
        }
    }
}



