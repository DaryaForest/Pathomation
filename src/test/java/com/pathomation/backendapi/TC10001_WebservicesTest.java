package com.pathomation.backendapi;

import java.io.IOException;
import java.net.URL;

import com.pathomation.base.BaseBackendTest;
import com.pathomation.util.ConstantsPma2;
import com.pathomation.util.data.XMLparser;
import com.pathomation.util.reporter.Reporter;
import org.testng.Assert;
import org.testng.annotations.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;


public class TC10001_WebservicesTest  extends BaseBackendTest {

    XMLparser xmlDoc = new XMLparser();

    @Test(testName = "TC 10001.1: API Webservice is operational")
    public void tc1001_1_APIWebserviseTest() throws IOException, ParserConfigurationException {
        Reporter.log("Expected " + ConstantsPma2.API_URL + " is running");
        URL url = new URL(ConstantsPma2.API_URL);
        try {
            Assert.assertTrue(
                    xmlDoc.isTagPresentAndHasChildNodes(url, "wsdl:definitions"), "Page isn't visible"
            );
        } catch (SAXException e) {
            e.printStackTrace();
            Assert.fail("Invalid or empty .xml file " + url);
        }

    }
    @Test(testName = "TC 10001.2: Admin Webservice is operational")
    public void tc1001_2_AdminWebserviceTest() throws IOException, ParserConfigurationException {
        Reporter.log("Expected " + ConstantsPma2.API_ADMIN_URL + " is running");
        URL url = new URL(ConstantsPma2.API_ADMIN_URL);
        try {
            Assert.assertTrue(
                    xmlDoc.isTagPresentAndHasChildNodes(url, "wsdl:definitions"), "Page isn't visible");
        } catch (SAXException e) {
            e.printStackTrace();
           Assert.fail("Invalid or empty .xml file " + url);
        }
    }

}



