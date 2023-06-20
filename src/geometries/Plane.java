/**
 * The Plane class represents a plane geometry in three-dimensional space.
 * It extends the Geometry class and implements methods for finding intersections with rays.
 */
package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

public class Plane extends Geometry {
    final protected Point point;
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
    public Plane(Point x, Point y, Point z) {
        this.point = x;

        Vector u = x.subtract(y);
        Vector v = x.subtract(z);
        Vector n = u.crossProduct(v);

        this.normal = n.normalize();
    }

    /**
     * Constructs a Plane object from a point and a normal vector.
     *
     * @param point  A point on the plane.
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

    /**
     * Returns the point on this plane.
     *
     * @return The point on this plane.
     */
    public Point getPoint() {
        return point;
    }

    /**
     * Finds the intersection points between a given ray and this plane.
     *
     * @param ray         The ray to intersect with this plane.
     * @param maxDistance The maximum distance for valid intersections.
     * @return A list of GeoPoint objects representing the intersection points, or null if no intersections found.
     */
    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {

        // If the ray starts in p0, there is no intersection
        if (point.equals(ray.getP0())) return null;

        Vector qMinusP0 = point.subtract(ray.getP0());
        double nQMinusP0 = normal.dotProduct(qMinusP0);
        double nv = normal.dotProduct(ray.getDir());

        // If v is orthogonal to the normal, v is parallel to the plane
        if (isZero(nv)) {
            return null;
        }

        double t = alignZero(nQMinusP0 / nv);

        // If there is an intersection point
        if (t > 0d) {
            if (alignZero(t - maxDistance) <= 0) {
                List<GeoPoint> list = new ArrayList<>();
                list.add(new GeoPoint(this, ray.getPoint(t)));
                return list;
            }
        }

        // If t <= 0, the ray starts after the plane
        return null;
    }


}
