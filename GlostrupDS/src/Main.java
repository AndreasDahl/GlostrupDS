import util.DicomLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author Andreas
 * @since 24-03-14
 */
public class Main extends JPanel {
    private BufferedImage[] images;
    private int index;

    public Main(BufferedImage[] images) {
        this.images = images;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        if (images == null)
            return;
        g2.drawImage(images[index], 0, 0, null);
        index = (index + 1) % images.length;
    }

    static public void main(String args[]) throws Exception {
        JFrame frame = new JFrame("ShowImageIR");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel panel = new Main(DicomLoader.loadDicom(args[0]));
        frame.getContentPane().add(panel);
        frame.setSize(400, 400);

        frame.setVisible(true);
    }
}
