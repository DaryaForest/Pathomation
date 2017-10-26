package com.pathomation.backendapi;

import com.pathomation.base.BaseBackendTest;
import com.pathomation.util.ConstantsPma2;
import com.pathomation.util.data.XMLparser;
import org.testng.Assert;
import com.pathomation.util.reporter.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;

public class TC10003_APIAuthenticate  extends BaseBackendTest {

    private XMLparser xmlDoc;

    /**
     * TC 10003.1: For authorized users
     */
    @DataProvider
    public Object[][] incorrectUserData() {
        return new Object[][]{{"foo", "bar"}, {"admin", "ADMIN"}};
    }

    @BeforeClass
    public void createSessions() {
        xmlDoc = new XMLparser();
    }

    @Test(testName = "TC 10003.1: For authorized users",priority = 1)
    public void tc10003_1ForAuthorizedUsers() throws IOException, ParserConfigurationException {

        URL url = new URL(String.format(ConstantsPma2.AUTHENTICATE_API_URL,
                ConstantsPma2.USER1_B_NAME, ConstantsPma2.USER1_PASSWORD));

        try {
            xmlDoc.parseXML(url);
            System.setProperty("sessionIDb_user1", xmlDoc.getId());
            xmlDoc.parseXML(url);
            Reporter.log("Expected user1 sessionId from first call <u>equal</u> user1 sessionId from second call<br>");
            Assert.assertEquals(xmlDoc.getId(), System.getProperty("sessionIDb_user1"));
            url = new URL(String.format(ConstantsPma2.AUTHENTICATE_API_URL,
                    "b_user2", "user2"));

            Assert.assertNotEquals(
                    System.getProperty("sessionIDb_user1"), xmlDoc.getXmlTextByTagNmae(url, "SessionId"),
                    "Expected b_user1 sessionId NOT equal sessionId for b_user2"
            );
        } catch (SAXException e) {
            e.printStackTrace();
            Assert.fail("Invalid or empty .xml file " + url);
        }
    }


    @Test(testName = "TC 10003.2: For un-authorized users", dataProvider = "incorrectUserData")
    public void tc10003_1ForUnauthorizedUsers(String userName, String password) throws
            IOException, ParserConfigurationException {
        URL url = new URL(String.format(ConstantsPma2.AUTHENTICATE_API_URL,
                userName, password));
        Reporter.log(
                "Check that sessionID CANNOT be obtained for a user " +
                        "with a given password through /xml/Authenticate with username = " +
                        userName + " and password = " + password);
        Reporter.log("Open page: " + url.toString());


        String reason = null;
        String success = null;
        try {
            success = xmlDoc.getXmlTextByTagNmae(url, "Success");
            reason = xmlDoc.getXmlTextByTagNmae(url, "Reason");
        } catch (SAXException e) {
            e.printStackTrace();
            Assert.fail("Invalid or empty .xml file " + url);
        }

        Assert.assertTrue(
                (reason.contains(ConstantsPma2.LOGON_FALURE_REASON) ||
                        reason.contains(ConstantsPma2.LOGON_FALURE_REASON_LDAP))

        );
        Assert.assertEquals(success, "false");
    }


}

