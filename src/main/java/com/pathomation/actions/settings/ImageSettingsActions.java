package com.pathomation.actions.settings;


import com.pathomation.base.BaseTest;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class ImageSettingsActions extends BaseTest{
    public String getContentFormat(String url) throws IOException {
        URL imageUrl = new URL(url);
        URLConnection conn = imageUrl.openConnection();
        String type = conn.getContentType();
        return type;
    }
    public String getContentSize(String url) throws IOException {
        BufferedImage bimg = null;
        try {
            bimg = ImageIO.read(new URL(url));
        } catch (IOException e) {
            e.printStackTrace();
        }
          return bimg.getWidth() + "*" + bimg.getHeight();
    }


    public void setImageFormat(String format)  {

    }

}
