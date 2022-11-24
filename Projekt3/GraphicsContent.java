package Projekt3;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import utils.ApplicationTime;

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
        //intension to draw axis: define 3d points

        Vector4d origin = new Vector4d(0,0,0,1);

        Vector4d x_axis = new Vector4d(1, 0,0, 1);
        // get projection from projectionmatrix

        Projektionmatrix p = new Projektionmatrix(135, 1.0/Math.sqrt(2.0));

        Vector2d x_point = p.multiVec(x_axis);
        Vector2d origin2d = p.multiVec(origin);

        g.drawLine(origin2d.roundX(), origin2d.roundY(), x_point.roundX(), x_point.roundY());

        /*g.setColor(Color.BLACK);
        g.drawLine(originX, originY + height / 2, originX + width - 1, originY + height / 2);

        g.setColor(Color.BLACK);
        g.drawLine(originX + width / 2, originY , originX + width / 2, originY + height);*/

        g.setColor(Color.BLACK);
        g.drawLine(originX + width * 5/12, originY + height/2, originX + width * 5/12, originY + height * 8/15 );
        g.setFont(new Font("Arial", Font.PLAIN, 10));
        String subtitle = "-0,2";
        g.drawString(subtitle, originX + width * 5/12 - 5, originY + height * 7/13 + 5);

        g.setColor(Color.BLACK);
        g.drawLine(originX + width * 7/12, originY + height/2, originX + width * 7/12, originY + height * 8/15 );
        g.setFont(new Font("Serif", Font.PLAIN, 10));
        String message = "0,2";
        g.drawString(message, originX + width * 7/12 - 5, originY + height * 7/13 + 5);

        g.setColor(Color.BLACK);
        g.drawLine(originX + width * 6/13, originY + height * 7/12, originX + width / 2, originY + height * 7/12 );
        g.drawString(subtitle, originX + width * 6/13 - 5, originY + height * 7/12 - 5);

        g.setColor(Color.BLACK);
        g.drawLine(originX + width * 6/13, originY + height * 5/12, originX + width / 2, originY + height * 5/12 );
        g.setFont(new Font("Arial", Font.PLAIN, 10));
        g.drawString(message, originX + width * 6/13 - 5, originY + height * 5/12 - 5);


        /*g2d.setStroke(new BasicStroke(3.0f));

        g.setColor(Color.BLUE);
        g.drawLine(originX + width / 2, originY , originX + width / 2, originY + height/2);

        g.setColor(Color.green);
        g.drawLine(originX + width /2, originY + height / 2, originX + width , originY + height / 2);

        g.setColor(Color.red);
        g.drawLine(originX + width / 2, originY + height /2, originX + width , originY + height);*/


        // drawing operations should be done in this method
        //use same projectionmatrix as above: create a calculation class
        g2d.setStroke(new BasicStroke(2.0f));

        g.setColor(Color.lightGray);
        g.drawOval(0,0,400,100);

        g.setColor(Color.gray);
        g.drawArc(0,0,400,100, -180, 180);

        }

    }
