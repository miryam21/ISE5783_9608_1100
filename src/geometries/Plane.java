package geometries;

import primitives.Point;
import primitives.Vector;

public class Plane implements Geometry {
    final protected Point  point;
    final protected Vector normal;

    /**
     * Constructs a Plane object from three points on the plane.
     * The normal to the plane is calculated as the cross product
     * of the two vectors connecting the first point to the other two.
     *
     * @param x The first point on the plane.
     * @param y The second point on the plane.
     * @param z The third point on the plane.
     */
    public Plane(Point x, Point y, Point z){
        this.point = x;

        Vector u = x.subtract(y);
        Vector v = x.subtract(z);
        Vector n = u.crossProduct(v);

        this.normal = n.normalize();
    }

    /**
     * Constructs a Plane object from a point and a normal vector.
     *
     * @param point A point on the plane.
     * @param normal The normal vector to the plane.
     */
    public Plane(Point point, Vector normal) {
        this.point = point;
        this.normal = normal.normalize();
    }

    /**
     * Returns the normal vector to this plane.
     *
     * @return The normal vector to this plane.
     */
    public Vector getNormal() {
        return normal;
    }

    /**
     * Returns the normal vector to the surface of this plane at the specified point.
     * Since this is a flat plane, the same normal is returned regardless of the point.
     *
     * @param point The point at which to compute the normal vector.
     * @return The normal vector to the surface of this plane at the specified point.
     */
    @Override
    public Vector getNormal(Point point) {
        return getNormal();
    }
}
