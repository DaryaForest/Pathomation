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

public class TC10013_API_GetAnnotations  extends BaseBackendTest {

    XMLparser xmlDoc = new XMLparser();
    URL authenticUrl;
    URL url;


    @BeforeClass
    public void setUp() throws IOException, ParserConfigurationException, SAXException {
        authenticUrl = new URL(String.format(ConstantsPma2.AUTHENTICATE_API_URL,
                ConstantsPma2.USER1_B_NAME, ConstantsPma2.USER1_PASSWORD));
        System.setProperty("sessionIDb_user1", xmlDoc.parseXML(authenticUrl).getId());
    }

    @Test(testName = "TC 10013: API::GetAnnotations")
    public void getAnnotations() throws IOException, ParserConfigurationException {

        Reporter.log("Getting session ID for   " + ConstantsPma2.USER1_B_NAME + "/" + ConstantsPma2.USER1_PASSWORD);
        if (System.getProperty("sessionIDb_user1").equals("")) {
            Assert.fail("Can't get session id from " + authenticUrl);
        }

        Reporter.log("Retrieve the annotations for <u>_sys_ref/annotations/CMU-1.mrxs</u>");
        try {

            String filePath = "%2Fannotations%2FCMU-1.mrxs";
            url = new URL(String.format(
                    ConstantsPma2.GET_ANNOTATION_API_XML_URL, System.getProperty("sessionIDb_user1"))
                    + "&PathOrUid=" + ConstantsPma2.ALIAS + filePath + "&currentUserOnly=false"
            );
            int countOfAnnotations = xmlDoc.countElementsByTag(url, "Annotation");
            Reporter.log("Count of annotations  " + countOfAnnotations);
            Assert.assertTrue(countOfAnnotations >= 5,
                    "A list of annotations is returned (at least 5)");


            Reporter.log("Retrieve the annotations for <u>_sys_ref/annotations/cmu-1.mrxs</u> (lower case)");
            filePath = "%2Fannotations%2Fcmu-1.mrxs";
            url = new URL(String.format(
                    ConstantsPma2.GET_ANNOTATION_API_XML_URL, System.getProperty("sessionIDb_user1"))
                    + "&PathOrUid=" + ConstantsPma2.ALIAS + filePath + "&currentUserOnly=false"
            );
            countOfAnnotations = xmlDoc.countElementsByTag(url, "Annotation");
            Reporter.log("Count of annotations  " + countOfAnnotations);
            Assert.assertTrue(countOfAnnotations == 0,
                    "No annotations are returned (the PathOrUID parameter of GetAnnotations() is case-sensitive");
        } catch (SAXException e) {
            e.printStackTrace();
            Assert.fail("Invalid or empty .xml file " + url);
        }


    }



}
