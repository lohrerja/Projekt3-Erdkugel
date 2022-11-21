package Projekt3;

import java.util.Arrays;

import static Projekt3.Constants.WINDOW_HEIGHT;
import static Projekt3.Constants.WINDOW_WIDTH;

public class Projektionsmatrix{

    public static void main(String[] args) {
        float[][] Base = new float[2][4];
        Base[0][0] = (float) (-1.0/2.0);
        Base[1][0] = (float) (-1.0/2.0);
        Base[0][1] = 1;
        Base[1][1] = 0;
        Base[0][2] = 0;
        Base[1][2] = -1;
        Base[0][3] = WINDOW_WIDTH/2;
        Base[1][3] = WINDOW_HEIGHT/2;

        System.out.println(Arrays.deepToString(Base));
    }



}
