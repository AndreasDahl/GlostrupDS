package util;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

/**
 * ColorMap contains collection of key colors mapped to certain values in the given range.
 * Any point between keys are calculated by interpolating the two nearest colors.
 *
 * @author Andreas
 * @since 02-04-14
 */
public class ColorMap {
    private final int range;
    private final NavigableMap<Integer, Color> colors;

    public ColorMap(int range) {
        this(range, Color.BLACK, Color.WHITE);
    }

    public ColorMap(int range, Color startColor, Color endColor) {
        this.colors = new TreeMap<Integer, Color>();
        this.range = range;
        addColor(0, startColor);
        addColor(this.range - 1, endColor);
    }

    public void addColor(int position, Color color) {
        testIsInRange(position);
        colors.put(position, color);
    }

    private void testIsInRange(int intensity) {
        if (intensity < 0 || intensity >= range) {
            throw new IllegalArgumentException("Requested intensity out of range: " + intensity);
        }
    }

    public Color getColor(int intensity) {
        if (intensity < 0)
            intensity += range;
        testIsInRange(intensity);

        Map.Entry<Integer, Color> floor = colors.floorEntry(intensity);
        Map.Entry<Integer, Color> ceil  = colors.ceilingEntry(intensity);

        int range = ceil.getKey() - floor.getKey();
        if (range == 0)
            return floor.getValue();
        else {
            int value = intensity - floor.getKey();
            float factor = (float) value / (float) range;

            Color color1 = floor.getValue();
            Color color2 = ceil.getValue();

            int red = Math.round(color1.getRed() * (1 - factor) + color2.getRed() * factor);
            int green = Math.round(color1.getGreen() * (1 - factor) + color2.getGreen() * factor);
            int blue = Math.round(color1.getBlue() * (1 - factor) + color2.getBlue() * factor);

            return new Color(red, green, blue);
        }
    }

    public BufferedImage apply(BufferedImage image) {
        BufferedImage result = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);

        byte[] greyPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();

        for (int i = 0; i < greyPixels.length; i++) {
            int x = i % image.getWidth();
            int y = i / image.getWidth();
            result.setRGB(x, y, getColor((int) greyPixels[i]).getRGB());
        }

        return result;
    }

    public int getRange() {
        return range;
    }
}
