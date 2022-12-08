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
        Vector3d u_hat = n_hat.cross(p).div((n_hat.cross(p)).abs());
        //TODO create Matrix from columns
        //TODO multiply matrix with this vector
        return ;
    }

    private Vector3d cross(Vector3d other) {
        return new Vector3d(y*other.z - other.y*z, z*other.x - other.z * x, x*other.y - other.x * y);
    }

    private Vector3d div(float quo) {
        return new Vector3d(x/quo, y/quo, z/quo);
    }
}
