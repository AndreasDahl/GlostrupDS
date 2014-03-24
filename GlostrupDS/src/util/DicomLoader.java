package util;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.io.Closeable;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

/**
 * @author Andreas
 * @since 24-03-14
 */
public class DicomLoader {
    public static BufferedImage[] loadDicom(String filename) throws IOException {
        FileInputStream fileInputStream = null;
        ImageInputStream imageInputStream = null;
        try {
            // Prepare streams
            fileInputStream = new FileInputStream(filename);
            imageInputStream = ImageIO.createImageInputStream(fileInputStream);

            // Get reader
            Iterator<ImageReader> readers = ImageIO.getImageReadersByFormatName("dicom");
            ImageReader imageReader = readers.next();

            // Read Images
            imageReader.setInput(imageInputStream, false);
            int imageCount = imageReader.getNumImages(true);
            BufferedImage[] images = new BufferedImage[imageCount];
            for (int i = 0; i < imageCount; ++i) {
                images[i] = imageReader.read(i);
            }
            return images;

        } finally {
            close(fileInputStream);
            close(imageInputStream);
        }
    }

    private static void close(Closeable closeable) {
        if (closeable == null) return;
        try {
            closeable.close();
        } catch (IOException e) {
            // TODO: log the exception
        }
    }

}
