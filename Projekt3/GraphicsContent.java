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
        g.setColor(Color.BLACK);

        Vector4d origin = new Vector4d(0,0,0,1);

        Vector4d x_axis = new Vector4d(1, 0,0, 1);
        // get projection from projectionmatrix

        Projektionmatrix p = new Projektionmatrix( 135, (int) (1.0/Math.sqrt(2.0)));

        Vector2d x_point = p.multiVec(x_axis);
        Vector2d origin2d = p.multiVec(origin);

        g.drawLine(origin2d.roundX(), origin2d.roundY(), x_point.roundX(), x_point.roundY());


        Vector4d base = new Vector4d(0,0,0,1);

        Vector4d y_axis = new Vector4d(0, 1,0, 1);
        // get projection from projectionmatrix

        Vector2d y_point = p.multiVec(y_axis);
        Vector2d base2d = p.multiVec(base);

        g.drawLine(base2d.roundX(), base2d.roundY(), y_point.roundX(), y_point.roundY());


        Vector4d begin = new Vector4d(0,0,0,1);

        Vector4d z_axis = new Vector4d(0, 0,1, 1);
        // get projection from projectionmatrix

        Vector2d z_point = p.multiVec(z_axis);
        Vector2d begin2d = p.multiVec(begin);

        g.drawLine(begin2d.roundX(), begin2d.roundY(), z_point.roundX(), z_point.roundY());

        /*g.setColor(Color.BLACK);
        g.drawLine(originX, originY + height / 2, originX + width - 1, originY + height / 2);

        g.setColor(Color.BLACK);
        g.drawLine(originX + width / 2, originY , originX + width / 2, originY + height);*/


        g.setColor(Color.red);

        Vector4d start = new Vector4d(0.5F,0,0,1);

        Vector2d start2d = p.multiVec(start);

        g.drawLine(start2d.roundX(), start2d.roundY(), x_point.roundX(), x_point.roundY());


        g.setColor(Color.green);

        Vector4d satan = new Vector4d(0,0.5F,0,1);

        Vector2d satan2d = p.multiVec(satan);

        g.drawLine(satan2d.roundX(), satan2d.roundY(), y_point.roundX(), y_point.roundY());


        g.setColor(Color.blue);

        Vector4d beginning = new Vector4d(0,0, 0.5F,1);

        Vector2d beginning2d = p.multiVec(beginning);

        g.drawLine(beginning2d.roundX(), beginning2d.roundY(), z_point.roundX(), z_point.roundY());

        //to check if it's the center
        Vector4d b = new Vector4d(0,0, 0,1);

        Vector2d b2d = p.multiVec(b);

        g.fillOval(b2d.roundX(),b2d.roundY(),2,2);

        /*g2d.setStroke(new BasicStroke(3.0f));

        g.setColor(Color.BLUE);
        g.drawLine(originX + width / 2, originY , originX + width / 2, originY + height/2);

        g.setColor(Color.green);
        g.drawLine(originX + width /2, originY + height / 2, originX + width , originY + height / 2);

        g.setColor(Color.red);
        g.drawLine(originX + width / 2, originY + height /2, originX + width , originY + height);*/



        /*g.setColor(Color.BLACK);
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
        g.drawString(message, originX + width * 6/13 - 5, originY + height * 5/12 - 5);*/



        // drawing operations should be done in this method
        //use same projectionmatrix as above: create a calculation class
        g2d.setStroke(new BasicStroke(2.0f));

        g.setColor(Color.lightGray);
        g.drawOval(0,0,400,100);

        int r = 1;
        //vertical
        for(float phi = 0; phi == 0; phi++){
            for(float tet = 0; tet <= 2*Math.PI; tet++){
                Vector4d w = new Vector4d((float) (r * Math.cos(tet) * Math.cos(phi)), (float) (r * Math.cos(tet) * Math.sin(phi)), (float) (r * Math.sin(tet)),1);

                Vector2d w2d = p.multiVec(w);

                g.fillOval(w2d.roundX(), w2d.roundY(), 4, 4);
            }
        }

        for(float phi = 0; phi <= 2; phi++){
            for(float tet = 0; tet <= 2*Math.PI; tet++){
                Vector4d e = new Vector4d((float) (r * Math.cos(tet) * Math.cos(phi)), (float) (r * Math.cos(tet) * Math.sin(phi)), (float) (r * Math.sin(tet)),1);

                Vector2d e2d = p.multiVec(e);

                g.fillOval(e2d.roundX(), e2d.roundY(), 4, 4);

            }
        }

        for(float phi = 0; phi >= -2; phi--){
            for(float tet = 0; tet <= 2*Math.PI; tet++){
                Vector4d t = new Vector4d((float) (r * Math.cos(tet) * Math.cos(phi)), (float) (r * Math.cos(tet) * Math.sin(phi)), (float) (r * Math.sin(tet)),1);

                Vector2d t2d = p.multiVec(t);

                g.fillOval(t2d.roundX(), t2d.roundY(), 4, 4);
            }
        }
        //horizontal
        for(float phi = 0; phi <= 2*Math.PI; phi++){
            for(float tet = 0; tet <= 2 ; tet++){
                Vector4d u = new Vector4d((float) (r * Math.cos(tet) * Math.cos(phi)), (float) (r * Math.cos(tet) * Math.sin(phi)), (float) (r * Math.sin(tet)),1);

                Vector2d u2d = p.multiVec(u);

                g.fillOval(u2d.roundX(), u2d.roundY(), 4, 4);
            }
        }

        for(float phi = 0; phi <= 2*Math.PI; phi++) {
            for (float tet = 0; tet >= -2 ; tet--) {
                Vector4d s = new Vector4d((float) (r * Math.cos(tet) * Math.cos(phi)), (float) (r * Math.cos(tet) * Math.sin(phi)), (float) (r * Math.sin(tet)),1);

                Vector2d s2d = p.multiVec(s);

                g.fillOval(s2d.roundX(), s2d.roundY(), 4, 4);
            }
        }

        //Äquator
        for(float phi = 0; phi <= 2*Math.PI; phi++){
            g.setColor(Color.BLUE);

            Vector4d ae = new Vector4d((float) (r * Math.cos(phi)), (float) (r * Math.sin(phi)), (float) (0),1);

            Vector2d ae2d = p.multiVec(ae);

            g.fillOval(ae2d.roundX(), ae2d.roundY(), 4, 4);
        }






        g.setColor(Color.darkGray);
        g.drawArc(0,0,400,100, -180, 180);

        }

}
