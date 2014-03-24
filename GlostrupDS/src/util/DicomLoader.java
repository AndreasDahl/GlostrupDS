package util;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

/**
 * @author Andreas
 * @since 24-03-14
 */
public class DicomLoader {
    public static BufferedImage[] loadDicom(String filename) throws IOException {
        FileInputStream fin = new FileInputStream(filename);
        String suffix = filename.substring(filename.lastIndexOf('.') + 1);
        System.out.println("suf " + suffix);
        Iterator<ImageReader> readers = ImageIO.getImageReadersByFormatName("dicom");
        System.out.println("HasNext: " + readers.hasNext());
        ImageReader imageReader = readers.next();
        ImageInputStream iis = ImageIO.createImageInputStream(fin);
        imageReader.setInput(iis, false);
        int num = imageReader.getNumImages(true);
        BufferedImage[] images = new BufferedImage[num];
        for (int i = 0; i < num; ++i) {
            images[i] = imageReader.read(i);
        }
        fin.close();
        return images;
    }

}
