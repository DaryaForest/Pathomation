package com.pathomation.backendapi;

import com.pathomation.base.BaseBackendTest;
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
import java.net.MalformedURLException;
import java.net.URL;

public class TC15003_AdminGetUsers  extends BaseBackendTest {

    private XMLparser xmlDoc;

    URL adminAuthenticUrl;

    @BeforeClass
    public void createSessions() throws IOException, ParserConfigurationException, SAXException {

        xmlDoc = new XMLparser();
        adminAuthenticUrl = new URL(String.format(ConstantsPma2.ADMIN_AUTHENTICATE_API_URL,
                ConstantsPma2.ADMIN_USER_NAME, ConstantsPma2.ADMIN_PASSWORD));
        System.setProperty("sessionId", xmlDoc.parseXML(adminAuthenticUrl).getId());
    }

    @Test(testName = "TC 15003: Admin::GetUsers()")
    public void requestListOfUsers() throws IOException, ParserConfigurationException {
        {
            Reporter.log("Getting session ID for   " + ConstantsPma2.ADMIN_USER_NAME + "/" + ConstantsPma2.ADMIN_PASSWORD);
            if (System.getProperty("sessionId").equals("")) {
                Assert.fail("Can't get session id from " + adminAuthenticUrl);
            }

            URL url = new URL(String.format(ConstantsPma2.ADMIN_GET_USERS_XML + "?sessionID=" +
                    System.getProperty("sessionID"))
            );
            Reporter.log("Expected list of users");
            try {
                Assert.assertTrue(
                        xmlDoc.isTagPresentAndHasChildNodes(url, "ArrayOfUser")
                );
            } catch (SAXException e) {
                e.printStackTrace();
                Assert.fail("Invalid or empty .xml file " + url);
            }
        }
    }
}
