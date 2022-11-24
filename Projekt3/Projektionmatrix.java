package Projekt3;

import java.util.Arrays;
import java.lang.Math;

import static Projekt3.Constants.*;

public class Projektionmatrix {

    double a;
    float s1;

    public Projektionmatrix(double a, float s1) {
        Matrix m = new Matrix [2][4];
        m[0][0] = (float) (-s1 * Math.sin(Math.toRadians(a)));
        m[1][0] = (float) (-s1 * Math.cos(Math.toRadians(a)));
        m[0][1] = 1;
        m[1][1] = 0;
        m[0][2] = 0;
        m[1][2] = -1;
        m[0][3] = WINDOW_WIDTH/2;
        m[1][3] = WINDOW_HEIGHT/2;

        System.out.println(Arrays.deepToString(m));
    }

    public Vector2d multiVec(Vector4d v){
         Vector2d u = new Vector2d(0,0);

        for(int i = 0; i < J.length; i++) {
            for (int j = 0; j < P[0].length; j++) {
                T[i][j] = multiplyMatricesIndeces(J, P, i, j); //interim step for multiplication
            }
        }
        return v;
    }

    public static float multiplyMatricesIndeces(float[][] J, float[][] P, int i, int j) {       //method for multiplication for Indeces
        float index = 0;
        for (int k = 0; k < P.length; k++){
            index += J[i][k] * P[k][j];
        }
        return index;
    }


}
