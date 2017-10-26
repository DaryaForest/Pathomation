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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TC10014_APIGetAnnotationDistance  extends BaseBackendTest {

    XMLparser xmlDoc = new XMLparser();
    URL authenticUrl;
    URL url;


    @BeforeClass
    public void setUp() throws IOException, ParserConfigurationException, SAXException {
        authenticUrl = new URL(String.format(ConstantsPma2.AUTHENTICATE_API_URL,
                ConstantsPma2.USER1_B_NAME, ConstantsPma2.USER1_PASSWORD));
        System.setProperty("sessionIDb_user1", xmlDoc.parseXML(authenticUrl).getId());
    }

    @Test(testName = "TC 10014: API::GetAnnotationDistance")
    public void getAnnotationsDistance() throws IOException, ParserConfigurationException {

        Map<String, Map<String, Double>> mapOfAnnatation_sDistances = new HashMap<String, Map<String, Double>>();
        Map<String, Double> mapCMU1_mrxs = new HashMap<String, Double>();
        mapCMU1_mrxs.put("0", 443.4457328);
        mapCMU1_mrxs.put("1", 1377.4575123);
        mapCMU1_mrxs.put("2", 0.0);
        mapCMU1_mrxs.put("3", 0.0);
        mapCMU1_mrxs.put("4", 0.0);
        mapCMU1_mrxs.put("5", 436.2721096);
        mapCMU1_mrxs.put("6", 1383.84);
        mapCMU1_mrxs.put("7", 998.8210265);
        mapCMU1_mrxs.put("8", 984.7020468);
        mapCMU1_mrxs.put("9", 899.4624159);
        mapCMU1_mrxs.put("10", 2187.685674);

        Map<String, Double> mapCMU1_svs = new HashMap<String, Double>();
        mapCMU1_svs.put("0", 335.6763476);//TODO in table(TC  10014 - 336.1984561)
        mapCMU1_svs.put("1", 686.8337351);
        mapCMU1_svs.put("2", 0.0);
        mapCMU1_svs.put("3", 0.0);
        mapCMU1_svs.put("4", 0.0);
        mapCMU1_svs.put("5", 298.5338137);
        mapCMU1_svs.put("6", 463.072);
        mapCMU1_svs.put("7", 1013.6680479);
        mapCMU1_svs.put("8", 471.8664785);
        mapCMU1_svs.put("9", 773.8013978);
        mapCMU1_svs.put("10", 994.622701);

        Map<String, Double> mapCMU1_ndpi = new HashMap<String, Double>();
        mapCMU1_ndpi.put("0", 129.102952);//TODO in table(TC  10014 - 129.8028644)
        mapCMU1_ndpi.put("1", 179.5909194);//TODO in table(TC  10014 - 178.0074422)
        mapCMU1_ndpi.put("2", 0.0);
        mapCMU1_ndpi.put("3", 0.0);
        mapCMU1_ndpi.put("4", 0.0);
        mapCMU1_ndpi.put("5", 100.0849347);
        mapCMU1_ndpi.put("6", 279.0410039);
        mapCMU1_ndpi.put("7", 217.2942676);
        mapCMU1_ndpi.put("8", 161.082472);
        mapCMU1_ndpi.put("9", 398.5514785);
        mapCMU1_ndpi.put("10", 465.891767);

        mapOfAnnatation_sDistances.put("CMU-1.ndpi", mapCMU1_ndpi);
        mapOfAnnatation_sDistances.put("CMU-1.mrxs", mapCMU1_mrxs);
        mapOfAnnatation_sDistances.put("CMU-1.svs", mapCMU1_svs);


        Reporter.log("Getting session ID for   " + ConstantsPma2.USER1_B_NAME + "/" + ConstantsPma2.USER1_PASSWORD);
        if (System.getProperty("sessionIDb_user1").equals("")) {
            Assert.fail("Can't get session id from " + authenticUrl);
        }

        Reporter.log("'GetFiles' request for _sys_ref/annotations");
        String folderName = "_sys_ref/annotations";
        String urlStr = ConstantsPma2.API_XML_URL + ConstantsPma2.GET_FILES_DIRECTORY_URL + "?sessionId="
                + System.getProperty("sessionIDb_user1") + "&path=" + ConstantsPma2.ALIAS + "%2F" + folderName;
        Reporter.log("Open url " + urlStr + "\n");
        url = new URL(urlStr);


        List<String> listOfFiles = new ArrayList<>();
        try {
            listOfFiles = xmlDoc.getListOfElementsByTag(url, listOfFiles, "string");
            if (listOfFiles.size() == 0) {
                Assert.fail("Request doesn't return any files");
            }
        } catch (SAXException e) {
            e.printStackTrace();
            Assert.fail("Invalid or empty .xml file " + url);
        }

        for (String filePath : listOfFiles) {
            Reporter.log("<B>Retrieve the annotation for file " + filePath + "</B>");
            String fileName = filePath.substring(filePath.lastIndexOf("/") + 1, filePath.length());
            Map<String, Double> mapOfDistances = mapOfAnnatation_sDistances.get(fileName);
            urlStr = String.format(ConstantsPma2.GET_ANNOTATION_API_XML_URL, System.getProperty("sessionIDb_user1"))
                    + "&PathOrUid=" + filePath;
            Reporter.log("Open url " + urlStr);
            url = new URL(urlStr);

            List<String> listOfAnnotationsID = new ArrayList<>();

            try {
                listOfAnnotationsID = xmlDoc.getListOfElementsByTag(url, listOfAnnotationsID, "AnnotationID");//list of annotations


                for (String annotationID : listOfAnnotationsID) {
                    //0.0 - N/A annotation, according to "ignore and don't include in the test the Slides where the Lengh or Area is N/A" shouldn't be tested
                    Double annotationDistanceFromTC = mapOfDistances.get(annotationID);
                    if ( annotationDistanceFromTC != 0.0) {
                        urlStr = ConstantsPma2.API_XML_URL + ConstantsPma2.GET_ANNOTATION_DISTANCE_URL + "?sessionId="
                                + System.getProperty("sessionIDb_user1") + "&PathOrUid=" + filePath + "&layerID=1&annotationID=" + annotationID;
                        Reporter.log("Open url " + urlStr);
                        url = new URL(urlStr);

                        Double distanceFromXml = round(Double.parseDouble(
                                xmlDoc.getXmlTextByTagNmae(url, "double")), 7
                        );
                        Assert.assertEquals(distanceFromXml, annotationDistanceFromTC,
                                "The distances from xml and expected values" +
                                        "(see TCTC 10014 in Help&Manual) must be equal");
                    } else {
                        continue;
                    }
                }
            } catch (SAXException e) {
                e.printStackTrace();
                Assert.fail("Invalid or empty .xml file " + url);
            }
        }


    }

    private static Double round(Double value, int scale) {
        return Math.round(value * Math.pow(10, scale)) / Math.pow(10, scale);
    }
}
