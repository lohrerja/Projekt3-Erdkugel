package Projekt3;

public class Vector3d{
    public float x;
    public float y;
    public float z;

    public float[] vector;

    public Vector3d(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;

        this.vector = new float[3];
        this.vector[0] = x;
        this.vector[1] = y;
        this.vector[2] = z;
    }

    public Vector4d getHomogeneous(){
        return new Vector4d(x,y,z,1.0f);
    }
    public float abs(){
        return (float) Math.sqrt(x*x + y*y + z*z);
    }

    public float dot(Vector3d other){
        return x*other.x + y*other.y + z*other.z;
    }

    public Vector3d rotate(Vector3d p, Vector3d q){
        //define rotation matrix columns
        Vector3d p_hat = p.div(p.abs());
        Vector3d n_hat = p.cross(q).div((p.cross(q)).abs());
        Vector3d u_hat = n_hat.cross(p_hat).div((n_hat.cross(p_hat)).abs());
        //TODO create Matrix from columns
        //TODO multiply matrix with this vector

        public Vector3d doubleCross(){
            Vector3d docr = Constants.RADIUS * Math.cos(t) * p_hat + Constants.RADIUS * Math.sin(t) * u_hat;
            return docr;
        }

    }
    public Vector3d multiply(Matrix p){
        Vector3d w = new Vector3d(0,0,0);
        int i;
        int j;
        for (i = 0, i<p.row, i++){
            for (j = 0, j<p.col, j++){
                w.vector[i] = multiplyindeces(w,i);
            }
        }
        return w;
    }

    public float multiplyindeces(Vector3d w, int i){
        float index = 0;
        assert p.col == 1;
        for (int k = 0; k < p.col; k++){
            index += p.m[i][k] * w.vector[k];
        }
        return index;
    }

    private Vector3d cross(Vector3d other) {
        return new Vector3d(y*other.z - other.y*z, z*other.x - other.z * x, x*other.y - other.x * y);
    }

    private Vector3d div(float quo) {
        return new Vector3d(x/quo, y/quo, z/quo);
    }
}
