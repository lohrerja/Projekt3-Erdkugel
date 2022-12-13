package Projekt3;

public class Matrix{
    public int col;
    public int row;
    public float[][] m;

    public Matrix(int row, int col) {
        this.row = row;
        this.col = col;
        this.m = new float[this.row][this.col];

    }
    public Matrix(Vector3d col1, Vector3d col2, Vector3d col3) {
        this.row = 3;
        this.col = 3;
        this.m = new float[this.row][this.col];

        m[0][0] = col1.x;
        m[1][0] = col1.y;
        m[2][0] = col1.z;
        m[0][1] = col2.x;
        m[1][1] = col2.y;
        m[2][1] = col2.z;
        m[0][2] = col3.x;
        m[1][2] = col3.y;
        m[2][2] = col3.z;


    }
    public Vector3d multiVec(Vector3d v){
        Vector3d u = new Vector3d(0,0,0);
        for(int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.col; j++) {
                u.vector[i] = multiplyMatricesIndeces(v, i); //interim step for multiplication
            }
        }
        return u;
    }

    public float multiplyMatricesIndeces(Vector3d v, int i) {       //method for multiplication for Indeces
        float index = 0;
        assert this.col == 3;
        for (int k = 0; k < this.col; k++){
            index += this.m[i][k] * v.vector[k];
        }
        return index;
    }
}
//not necessary but can keep
