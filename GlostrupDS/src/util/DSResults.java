package util;

import java.awt.*;

/**
 * @author Andreas
 * @since 28-03-14
 */
public final class DSResults {
    private final Image chosenImage;
    private final int x, y, z;
    private final byte intensity;

    public DSResults(Image chosenImage, int x, int y, int z, byte intensity) {
        this.chosenImage = chosenImage;
        this.x = x;
        this.y = y;
        this.z = z;
        this.intensity = intensity;
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
