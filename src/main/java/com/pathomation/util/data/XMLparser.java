package com.pathomation.util.data;


import com.pathomation.util.reporter.Reporter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

public class XMLparser {
    private String sessionId;
    private String status;
    private String reason;


    public int getCountNodeElements(URL url) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        URLConnection connection = url.openConnection();
        InputStream stream = connection.getInputStream();
        Document document = builder.parse(stream);
        NodeList list = document.getChildNodes();
        Reporter.log("COUNT OF NODE ELEMENTS " + list.getLength());
        return list.getLength();
    }


    public static List getListOfElementsByTag(URL url, List <String>listOfElements, String tagName) throws IOException, ParserConfigurationException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        URLConnection connection = url.openConnection();
        InputStream stream = connection.getInputStream();
        Document document = builder.parse(stream);
        NodeList idEl = document.getElementsByTagName(tagName);
        for (int i = 0; i < idEl.getLength(); i++) {
            listOfElements.add(idEl.item(i).getTextContent());
        }
        return listOfElements;

    }

    public static int countElementsByTag(URL url, String tagName) throws IOException, ParserConfigurationException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        URLConnection connection = url.openConnection();
        InputStream stream = connection.getInputStream();
        Document document = builder.parse(stream);
        NodeList idEl = document.getElementsByTagName(tagName);
        return idEl.getLength();

    }

    public static void openXmlUrl(URL url) throws IOException{
        Reporter.log("Open connection with " + url.toString());
        URLConnection connection = url.openConnection();
        connection.getInputStream();
    }


    public String getXmlTextByTagNmae(URL url, String tagName) throws IOException, ParserConfigurationException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        URLConnection connection = url.openConnection();
        Reporter.log("Open connection with " + url.toString());
        InputStream stream = connection.getInputStream();
        Document document = builder.parse(stream);
        Element idEl = (Element) document.getElementsByTagName(tagName).item(0);
        String textContent = idEl.getTextContent();
        if(tagName.equals("Reason")) this.reason = textContent;
        if(tagName.equals("Success")) this.status = textContent;
        if(tagName.equals("SessionId")) this.sessionId = textContent;
        //System.out.println("TEXT CONTENT " +  idEl.getAttribute("i:nil"));
        if(tagName.equals("SessionId") & (textContent.isEmpty()))
            textContent = this.sessionId = idEl.getAttribute("i:nil");
        return textContent;
    }

    public boolean isTagPresentAndHasChildNodes(URL url, String tagName) throws IOException, ParserConfigurationException, SAXException {
        //Reporter.log("Open URL " + url);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        URLConnection connection = url.openConnection();
        InputStream stream = connection.getInputStream();
        Document document = builder.parse(stream);
        Element idEl = (Element) document.getElementsByTagName(tagName).item(0);
        return idEl.hasChildNodes();
    }
    public List<User> getUsers(URL url, List listOfUser) throws IOException, ParserConfigurationException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        URLConnection connection = url.openConnection();
        InputStream stream = connection.getInputStream();
        Document dom = builder.parse(stream);

        Element docEle = dom.getDocumentElement();
        NodeList nl = docEle.getChildNodes();
        if (nl != null) {
            int length = nl.getLength();
            for (int i = 0; i < length; i++) {
                if (nl.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    Element el = (Element) nl.item(i);
                    if (el.getNodeName().contains("User")) {
                        User nUser = new User();
                        nUser.setFirstName(el.getElementsByTagName("FirstName").item(0).getTextContent());
                        nUser.setLastName(el.getElementsByTagName("LastName").item(0).getTextContent());
                        nUser.setLogin(el.getElementsByTagName("Login").item(0).getTextContent());
                        nUser.setEmail(el.getElementsByTagName("Email").item(0).getTextContent());
                        nUser.setAdmin((el.getElementsByTagName("Administrator").item(0).getTextContent()).equals("true")?true:false);
                        nUser.setCanAnotate((el.getElementsByTagName("CanAnnotate").item(0).getTextContent()).equals("true")?true:false);
                        nUser.setSuspended((el.getElementsByTagName("Suspended").item(0).getTextContent()).equals("true")?true:false);
                        listOfUser.add(nUser);

                    }

                }
            }
        }

        return listOfUser;
    }



    public XMLparser parseXML(URL url) throws IOException, ParserConfigurationException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        URLConnection connection = url.openConnection();
        InputStream stream = connection.getInputStream();
        Document document = builder.parse(stream);

        Element idEl = (Element) document.getElementsByTagName("SessionId").item(0);
        this.sessionId = idEl.getTextContent();
        System.setProperty("sessionID", this.sessionId);

        idEl = (Element) document.getElementsByTagName("Success").item(0);
        this.status = idEl.getTextContent();

        return this;
    }


    public String getId() {
        return this.sessionId;
    }

    public String getStatus() {
        return this.status;
    }

    public String getReason() {
        return this.reason;
    }



}
