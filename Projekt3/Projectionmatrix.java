package Projekt3;

import static Projekt3.Constants.WINDOW_HEIGHT;
import static Projekt3.Constants.WINDOW_WIDTH;

public class Projectionmatrix extends Matrix{
    double a;

    float s1;
    public Projectionmatrix(double a, float s1) {
        super(2, 4, m);

        this.a = a;
        this.s1 = s1;

        Matrix p = new Matrix(2, 4, m);
        m[0][0] = (float) (-this.s1 * Math.sin(Math.toRadians(this.a)));
        m[1][0] = (float) (-this.s1 * Math.cos(Math.toRadians(this.a)));
        m[0][1] = 1;
        m[1][1] = 0;
        m[0][2] = 0;
        m[1][2] = -1;
        m[0][3] = WINDOW_WIDTH / 2;
        m[1][3] = WINDOW_HEIGHT / 2;
    }


    public Vector2d multiVec(Vector4d v, Matrix m){
        Vector2d u = new Vector2d(0,0);
        for(int i = 0; i < m.length; i++) {
            for (int j = 0; j < 1; j++) {
                u = multiplyMatricesIndeces(m, v, i, j); //interim step for multiplication
            }
        }
        return u;
    }

    public static float multiplyMatricesIndeces(Matrix m, Vector4d v, int i, int j) {       //method for multiplication for Indeces
        float index = 0;
        for (int k = 0; k < m.length; k++){
            index += m[i][k] * v;
        }
        return index;
    }


}
