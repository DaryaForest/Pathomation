package com.pathomation.backendapi;


import com.pathomation.base.BaseBackendTest;
import com.pathomation.util.ConstantsPma2;
import com.pathomation.util.reporter.Reporter;
import com.pathomation.util.reporter.VersionInfoNumberValidator;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class TC10002_APIGetVersionInfoJSONTest  extends BaseBackendTest {

    @Test(testName = "TC 10002: API::GetVersionInfo() JSON")
    public void checkResponseApiVersionJSONPage() throws IOException {

        URL url = new URL(ConstantsPma2.VERSION_API_JSON_URL);
        Reporter.log("Open url " + url + "\n");
        URLConnection connection = url.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(
                connection.getInputStream(), "UTF-8"));
        String inputLine;
        StringBuilder info = new StringBuilder();
        while ((inputLine = in.readLine()) != null)
            info.append(inputLine);
        in.close();
        Reporter.log("Expected HTTP request returns version info in A.B.C.D format");
		Reporter.log("Info from site " + info.toString());
		Assert.assertTrue(VersionInfoNumberValidator.validateJSON(info.toString().trim()));
	}
}

