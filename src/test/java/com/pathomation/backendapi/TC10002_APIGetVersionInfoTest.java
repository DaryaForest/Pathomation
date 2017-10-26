package com.pathomation.backendapi;

import com.pathomation.base.BaseBackendTest;
import com.pathomation.util.ConstantsPma2;
import com.pathomation.util.data.XMLparser;
import com.pathomation.util.reporter.Reporter;
import com.pathomation.util.reporter.VersionInfoNumberValidator;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;

public class TC10002_APIGetVersionInfoTest  extends BaseBackendTest {

    XMLparser xmlDoc = new XMLparser();

    @Test(testName = "TC 10002: API::GetVersionInfo()")
    public void checkResponseApiVersionPage() throws IOException, ParserConfigurationException {
        URL url = new URL(ConstantsPma2.VERSION_API_URL);
        String versionInfo = null;
        try {
            Reporter.log("Get version number");
            versionInfo = xmlDoc.getXmlTextByTagNmae(url, "string");
        } catch (SAXException e) {
            Reporter.log("Invalid or empty .xml file");
            e.printStackTrace();
            Assert.fail("Invalid or empty .xml file " + url);
        }
        Assert.assertTrue(VersionInfoNumberValidator.validateXMLVersionInfo(versionInfo),
                "Version number must be in x.x.x.xxx format, version number is " + versionInfo);
    }
}


