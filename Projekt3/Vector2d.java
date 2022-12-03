package Projekt3;

public class Vector2d {

    public float[] vector;

    public Vector2d(float x, float y) {

        this.vector = new float[2];
        this.vector[0] = x;
        this.vector[1] = y;
    }

    public int roundX(){
        return Math.round(this.vector[0]);
    }

    public int roundY(){
        return Math.round(this.vector[1]);
    }
}
