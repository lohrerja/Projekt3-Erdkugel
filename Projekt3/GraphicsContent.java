package Projekt3;

import javax.swing.*;
import java.awt.*;

public class GraphicsContent extends JPanel {

    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2d;
        g2d = (Graphics2D) g;

        int originX = 0;
        int originY = 0;
        int width = this.getWidth();
        int height = this.getHeight();

        g.setColor(Color.BLACK);
        g.drawLine(originX, originY + height / 2, originX + width - 1, originY + height / 2);

        g.setColor(Color.BLACK);
        g.drawLine(originX + width / 2, originY , originX + width / 2, originY + height);

    }

}
