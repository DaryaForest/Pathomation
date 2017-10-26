package com.pathomation.backendapi;

import com.pathomation.base.BaseBackendTest;
import com.pathomation.util.ConstantsPma2;
import com.pathomation.util.data.XMLparser;
import com.pathomation.util.reporter.Reporter;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;

public class TC10012_APICanAnnotate  extends BaseBackendTest {

    XMLparser xmlDoc = new XMLparser();


    @Test(testName = "TC 10012.1: For users that can annotate")
    public void tc10012_1CanAnnotate() throws IOException, ParserConfigurationException {

        URL url = new URL(
                String.format(
                        ConstantsPma2.AUTHENTICATE_API_URL, ConstantsPma2.USER_CAN_ANNOTATE_NAME, ConstantsPma2.USER1_PASSWORD)
        );

        Reporter.log("Expected " + url + " is running");
        try {
            System.setProperty("canAnnotateUserId", xmlDoc.parseXML(url).getId());
        } catch (SAXException e) {
            Reporter.log("Invalid or empty .xml file");
            e.printStackTrace();
            Assert.fail("Invalid or empty .xml file" + url);
        }

        Reporter.log("Getting session ID for   " + ConstantsPma2.USER_CAN_ANNOTATE_NAME + "/" + ConstantsPma2.USER1_PASSWORD);
        if (System.getProperty("canAnnotateUserId").equals("")) {
            Assert.fail("Can't get session id from " + url.toString());
        }

        url = new URL(
                String.format(
                        ConstantsPma2.CAN_ANNOTATE_API_XML_URL, System.getProperty("canAnnotateUserId"))
        );
        Reporter.log("Expected " + url + " is running");
        Reporter.log("Expected CanAnnotate = true for " + ConstantsPma2.USER_CAN_ANNOTATE_NAME);
        try {
            Assert.assertEquals(xmlDoc.getXmlTextByTagNmae(url, "boolean"), "true");
        } catch (SAXException e) {
            e.printStackTrace();
            Assert.fail("Invalid or empty .xml file " + url);
        }

    }

    @Test(testName = "TC 10012.2: For users that can NOT annotate")
    public void tc10012_2CanNotAnnotate() throws IOException, ParserConfigurationException {

        URL url = new URL(
                String.format(
                        ConstantsPma2.AUTHENTICATE_API_URL, ConstantsPma2.USER_CAN_NOT_ANNOTATE_NAME, ConstantsPma2.USER1_PASSWORD)
        );


        Reporter.log("Expected " + url + " is running");
        try {
            System.setProperty("canAnnotateUserId", xmlDoc.parseXML(url).getId());
        } catch (SAXException e) {
            e.printStackTrace();
            Assert.fail("Invalid or empty .xml file " + url);
        }


        Reporter.log("Getting session ID for   " + ConstantsPma2.USER_CAN_NOT_ANNOTATE_NAME + "/" + ConstantsPma2.USER1_PASSWORD);
        if (System.getProperty("canAnnotateUserId").equals("")) {
            Assert.fail("Can't get session id from " + url.toString());
        }

        url = new URL(
                String.format(
                        ConstantsPma2.CAN_ANNOTATE_API_XML_URL, System.getProperty("canAnnotateUserId"))
        );
        Reporter.log("Expected " + url + " is running");
        Reporter.log("Expected CanAnnotate = false for " + ConstantsPma2.USER_CAN_NOT_ANNOTATE_NAME);
        try {
            Assert.assertEquals(xmlDoc.getXmlTextByTagNmae(url, "boolean"), "false");
        } catch (SAXException e) {
            e.printStackTrace();
            Assert.fail("Invalid or empty .xml file " + url);
        }

    }

}
