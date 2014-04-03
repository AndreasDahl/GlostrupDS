package util;

import java.awt.image.BufferedImage;

/**
 * @author Andreas
 * @since 28-03-14
 */
public final class DSResults {
    private final BufferedImage chosenImage;
    private final int x, y, z;
    private final int intensity;

    public DSResults(BufferedImage chosenImage, int x, int y, int z, int intensity) {
        this.chosenImage = chosenImage;
        this.x = x;
        this.y = y;
        this.z = z;
        this.intensity = intensity;
    }

    public BufferedImage getChosenImage() {
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

    public int getIntensity() {
        return intensity;
    }
}
