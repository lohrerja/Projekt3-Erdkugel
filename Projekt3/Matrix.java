package Projekt3;

import java.util.Arrays;

public class Matrix {
    public static void main(String[] args){
        float[][] M= new float[3][2];
        M[0][0] = (float) (-1.0/2.0);
        M[0][1] = (float) (1.0/2.0);
        M[1][0] = 1;
        M[1][1] = 0;
        M[2][0] = 0;
        M[2][1] = -1;
        System.out.println(Arrays.deepToString(M));
    }
}
