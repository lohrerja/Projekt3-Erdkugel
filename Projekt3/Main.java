package Projekt3;

import javax.swing.*;

public class Main {

    public static void main( String[] args ) {

        JFrame f = new JFrame();

        f.setTitle("Projekt3 - Flugzeugstrecke");
        f.setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        f.setLocation(2,2);
        f.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        f.setVisible( true );

        Animation animation = null;
        animation = new GraphicsContent();
        animation.start();

    }

}
