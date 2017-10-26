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

public class TC15001_AdminAuthenticate  extends BaseBackendTest {

    private XMLparser xmlDoc;

    URL adminAuthenticUrl;
    URL b_userAuthenticURL;

    @BeforeClass
    public void createSessions() throws IOException, ParserConfigurationException, SAXException {
        xmlDoc = new XMLparser();
        adminAuthenticUrl = new URL(String.format(ConstantsPma2.AUTHENTICATE_API_URL,
                ConstantsPma2.ADMIN_USER_NAME, ConstantsPma2.ADMIN_PASSWORD));
        System.setProperty("sessionId", xmlDoc.parseXML(adminAuthenticUrl).getId());


        b_userAuthenticURL = new URL(String.format(ConstantsPma2.AUTHENTICATE_API_URL,
                ConstantsPma2.USER1_B_NAME, ConstantsPma2.USER1_PASSWORD));
        System.setProperty("sessionIDb_user1", xmlDoc.parseXML(b_userAuthenticURL).getId());
    }

    @Test(testName = "TC 15001.1: For authorized users")
    public void adminAuthenticateWithCorrectData() throws IOException, ParserConfigurationException {
        {
            Reporter.log(
                    "Check that sessionID CAN be obtained for a user with a given password through " +
                            "/xml/Authenticate with username = "
                            + ConstantsPma2.ADMIN_USER_NAME + " and password = " + ConstantsPma2.ADMIN_PASSWORD
            );
            Reporter.log("Getting session ID for   " + ConstantsPma2.ADMIN_USER_NAME + "/" + ConstantsPma2.ADMIN_PASSWORD);
            if (System.getProperty("sessionId").equals("")) {
                Assert.fail("Can't get session id from " + adminAuthenticUrl.toString());
            }
            Reporter.log("Getting session ID for   " + ConstantsPma2.USER1_B_NAME + "/" + ConstantsPma2.USER1_PASSWORD);
            if (System.getProperty("sessionIDb_user1").equals("")) {
                Assert.fail("Can't get session id from " + b_userAuthenticURL);
            }

            Reporter.log("Expected user1 sessionId NOT equal sessionId for admin");
            Assert.assertNotEquals(System.getProperty("sessionId"), System.getProperty("sessionIDb_user1"),
                    "Expected 'admin' sessionId doesn't equal to 'b_user1' sessionId");
        }
    }

    @Test(testName = "TC 15001.2: For un-authorized users")
    public void adminAuthenticateWithIncorrectData() throws IOException, ParserConfigurationException {
        {
            URL url = new URL(String.format(ConstantsPma2.ADMIN_AUTHENTICATE_API_URL,
                    ConstantsPma2.ADMIN_USER_NAME,
                    ConstantsPma2.INCORRECT_ADMIN_USER_PASSWORD));

            Reporter.log("Check that sessionId CAN'T be obtained for given user with a given password "
                    + url.toString()
            );

            try {
                Assert.assertEquals(
                        xmlDoc.getXmlTextByTagNmae(url, "Reason"),
                        ConstantsPma2.LOGON_FALURE_REASON
                );

                Assert.assertEquals(
                        xmlDoc.getXmlTextByTagNmae(url, "Success"),
                        "false"
                );
                String sessionID = xmlDoc.getXmlTextByTagNmae(url, "SessionId");

                Assert.assertEquals(sessionID,
                        "true",
                        "Expected 'i:nil' atrribute of SessionId = 'true'"
                );
            } catch (SAXException e) {
                e.printStackTrace();
                Assert.fail("Invalid or empty .xml file " + url);
            }

        }

    }

    @Test(testName = "TC 15001.3: For non-administrative users")
    public void adminAuthenticateUnauthorizedUsers() throws IOException, ParserConfigurationException {
        {
            URL url = new URL(String.format(ConstantsPma2.ADMIN_AUTHENTICATE_API_URL,
                    ConstantsPma2.USER1_B_NAME,
                    ConstantsPma2.USER1_PASSWORD));

            Reporter.log("Check that sessionId CAN'T be obtained for given user with a given password "
                    + url.toString()
            );

            try {
                Assert.assertEquals(
                        xmlDoc.getXmlTextByTagNmae(url, "Reason"),
                        ConstantsPma2.LOGON_FALURE_REASON
                );

                Assert.assertEquals(
                        xmlDoc.getXmlTextByTagNmae(url, "Success"),
                        "false"
                );
                String sessionID = xmlDoc.getXmlTextByTagNmae(url, "SessionId");

                Assert.assertEquals(sessionID,
                        "true",
                        "Expected 'i:nil' atrribute of SessionId = 'true'"
                );
            } catch (SAXException e) {
                e.printStackTrace();
                Assert.fail("Invalid or empty .xml file " + url);
            }

        }
    }
}
