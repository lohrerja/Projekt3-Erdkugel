package Projekt3;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import utils.ApplicationTime;
import Projekt3.*;

import static Projekt3.Constants.*;

public class GraphicsContent extends Animation{

    @Override
    protected ArrayList<JFrame> createFrames(ApplicationTime applicationTimeThread) {
        // a list of all frames (windows) that will be shown
        ArrayList<JFrame> frames = new ArrayList<>();

        // Create main frame (window)

        JFrame frame = new JFrame("Projekt3 - Flugzeugstrecke");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new GraphicsContentPanel(applicationTimeThread);
        frame.add(panel);
        frame.pack(); // adjusts size of the JFrame to fit the size of it's components
        frame.setVisible(true);
        frame.setLocation(2,2);
        frame.setVisible( true );

        frames.add(frame);
        return frames;
    }

}


class GraphicsContentPanel extends JPanel {

    private final ApplicationTime t;
    public GraphicsContentPanel(ApplicationTime thread) {this.t=thread;}
    public Dimension getPreferredSize() { return new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT);}

    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        double time = t.getTimeInSeconds();
        Graphics2D g2d;
        g2d = (Graphics2D) g;

        int originX = 0;
        int originY = 0;
        int width = this.getWidth();
        int height = this.getHeight();

        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);

        g.setColor(Color.BLACK);
        g.drawLine(originX, originY + height / 2, originX + width - 1, originY + height / 2);

        g.setColor(Color.BLACK);
        g.drawLine(originX + width / 2, originY , originX + width / 2, originY + height);

        g2d.setStroke(new BasicStroke(5.0f));

        g.setColor(Color.red);
        g.drawLine(originX + width / 2, originY + height /2, originX + width , originY + height);

        g.setColor(Color.BLUE);
        g.drawLine(originX + width / 2, originY , originX + width / 2, originY + height/2);

        g.setColor(Color.green);
        g.drawLine(originX + width /2, originY + height / 2, originX + width , originY + height / 2);


        // set this panel's preferred size for auto-sizing the container JFrame
        /*public Dimension getPreferredSize() {
            return new Dimension(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        }*/

        // drawing operations should be done in this method


            for (int i = 0; i < 100; i++) {
                g.setColor(Color.BLUE);
                g.fillOval((int)  f(2 * Math.PI * i / 100)[0] + 25, (int) f(2 * Math.PI * i / 100)[1] + 15, 4, 4);
            }

            double[] posVector = f(time);

            g.setColor(Color.RED);
            g.fillOval((int) posVector[0] + 25 - 15, (int) posVector[1] + 15 - 15, 30, 30);
        }

        double[] f(double t) {
            double[] result = new double[2];
            result[0] = 400 + 200 * Math.cos(t);
            result[1] = 200 + 100 * Math.sin(t);
            return result;
        }

    }
