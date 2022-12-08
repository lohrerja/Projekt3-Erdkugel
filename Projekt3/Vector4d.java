package Projekt3;

public class Vector4d {
    public float x;
    public float y;
    public float z;
    public float w;
    public float[] vector;

    public Vector4d(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;

        this.vector = new float[4];
        this.vector[0] = x;
        this.vector[1] = y;
        this.vector[2] = z;
        this.vector[3] = w;
    }


}
