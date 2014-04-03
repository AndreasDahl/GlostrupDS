package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author Andreas
 * @since 24-03-14
 */
public class GuiImage extends JPanel {
    private BufferedImage image;

    public GuiImage() {
        super();
    }

    public GuiImage(BufferedImage img) {
        super();
        this.image = img;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;

        g2.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        g2.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }

}
