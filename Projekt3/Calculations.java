package Projekt3;

import static Projekt3.Constants.RADIUS;

public class Calculations {

    public static float getAngle(Vector3d p, Vector3d q) {
        // cos(delta) = dot(p,q)/abs(p)*abs(q)
        float cos_delta = p.dot(q)/(p.abs() * q.abs());
        return (float) Math.acos(cos_delta);
    }

    public static Vector3d getCartesian(float phi, float theta){
        return new Vector3d((float) (RADIUS * Math.cos(theta) * Math.cos(phi)),
                (float) (Constants.RADIUS * Math.cos(theta) * Math.sin(phi)),
                (float) (Constants.RADIUS * Math.sin(theta)));
    }

    public static Vector3d rotate(Vector3d p, Vector3d q, float t) {
        //define rotation matrix columns
        Vector3d p_hat = p.div(p.abs());
        Vector3d n_hat = p.cross(q).div((p.cross(q)).abs());
        Vector3d u_hat = n_hat.cross(p_hat).div((n_hat.cross(p_hat)).abs());
        //TODO multiply matrix with this vector
        Vector3d cos_t = new Vector3d((float) (Constants.RADIUS * Math.cos(t) * p_hat.x),
                (float) (Constants.RADIUS * Math.cos(t) * p_hat.y),
                (float) (Constants.RADIUS * Math.cos(t) * p_hat.z));
        Vector3d sin_t = new Vector3d((float) (Constants.RADIUS * Math.sin(t) * u_hat.x),
                (float) (Constants.RADIUS * Math.sin(t) * u_hat.y),
                (float) (Constants.RADIUS * Math.sin(t) * u_hat.z));

        return new Vector3d(cos_t.x + sin_t.x, cos_t.y + sin_t.y, cos_t.z + sin_t.z);
    }
}
