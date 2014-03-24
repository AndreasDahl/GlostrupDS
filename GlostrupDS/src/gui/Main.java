package gui;

import util.DSCalculator;
import util.DicomLoader;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author Andreas
 * @since 24-03-14
 */
public class Main {
    public static final String DEFAULT_IMG_PATH = "GlostrupDS/res/testBrain.dcm";
    public static final String TITLE = "GlostrupDS";
    private static JFrame frame;

    private JPanel mainPanel;
    private GuiImage guiImage1;
    private JButton nextButton;
    private JButton previousButton;
    private BufferedImage[] images;
    private int i = 0;

    private void createUIComponents() {
        try {
            images = DicomLoader.loadDicom(DEFAULT_IMG_PATH);
            images = Arrays.copyOfRange(images, 3, images.length);
            DSCalculator calculator = new DSCalculator(images);
            i = calculator.getZ();
            guiImage1 = new GuiImage(calculator.getChosenImage());
        } catch (IOException e) {
            e.printStackTrace();
        }

        previousButton = new JButton("Next");
        previousButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                i -= 1;
                if (i < 0)
                    i += images.length;
                guiImage1.setImage(images[i]);
            }
        });

        nextButton = new JButton("Next");
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                i = (i + 1) % images.length;
                guiImage1.setImage(images[i]);
            }
        });
    }

    static public void main(String args[]) throws Exception {
        frame = new JFrame(TITLE);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Main main = new Main();
        frame.getContentPane().add(main.mainPanel);
        frame.setSize(400, 400);
        frame.setVisible(true);
    }
}
