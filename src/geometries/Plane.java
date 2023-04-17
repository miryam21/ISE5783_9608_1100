package geometries;

import primitives.Point;
import primitives.Vector;
/*
try
 */
public class Plane implements Geometry {
     final protected Point  point;
   final protected Vector normal;
public Plane(Point x, Point y, Point z){
    this.point=x;

    Vector u=x.subtract(y);
    Vector v=x.subtract(z);
    Vector n=u.crossProduct(v);

    this.normal=n.normalize();

}
    public Plane(Point point, Vector normal) {
        this.point = point;
        this.normal = normal.normalize();
    }

    public Vector getNormal() {
        return normal;
    }

    @Override
    public Vector getNormal(Point point) {
        return getNormal();
    }
}
