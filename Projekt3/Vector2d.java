package Projekt3;

public class Vector2d {
    public float x;
    public float y;

    public Vector2d(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public int roundX(){
        return Math.round(x);
    }

    public int roundY(){
        return Math.round(y);
    }
}
