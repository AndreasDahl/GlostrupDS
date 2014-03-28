package util;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

/**
 * @author Andreas
 * @since 24-03-14
 */
public class DSCalculator {

    public static DSResults calculate(BufferedImage[] images) {
        // Find brightest pixel

        Image chosenImage = images[0];
        int x = 0;
        int y = 0;
        int z = 0;
        byte intensity = 0;

        for (int i = 0; i < images.length; i++) {
            byte[] pixels = ((DataBufferByte)images[z].getRaster().getDataBuffer()).getData();
            int width = images[z].getWidth();

            // TODO: Handle several instance of same brightest brightness
            for (int pIndex = 0; i < pixels.length; i++) {
                if (pixels[pIndex] > intensity) {
                    x = pIndex % width;
                    y = pIndex / width;
                    z = i;
                    intensity = pixels[i];
                    chosenImage = images[z];
                }
            }
            z++;
        }
        System.out.println("X: " + x + " Y: " + y + " Z: " + z + " Intensity: " + intensity);

        return new DSResults(chosenImage, x, y, z, intensity);
    }


}
