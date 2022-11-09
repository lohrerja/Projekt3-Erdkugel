package Projekt3;

import javax.swing.*;

public class Main {

    public static void main( String[] args ) {

        JFrame f = new JFrame();

        f.setTitle("Willkommen zum ersten Workshop im Seminar Mathematik und Simulation");
        f.setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        f.setLocation(2,2);
        f.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        f.add( new GraphicsContent() );
        f.setVisible( true );

    }

}
