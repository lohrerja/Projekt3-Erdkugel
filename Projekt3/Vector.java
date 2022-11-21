package Projekt3;

import java.util.Arrays;

public class Vector extends Constants{
    public float x;
    public float y;

    public Vector(float x, float y) {
        this.x = x;
        this.y = y;

        float[][] a= new float[2][1];
        a[0][0] = x;
        a[1][0] = y;
    }

    Vector v = new Vector(WINDOW_WIDTH/2, WINDOW_HEIGHT/2);
}
