package com.pathomation.util;


import com.pathomation.util.reporter.Reporter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;


public class ImageRGBComorator {
    public static boolean compare(String msg, String urlImg1, String fileImg) throws IOException {
        Reporter.log(msg);
        URL url = new URL(urlImg1);
        InputStream stream = url.openStream();
        boolean compareFlag = true;
        File file2 = new File(fileImg);

        BufferedImage image1 = ImageIO.read(stream);
        BufferedImage image2 = ImageIO.read(file2);

        int columns = image1.getWidth();
        int rows = image1.getHeight();

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                int rgb = image1.getRGB(col, row);
                int rgb2 = image2.getRGB(col, row);
                if (rgb == rgb2) continue;
                else {
                    compareFlag = false;
                    break;
                }
            }
        }
        return compareFlag;


    }
}
