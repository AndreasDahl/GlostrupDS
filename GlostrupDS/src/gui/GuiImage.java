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
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Image img = image.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
        g.drawImage(img, 0, 0, this);
    }

}
