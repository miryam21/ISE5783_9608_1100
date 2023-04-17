package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

public class Tube {
    final protected Ray axisRay;

    public Tube(Ray axisRay) {
        this.axisRay = axisRay;
    }

    public Ray getAxisRay() {
        return axisRay;
    }
    public Vector getNormal(Point point){
        return null;
    }
}
