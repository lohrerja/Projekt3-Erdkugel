package Projekt3;

public class Matrix{
    public int col;
    public int row;
    public float[][] m;

    public Matrix(int row, int col, float[][] m) {
        this.row = row;
        this.col = col;
        this.m = new float[this.row][this.col];

    }
}
//not necessary but can keep
