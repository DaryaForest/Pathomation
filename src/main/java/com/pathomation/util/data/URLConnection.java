package com.pathomation.util.data;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;

public class URLConnection {

    public static String getContentType(URL url) throws ParserConfigurationException, IOException {
        java.net.URLConnection connection = url.openConnection();
        return connection.getContentType();
    }

    public static String getContent(URL url) throws ParserConfigurationException, IOException {
        java.net.URLConnection connection = url.openConnection();
        return (String) connection.getContent();
    }

}
