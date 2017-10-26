package com.pathomation.backendapi;


import com.pathomation.base.BaseBackendTest;
import com.pathomation.util.ConstantsPma2;
import com.pathomation.util.data.XMLparser;
import com.pathomation.util.data.URLConnection;

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

public class TC10008_GetDirectoriesCallTest  extends BaseBackendTest {

    XMLparser xmlDoc = new XMLparser();
    URL authenticUrl;
    URLConnection connection = new URLConnection();

    @BeforeClass
    public void setUp() throws IOException, ParserConfigurationException, SAXException {
        authenticUrl = new URL(String.format(ConstantsPma2.AUTHENTICATE_API_URL,
                ConstantsPma2.USER1_B_NAME, ConstantsPma2.USER1_PASSWORD));
        System.setProperty("sessionIDb_user1", xmlDoc.parseXML(authenticUrl).getId());
    }

    @Test(testName = "TC 10008: API::GetDirectories() without ID")
    public void checkResponseApiAuthenticatePageWithCorrectDataWithoutId() throws IOException, ParserConfigurationException {
        URL url = new URL(ConstantsPma2.API_XML_URL + ConstantsPma2.GET_DIRECTORY_URL);
        Reporter.log("Open page " + url.toString());
        Reporter.log("\n Expected the requested page doesn't contain valid xml");
        Assert.assertTrue(connection.getContentType(url).contains("text/html"));
    }

    @Test(testName = "TC 10008: API::GetDirectories() WITH ID")
    public void checkResponseApiAuthenticatePageWithCorrectDataWithId()
            throws IOException, ParserConfigurationException {

        Reporter.log("Getting session ID for   " + ConstantsPma2.USER1_B_NAME + "/" + ConstantsPma2.USER1_PASSWORD);
        if (System.getProperty("sessionIDb_user1").equals("")) {
            Assert.fail("Can't get session id from " + authenticUrl);
        }

        Reporter.log("Check that GetRootDirectories and GetDirectories return the same list of folders");
        //2
        Reporter.log("<b>GetDirectories</b>");
        String urlStr = ConstantsPma2.API_XML_URL +
                ConstantsPma2.GET_DIRECTORY_URL +
                "?sessionID=" + System.getProperty("sessionIDb_user1");
        Reporter.log("Open url " + urlStr + "\n");
        URL url = new URL(urlStr);
        try {
            Assert.assertTrue(
                    xmlDoc.isTagPresentAndHasChildNodes(url, "ArrayOfstring"),
                    "xml contains non-empty list of directories "
            );
            Reporter.log("Get list of directories");
            List<String> listOfDerectories = new ArrayList<>();
            listOfDerectories = xmlDoc.getListOfElementsByTag(url, listOfDerectories, "string");

            Reporter.log("<b>GetRootDirectories</b>");
            urlStr = ConstantsPma2.API_XML_URL +
                    ConstantsPma2.GET_ROOT_DIRECTORY_URL +
                    "?sessionID=" + System.getProperty("sessionIDb_user1");
            Reporter.log("Open url " + urlStr + "\n");

            url = new URL(urlStr);
            Reporter.log("Get list of directories");
            List<String> listOfRootDerectories = new ArrayList<>();
            listOfRootDerectories = xmlDoc.getListOfElementsByTag(url, listOfRootDerectories, "string");
            Assert.assertTrue(
                    listOfDerectories.equals(listOfRootDerectories),
                    "Result after request 'GetRootDirectories' equal 'GetDirectories'"
            );


            //3
            Reporter.log("Check that  GetDirectories with path different from GetDirectories without path");
            Reporter.log("<b>GetDirectories with path</b>");
            urlStr = ConstantsPma2.API_XML_URL +
                    ConstantsPma2.GET_DIRECTORY_URL + "?path=" + ConstantsPma2.ALIAS +
                    "&sessionID=" + System.getProperty("sessionIDb_user1");
            Reporter.log("Open url " + urlStr + "\n");
            url = new URL(urlStr);


            List<String> listOfDerectoriesWithPathParametr = new ArrayList<>();
            listOfDerectoriesWithPathParametr = xmlDoc.getListOfElementsByTag(url, listOfDerectoriesWithPathParametr, "string");
            Assert.assertTrue(
                    !listOfDerectoriesWithPathParametr.equals(listOfDerectories),
                    "Result after request GetDirectories with path differ from GetDirectories without path"
            );
            //4
            Reporter.log(
                    "Check that  'GetDirectories calls with path' and 'GetDirectories calls with path%2F' return the same list of folders");
            Reporter.log("<b>GetDirectories with path and %2F</b>");
            urlStr = ConstantsPma2.API_XML_URL +
                    ConstantsPma2.GET_DIRECTORY_URL + "?path=" + ConstantsPma2.ALIAS + "%2F" +
                    "&sessionID=" + System.getProperty("sessionIDb_user1");
            Reporter.log("Open url " + urlStr + "\n");
            url = new URL(urlStr);

            List<String> listOfDerectoriesWithPathParameterWith2F = new ArrayList<>();
            listOfDerectoriesWithPathParameterWith2F = xmlDoc.getListOfElementsByTag(url,
                    listOfDerectoriesWithPathParameterWith2F, "string");

            Assert.assertTrue(
                    listOfDerectoriesWithPathParametr.equals(listOfDerectoriesWithPathParameterWith2F),
                    "Result after request 'GetDirectories calls with path' equal 'GetDirectories calls with path%2F'"
            );
            //5
            Reporter.log(
                    "Check that  'GetDirectories calls with path '_sys_ref%2Fbrightfield' returns not the same to previous call");
            Reporter.log("<b>GetDirectories with path and %2Fbrightfield</b>");
            urlStr = ConstantsPma2.API_XML_URL +
                    ConstantsPma2.GET_DIRECTORY_URL + "?path=" + ConstantsPma2.ALIAS + "%2F" + "brightfield" +
                    "&sessionID=" + System.getProperty("sessionIDb_user1");
            url = new URL(urlStr);

            Reporter.log("Open url " + urlStr + "\n");
            List<String> listOfDerectoriesInSubfolder = new ArrayList<>();
            listOfDerectoriesInSubfolder = xmlDoc.getListOfElementsByTag(url, listOfDerectoriesInSubfolder, "string");
            Assert.assertFalse(
                    listOfDerectoriesInSubfolder.equals(listOfDerectoriesWithPathParameterWith2F)
            );
            Assert.assertFalse(
                    listOfDerectoriesInSubfolder.equals(listOfDerectories)
            );
        } catch (SAXException e) {
            e.printStackTrace();
            Assert.fail("Invalid or empty .xml file " + url);
        }
    }


}
