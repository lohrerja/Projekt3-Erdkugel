package Projekt3;

import java.util.Arrays;

public class Matrix{
    public Matrix(int col, int row){

        float[][] m = new float[col][row];      //Java coordinate system
        m[0][0] = 1;
        m[1][0] = 0;
        m[0][1] = 0;
        m[1][1] = -1;
        System.out.println(Arrays.deepToString(m));

        float[][] P = new float[2][3];      //Projectionmatrix
        P[0][0] = (float) (-1.0/2.0);
        P[1][0] = (float) (-1.0/2.0);
        P[0][1] = 1;
        P[1][1] = 0;
        P[0][2] = 0;
        P[1][2] = 1;
        System.out.println(Arrays.deepToString(P));

        float[][] M = multiMatrix(m, P);    //Method for Matrix M
        System.out.println(Arrays.deepToString(M));     //adjusted projectionmatrix for java
    }


    public static float[][] multiMatrix(float[][] J, float[][] P) {     //Method for Matrixmultiplication

        float[][] T = new float[J.length][P[0].length];

        for(int i = 0; i < J.length; i++) {
            for (int j = 0; j < P[0].length; j++) {
                T[i][j] = multiplyMatricesIndeces(J, P, i, j); //interim step for multiplication
            }
        }
        return T;
    }

    public static float multiplyMatricesIndeces(float[][] J, float[][] P, int i, int j) {       //method for multiplication for Indeces
        float index = 0;
        for (int k = 0; k < P.length; k++){
            index += J[i][k] * P[k][j];
        }
        return index;
    }
}
//not necessairy but can keep
