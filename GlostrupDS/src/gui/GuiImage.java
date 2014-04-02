package gui;

import javax.swing.*;
import java.awt.*;

/**
 * @author Andreas
 * @since 24-03-14
 */
public class GuiImage extends JPanel {
    private Image image;

    public GuiImage(Image img) {
        super();
        this.image = img;
    }

    public void setImage(Image image) {
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
