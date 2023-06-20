package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 * The Tube class represents an infinite cylinder (a tube) in Cartesian coordinate system.
 * It is defined by its axis ray (the ray that passes through the center of the tube) and radius.
 */
public class Tube extends RadialGeometry {

    final protected Ray axisRay; // The axis ray of the tube

    /**
     * Constructs a tube with the specified axis ray.
     *
     * @param axisRay The axis ray of the tube.
     * @param radius  The radius of the tube.
     */
    public Tube(Ray axisRay, double radius) {
        super(radius);
        this.axisRay = axisRay; // Sets the axis ray of the tube
    }

    /**
     * Returns the axis ray of the tube.
     *
     * @return The axis ray of the tube.
     */
    public Ray getAxisRay() {
        return axisRay;
    }

    /**
     * Returns the normal vector to the tube at the specified point.
     *
     * @param point The point on the tube to get the normal vector at.
     * @return The normal vector to the tube at the specified point.
     */
    @Override
    public Vector getNormal(Point point) {
        Vector u = point.subtract(axisRay.getP0());
        double t = axisRay.getDir().dotProduct(u);
        if (t == 0)
            return u;
        Point o = axisRay.getP0().add(axisRay.getDir().scale(t));
        return point.subtract(o);
    }

    /**
     * Finds the intersections between the tube and a given ray.
     *
     * @param ray         The ray to intersect with the tube.
     * @param maxDistance The maximum distance to check for intersections.
     * @return A list of GeoPoint objects representing the intersections between the tube and the ray.
     */
    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        return null;
    }
}
