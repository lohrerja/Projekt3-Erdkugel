package Projekt3;

import javax.swing.*;
import javax.swing.text.Position;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import utils.ApplicationTime;

import static Projekt3.Constants.*;

public class GraphicsContent extends Animation {
    //ActionEventListener hat die ähnliche Funktion wie AddEventListener bei JS

    static JButton playButton = new JButton();
    static JButton pauseButton = new JButton();
    static JButton resetButton = new JButton();

    GraphicsContentPanel graphics;

    @Override
    protected ArrayList<JFrame> createFrames(ApplicationTime applicationTimeThread) {
        // a list of all frames (windows) that will be shown
        ArrayList<JFrame> frames = new ArrayList<>();

        // Create main frame (window)

        JFrame frame = new JFrame("Projekt3 - Flugzeugstrecke");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new GraphicsContentPanel(applicationTimeThread);

        //start button
        playButton.setText("Play");
        playButton.setSize(100, 50);
        playButton.setLocation(0, 60);
        playButton.addActionListener(new ControlButtons(playButton, frame, applicationTimeThread));

        //stop button
        pauseButton.setText("Pause");
        pauseButton.setSize(100, 50);
        pauseButton.setLocation(0, 120);
        pauseButton.addActionListener(new ControlButtons(pauseButton, frame, applicationTimeThread));

        //Reset button
        resetButton.setText("Reset");
        resetButton.setSize(100, 50);
        resetButton.setLocation(0, 0);
        resetButton.addActionListener(new ControlButtons(resetButton, frame, applicationTimeThread));

        graphics = new GraphicsContentPanel(applicationTimeThread);

        frame.add(playButton);
        frame.add(pauseButton);
        frame.add(resetButton);
        frame.add(graphics);
        frame.add(panel);

        frame.pack(); // adjusts size of the JFrame to fit the size of it's components
        frame.setVisible(true);
        frame.setLocation(2,2);

        frames.add(frame);
        return frames;
    }
}
class ControlButtons implements ActionListener {
    JButton button;
    JFrame frame;
    ApplicationTime applicationTimeThread;

    public ControlButtons(JButton button, JFrame frame, ApplicationTime applicationTimeThread) {
        this.button = button;
        this.frame = frame;
        this.applicationTimeThread = applicationTimeThread;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (button.equals(GraphicsContent.pauseButton)) {
            applicationTimeThread.pauseTime();
            System.out.println("Pause pressed");
        } else if (button.equals(GraphicsContent.resetButton)) {
            applicationTimeThread.resetTime();
            System.out.println("Reset pressed"); //WIP
        } else if (button.equals(GraphicsContent.playButton)) {
            applicationTimeThread.continueTime();
            System.out.println("Continue pressed");
        }
    }
}


class GraphicsContentPanel extends JPanel {

    private final ApplicationTime t;
    public double time;

    public GraphicsContentPanel(ApplicationTime thread) {
        this.t = thread;
    }

    public Dimension getPreferredSize() {
        return new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT);
    }

    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        time = t.getTimeInSeconds();
        Graphics2D g2d;
        g2d = (Graphics2D) g;

        //intension to draw axis: define 3d points
        g.setColor(Color.red);

        Vector4d origin = new Vector4d(0, 0, 0, 1);

        Vector4d x_axis = new Vector4d(100, 0, 0, 1);
        // get projection from projectionmatrix

        Projectionmatrix p = new Projectionmatrix(135.0, (float) (1.0f / Math.sqrt(2.0f)));

        Vector2d x_point = p.multiVec(x_axis);
        Vector2d origin2d = p.multiVec(origin);

        g.drawLine(origin2d.roundX(), origin2d.roundY(), x_point.roundX(), x_point.roundY());


        g.setColor(Color.green);
        Vector4d base = new Vector4d(0, 0, 0, 1);

        Vector4d y_axis = new Vector4d(0, 100, 0, 1);
        // get projection from projectionmatrix

        Vector2d y_point = p.multiVec(y_axis);
        Vector2d base2d = p.multiVec(base);

        g.drawLine(base2d.roundX(), base2d.roundY(), y_point.roundX(), y_point.roundY());


        g.setColor(Color.blue);
        Vector4d begin = new Vector4d(0, 0, 0, 1);

        Vector4d z_axis = new Vector4d(0, 0, 100, 1);
        // get projection from projectionmatrix

        Vector2d z_point = p.multiVec(z_axis);
        Vector2d begin2d = p.multiVec(begin);

        g.drawLine(begin2d.roundX(), begin2d.roundY(), z_point.roundX(), z_point.roundY());


        // drawing operations should be done in this method
        //use same projectionmatrix as above: create a calculation class
        g2d.setStroke(new BasicStroke(2.0f));

        g.setColor(Color.lightGray);

        float step = (float) (2 * Math.PI / Constants.NUMSTEPS);
        float rep = (float) (2 * Math.PI / Constants.REPEAT);


        //phi = horizontal; tet = vertical

        //vertical
        for (float phi = 0; phi <= 2 * Math.PI; phi = phi + rep) {
            for (float tet = 0; tet <= 2 * Math.PI; tet = tet + step) {
                Vector3d ver = Calculations.getCartesian(phi, tet);
                Vector4d ver_hom = ver.getHomogeneous();
                Vector2d ver_2d = p.multiVec(ver_hom);

                g.fillOval(ver_2d.roundX(), ver_2d.roundY(), 4, 4);
            }
        }

        //horizontal
        for (float phi = 0; phi <= 2 * Math.PI; phi = phi + step) {
            for (float tet = (float) (-0.5f * Math.PI); tet <= 0.5f * Math.PI; tet = tet + rep) {
                Vector3d hor = Calculations.getCartesian(phi, tet);
                Vector4d hor_hom = hor.getHomogeneous();
                Vector2d ver_2d = p.multiVec(hor_hom);

                g.fillOval(ver_2d.roundX(), ver_2d.roundY(), 4, 4);
            }
        }

        //Äquator
        g.setColor(Color.CYAN);
        for (float phi = 0; phi <= 2 * Math.PI; phi = phi + step) {

            Vector3d aequ = Calculations.getCartesian(phi, 0);
            Vector4d aequ_hom = aequ.getHomogeneous();
            Vector2d aequ_2d = p.multiVec(aequ_hom);

            g.fillOval(aequ_2d.roundX(), aequ_2d.roundY(), 4, 4);
        }


        //in front
        g.setColor(Color.gray);
        //vertical
        for (float phi = (float) (-2.0f / 7.0f * Math.PI); phi <= 4.0f / 7.0f * Math.PI; phi = phi + rep) {//set it to -0.periot 3
            for (float tet = (float) (-0.5f * Math.PI); tet <= 0.5f * Math.PI; tet = tet + step) {
                Vector3d verfro = Calculations.getCartesian(phi, tet);
                Vector4d verfro_hom = verfro.getHomogeneous();
                Vector2d verfro_2d = p.multiVec(verfro_hom);

                g.fillOval(verfro_2d.roundX(), verfro_2d.roundY(), 4, 4);
            }
        }

        //horizontal
        for (float phi = (float) (-0.3f * Math.PI); phi <= 0.7f * Math.PI; phi = phi + step) {
            for (float tet = (float) (-0.5f * Math.PI); tet <= 0.5f * Math.PI; tet = tet + rep) {
                Vector3d horfro = Calculations.getCartesian(phi, tet);
                Vector4d horfro_hom = horfro.getHomogeneous();
                Vector2d horfro_2d = p.multiVec(horfro_hom);

                g.fillOval(horfro_2d.roundX(), horfro_2d.roundY(), 4, 4);
            }
        }

        //aequator
        g.setColor(Color.BLUE);
        for (float phi = (float) (-0.3f * Math.PI); phi <= 0.7f * Math.PI; phi = phi + step) {

            Vector3d aefro = Calculations.getCartesian(phi, 0);
            Vector4d aefro_hom = aefro.getHomogeneous();
            Vector2d aefro_2d = p.multiVec(aefro_hom);

            g.fillOval(aefro_2d.roundX(), aefro_2d.roundY(), 4, 4);
        }

        //coordinats


        g.setColor(Color.RED);

        //point P
        float P_long = 0.5f;
        float P_lati = -1.5f;

        Vector3d p_full = Calculations.getCartesian(P_long, P_lati);
        Vector4d p_homogeneous = p_full.getHomogeneous();
        Vector2d p_draw = p.multiVec(p_homogeneous);

        g.fillOval(p_draw.roundX() - 5, p_draw.roundY() - 5, 10, 10);
        g.drawString("P", p_draw.roundX() - 15, p_draw.roundY() - 15);

        //point Q
        float q_long = -1.0f;
        float q_lati = 0.5f;

        Vector3d q_full = Calculations.getCartesian(q_long, q_lati);
        Vector4d q_homogeneous = q_full.getHomogeneous();
        Vector2d q_draw = p.multiVec(q_homogeneous);

        g.fillOval(q_draw.roundX() - 5, q_draw.roundY() - 5, 10, 10);
        g.drawString("Q", q_draw.roundX() - 15, q_draw.roundY() - 15);


        //compute angle delta between P and Q
        float delta = Calculations.getAngle(p_full, q_full);
        //System.out.println(delta);

        // get distance between p and q along great circle
        float distance = (float) (Constants.RADIUS * delta);

        //todo buttons
        double startTime = 0.0;//start button sets it on now
        double endTime = 7.0;//stop button sets startime on a number that never starts
        double duration = endTime - startTime;//slider to adjust speed through endtime

        //draw curve along great circle
        for (float t = 0; t <= time - startTime; t = t + step) {

            float part = (float) (t / duration) * delta;

            if (part > delta) {

                part = delta;
            }

            Vector3d cur = Calculations.rotate(p_full, q_full, part);
            Vector4d cur_hom = cur.getHomogeneous();
            Vector2d cur_2d = p.multiVec(cur_hom);

            g.fillOval(cur_2d.roundX(), cur_2d.roundY(), 4, 4);

        }
    }

    double[] f(double t, float X, float Y) {

        double[] result = new double[2];

        result[0] = X + 10 * Math.cos(t);//desides horizontal
        result[1] = Y + 10 * Math.sin(t);//desides vertical

        return result;
    }
}


