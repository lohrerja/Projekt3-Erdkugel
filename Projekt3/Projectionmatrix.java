package Projekt3;

import static Projekt3.Constants.WINDOW_HEIGHT;
import static Projekt3.Constants.WINDOW_WIDTH;

public class Projectionmatrix extends Matrix{
    double a;

    float s1;

    Matrix p;
    public Projectionmatrix(double a, float s1) {
        super(2, 4);

        this.a = a;
        this.s1 = s1;

        this.p = new Matrix(2, 4);
        this.p.m[0][0] = (float) (-this.s1 * Math.sin(Math.toRadians(this.a)));
        this.p.m[1][0] = (float) (-this.s1 * Math.cos(Math.toRadians(this.a)));
        this.p.m[0][1] = 1;
        this.p.m[1][1] = 0;
        this.p.m[0][2] = 0;
        this.p.m[1][2] = -1;
        this.p.m[0][3] = WINDOW_WIDTH / 2.0f;
        this.p.m[1][3] = WINDOW_HEIGHT / 2.0f;
    }


    public Vector2d multiVec(Vector4d v){
        Vector2d u = new Vector2d(0,0);
        for(int i = 0; i < this.p.row; i++) {
            for (int j = 0; j < this.p.col; j++) {
                u.vector[i] = multiplyMatricesIndeces(v, i); //interim step for multiplication
            }
        }
        return u;
    }

    public float multiplyMatricesIndeces(Vector4d v, int i) {       //method for multiplication for Indeces
        float index = 0;
        assert this.p.col == 4;
        for (int k = 0; k < this.p.col; k++){
            index += p.m[i][k] * v.vector[k];
        }
        return index;
    }


}
