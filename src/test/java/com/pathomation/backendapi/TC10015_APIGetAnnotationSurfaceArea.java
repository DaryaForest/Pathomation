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

public class TC10015_APIGetAnnotationSurfaceArea  extends BaseBackendTest {

    XMLparser xmlDoc = new XMLparser();
    URL authenticUrl;
    URL url;


    @BeforeClass
    public void setUp() throws IOException, ParserConfigurationException, SAXException {
        authenticUrl = new URL(String.format(ConstantsPma2.AUTHENTICATE_API_URL,
                ConstantsPma2.ADMIN_USER_NAME, ConstantsPma2.ADMIN_PASSWORD));
        System.setProperty("sessionIDb_user1", xmlDoc.parseXML(authenticUrl).getId());
    }

    @Test(testName = "TC 10015: API::GetAnnotationSurfaceArea")
    public void getAnnotationsSyrfaceArea() throws IOException, ParserConfigurationException {

        Map<String, Map<String, Double>> mapOfAnnatation_sArea = new HashMap<String, Map<String, Double>>();
        Map<String, Double> mapCMU1_mrxs = new HashMap<String, Double>();
        mapCMU1_mrxs.put("0", 0.0);
        mapCMU1_mrxs.put("1", 140708.8512);
        mapCMU1_mrxs.put("2", 0.0);
        mapCMU1_mrxs.put("3", 0.0);
        mapCMU1_mrxs.put("4", 0.0);
        mapCMU1_mrxs.put("5", 0.0);
        mapCMU1_mrxs.put("6", 110333.5632);
        mapCMU1_mrxs.put("7", 79134.71768);
        mapCMU1_mrxs.put("8", 61689.58712);
        mapCMU1_mrxs.put("9", 0.0);
        mapCMU1_mrxs.put("10", 301012.8768);

        Map<String, Double> mapCMU1_svs = new HashMap<String, Double>();
        mapCMU1_svs.put("0", 0.0);
        mapCMU1_svs.put("1", 31196.83729);
        mapCMU1_svs.put("2", 0.0);
        mapCMU1_svs.put("3", 0.0);
        mapCMU1_svs.put("4", 0.0);
        mapCMU1_svs.put("5", 0.0);
        mapCMU1_svs.put("6", 13398.24581);
        mapCMU1_svs.put("7", 81504.80621);
        mapCMU1_svs.put("8", 11847.34308);
        mapCMU1_svs.put("9", 0.0);
        mapCMU1_svs.put("10", 64885.56879);

        Map<String, Double> mapCMU1_ndpi = new HashMap<String, Double>();
        mapCMU1_ndpi.put("0", 0.0);
        mapCMU1_ndpi.put("1", 1995.54595);//in TC 2005.723071
        mapCMU1_ndpi.put("2", 0.0);
        mapCMU1_ndpi.put("3", 0.0);
        mapCMU1_ndpi.put("4", 0.0);
        mapCMU1_ndpi.put("5", 0.0);
        mapCMU1_ndpi.put("6", 4383.4302);
        mapCMU1_ndpi.put("7", 3745.30176);
        mapCMU1_ndpi.put("8", 1983.81111);
        mapCMU1_ndpi.put("9", 0.0);
        mapCMU1_ndpi.put("10", 12258.54657);

        mapOfAnnatation_sArea.put("CMU-1.ndpi", mapCMU1_ndpi);
        mapOfAnnatation_sArea.put("CMU-1.mrxs", mapCMU1_mrxs);
        mapOfAnnatation_sArea.put("CMU-1.svs", mapCMU1_svs);


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
            Map<String, Double> mapOfAreas = mapOfAnnatation_sArea.get(fileName);
            urlStr = String.format(ConstantsPma2.GET_ANNOTATION_API_XML_URL, System.getProperty("sessionIDb_user1"))
                    + "&PathOrUid=" + filePath;
            Reporter.log("Open url " + urlStr);
            url = new URL(urlStr);

            List<String> listOfAnnotationsID = new ArrayList<>();

            try {
                listOfAnnotationsID = xmlDoc.getListOfElementsByTag(url, listOfAnnotationsID, "AnnotationID");//list of annotations

                for (String annotationID : listOfAnnotationsID) {
                    //0.0 - N/A annotation, according to "ignore and don't include in the test the Slides where the Lengh or Area is N/A" shouldn't be tested
                    Double annotationDistanceFromTC = mapOfAreas.get(annotationID);
                    if (annotationDistanceFromTC != 0.0) {
                        urlStr = ConstantsPma2.API_XML_URL + ConstantsPma2.GET_ANNOTATION_SURFACE_AREA_URL + "?sessionId="
                                + System.getProperty("sessionIDb_user1") + "&PathOrUid=" + filePath + "&layerID=1&annotationID=" + annotationID;
                        Reporter.log("Open url " + urlStr);
                        url = new URL(urlStr);

                        Double distanceFromXml = round(Double.parseDouble(
                                xmlDoc.getXmlTextByTagNmae(url, "double")), 5
                        );
                        if (mapOfAreas.get(annotationID).equals(null)) throw new NullPointerException();
                        Assert.assertEquals(distanceFromXml, mapOfAreas.get(annotationID),
                                "The 'area' value from xml and expected value" +
                                        "(see TC 10015 in Help&Manual) must be equal");
                    } else {
                        continue;
                    }
                }
            } catch (SAXException e) {
                e.printStackTrace();
                Assert.fail("Invalid or empty .xml file " + url);
            } catch (NullPointerException e) {
                e.printStackTrace();
                Assert.fail("Can't find area value by key(id) in map(table)");
            }
        }
    }

    private static Double round(Double value, int scale) {
        return Math.round(value * Math.pow(10, scale)) / Math.pow(10, scale);
    }
}
