package util;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

/**
 * @author Andreas
 * @since 24-03-14
 */
public class DSCalculator {
    private Image chosenImage;
    private int x, y, z;
    private byte intensity = 0;

    public DSCalculator(BufferedImage[] images) {
        // Find brightest pixel
        int dz = 0;
        while (dz < images.length) {
            byte[] pixels = ((DataBufferByte)images[dz].getRaster().getDataBuffer()).getData();
            int width = images[dz].getWidth();

            // TODO: Handle several instance of same brightest brightness
            for (int i = 0; i < pixels.length; i++) {
                if (pixels[i] > intensity) {
                    x = i % width;
                    y = i / width;
                    z = dz;
                    intensity = pixels[i];
                    chosenImage = images[dz];
                }
            }
            dz++;
        }
        System.out.println("X: " + x + " Y: " + y + " Z: " + z + " Intensity: " + intensity);
    }

    public Image getChosenImage() {
        return chosenImage;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public byte getIntensity() {
        return intensity;
    }
}
