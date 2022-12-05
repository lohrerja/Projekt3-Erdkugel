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

        //intension to draw axis: define 3d points
        g.setColor(Color.red);

        Vector4d origin = new Vector4d(0,0,0,1);

        Vector4d x_axis = new Vector4d(100, 0,0, 1);
        // get projection from projectionmatrix

        Projectionmatrix p = new Projectionmatrix(135.0, (float) (1.0f/Math.sqrt(2.0f)));

        Vector2d x_point = p.multiVec(x_axis);
        Vector2d origin2d = p.multiVec(origin);

        g.drawLine(origin2d.roundX(), origin2d.roundY(), x_point.roundX(), x_point.roundY());


        g.setColor(Color.green);
        Vector4d base = new Vector4d(0,0,0,1);

        Vector4d y_axis = new Vector4d(0, 100,0, 1);
        // get projection from projectionmatrix

        Vector2d y_point = p.multiVec(y_axis);
        Vector2d base2d = p.multiVec(base);

        g.drawLine(base2d.roundX(), base2d.roundY(), y_point.roundX(), y_point.roundY());


        g.setColor(Color.blue);
        Vector4d begin = new Vector4d(0,0,0,1);

        Vector4d z_axis = new Vector4d(0, 0,100, 1);
        // get projection from projectionmatrix

        Vector2d z_point = p.multiVec(z_axis);
        Vector2d begin2d = p.multiVec(begin);

        g.drawLine(begin2d.roundX(), begin2d.roundY(), z_point.roundX(), z_point.roundY());





        // drawing operations should be done in this method
        //use same projectionmatrix as above: create a calculation class
        g2d.setStroke(new BasicStroke(2.0f));

        g.setColor(Color.lightGray);//TODO create a funktion for Vektor4d that is in all
        //TODO create a variable for how often it will be around an axis

        float step = (float) (2*Math.PI/ Constants.NUMSTEPS);
        float rep = (float) (2*Math.PI/ Constants.REPEAT);
        int r = 200;
        //phi = horizontal; tet = vertical

        //vertical
        for(float phi = 0; phi <= 2*Math.PI; phi = phi + rep){
            for(float tet = 0; tet <= 2*Math.PI; tet = tet + step){
                Vector4d e = new Vector4d((float) (r * Math.cos(tet) * Math.cos(phi)), (float) (r * Math.cos(tet) * Math.sin(phi)), (float) (r * Math.sin(tet)),1);

                Vector2d e2d = p.multiVec(e);

                g.fillOval(e2d.roundX(), e2d.roundY(), 4, 4);
            }
        }

        //horizontal
        for(float phi = 0; phi <= 2*Math.PI; phi = phi + step){
            for(float tet = -0.5f; tet <= 0.5f*Math.PI; tet = tet + rep){
                Vector4d u = new Vector4d((float) (r * Math.cos(tet) * Math.cos(phi)), (float) (r * Math.cos(tet) * Math.sin(phi)), (float) (r * Math.sin(tet)),1);

                Vector2d u2d = p.multiVec(u);

                g.fillOval(u2d.roundX(), u2d.roundY(), 4, 4);
            }
        }

        //Äquator
        g.setColor(Color.CYAN);
        for(float phi = 0; phi <= 2*Math.PI; phi = phi + step){

            Vector4d ae = new Vector4d((float) (r * Math.cos(phi)), (float) (r * Math.sin(phi)), (float) (0),1);

            Vector2d ae2d = p.multiVec(ae);

            g.fillOval(ae2d.roundX(), ae2d.roundY(), 4, 4);
        }


        //in front

        g.setColor(Color.BLUE);
        for(float phi = (float) (-0.3f*Math.PI); phi <= 0.7f*Math.PI; phi = phi + step){

            Vector4d ae = new Vector4d((float) (r * Math.cos(phi)), (float) (r * Math.sin(phi)), (float) (0),1);

            Vector2d ae2d = p.multiVec(ae);

            g.fillOval(ae2d.roundX(), ae2d.roundY(), 4, 4);
        }
        g.setColor(Color.gray);

        //vertical
        for(float phi = (float) (-1.0f/3.0f*Math.PI); phi <= 2.0f/3.0f*Math.PI; phi = phi + rep){//set it to -0.periot 3
            for(float tet = (float) (-0.5f*Math.PI); tet <= 0.5f*Math.PI; tet = tet + step){
                Vector4d e = new Vector4d((float) (r * Math.cos(tet) * Math.cos(phi)), (float) (r * Math.cos(tet) * Math.sin(phi)), (float) (r * Math.sin(tet)),1);

                Vector2d e2d = p.multiVec(e);

                g.fillOval(e2d.roundX(), e2d.roundY(), 4, 4);
            }
        }

        //horizontal
        for(float phi = (float) (-0.3f*Math.PI); phi <= 0.7f*Math.PI; phi = phi + step){
            for(float tet = -0.5f; tet <= 0.5f*Math.PI; tet = tet + rep){
                Vector4d u = new Vector4d((float) (r * Math.cos(tet) * Math.cos(phi)), (float) (r * Math.cos(tet) * Math.sin(phi)), (float) (r * Math.sin(tet)),1);

                Vector2d u2d = p.multiVec(u);

                g.fillOval(u2d.roundX(), u2d.roundY(), 4, 4);
            }
        }


        double[] posVector = f(time);

        g.setColor(Color.RED);
        g.fillOval((int) posVector[0] + base2d.roundX() , (int) posVector[1]+base2d.roundY(), 20, 20);
    }

    double[] f(double t) {

        Projectionmatrix p = new Projectionmatrix(135.0, (float) (1.0f/Math.sqrt(2.0f)));
        float step = (float) (Math.PI/ Constants.NUMSTEPS);
        float rep = (float) (Math.PI/ Constants.REPEAT);
        int r = 200;

        double[] result = new double[2];
        for(float phi = 0; phi <= Math.PI; phi = phi + rep){
            for(float tet = 0; tet <= Math.PI; tet = tet + step){
                Vector4d e = new Vector4d((float) (Math.cos(tet) * Math.cos(phi)), (float) ( Math.cos(tet) * Math.sin(phi)), (float) ( Math.sin(tet)), 1);

                Vector2d e2d = p.multiVec(e);

                result[0] = e2d.roundX()/2 + e2d.roundY()/2 * Math.cos(t);
                result[1] = e2d.roundX()/2 + e2d.roundY()/2 * Math.sin(t);
            }
        }
        return result;
    }

    }


