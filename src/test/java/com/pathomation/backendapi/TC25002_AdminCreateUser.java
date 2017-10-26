package com.pathomation.backendapi;


import com.pathomation.base.BaseBackendTest;
import com.pathomation.util.ConstantsPma2;
import com.pathomation.util.data.XMLparser;
import com.pathomation.util.data.User;
import org.testng.Assert;
import com.pathomation.util.reporter.Reporter;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class TC25002_AdminCreateUser extends BaseBackendTest {

    URL adminAuthenticUrl;
    XMLparser xmlDoc = new XMLparser();


    @BeforeClass
    public void getSessionId() throws IOException, ParserConfigurationException, SAXException {
        adminAuthenticUrl = new URL(String.format(ConstantsPma2.ADMIN_AUTHENTICATE_API_URL,
                ConstantsPma2.ADMIN_USER_NAME, ConstantsPma2.ADMIN_PASSWORD));
        System.setProperty("sessionId", xmlDoc.parseXML(adminAuthenticUrl).getId());
    }

    @Test(testName = "TC 25002: Admin::CreateUser()")
    public void checkUserCreate() throws ParserConfigurationException, IOException {
        boolean isUserAdded = false;

        Reporter.log("Getting session ID for   " + ConstantsPma2.ADMIN_USER_NAME + "/" + ConstantsPma2.ADMIN_PASSWORD);
        if (System.getProperty("sessionId").equals("")) {
            Assert.fail("Can't get session id from " + adminAuthenticUrl);
        }


        User user = new User();
        List<User> listOfUsers = null;
        user.createUserApiCall();
        URL url = new URL(
                ConstantsPma2.ADMIN_GET_USERS_XML + "?sessionID=" + System.getProperty("sessionId")
        );
        try {
            Assert.assertTrue(
                    xmlDoc.isTagPresentAndHasChildNodes(url, "ArrayOfUser"),
                    "xml contains non-empty list of users "
            );
            listOfUsers = new ArrayList<>();
            listOfUsers = xmlDoc.getUsers(url, listOfUsers);
        } catch (SAXException e) {
            e.printStackTrace();
            Assert.fail("Invalid or empty .xml file " + url);

        }
        for (User u : listOfUsers) {
            if (u.equals(user)) isUserAdded = true;
        }
        Reporter.log("Expected newly created user visible in the list of users on page ");
        Assert.assertTrue(
                isUserAdded,
                "Expected newly created user visible in the list of users on page " + url.toString()
        );

    }

}
