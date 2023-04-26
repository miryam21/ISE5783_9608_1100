package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * The Sphere class represents a 3D sphere in Cartesian coordinate system.
 * It is defined by its radius and center point.
 */
public class Sphere extends RadialGeometry {
    final protected Point center; // The center point of the sphere

    /**
     * Constructs a sphere with the specified radius and center point.
     * @param radius The radius of the sphere.
     * @param center The center point of the sphere.
     */
    public Sphere(double radius, Point center) {
        super(radius); // Calls the RadialGeometry constructor to set the radius
        this.center = center; // Sets the center point of the sphere
    }

    /**
     * Returns the center point of the sphere.
     * @return The center point of the sphere.
     */
    public Point getCenter() {
        return center;
    }

    /**
     * Returns the normal vector to the sphere at the specified point.
     * @param point The point on the sphere to get the normal vector at.
     * @return The normal vector to the sphere at the specified point.
     */
    @Override
    public Vector getNormal(Point point) {
       return center.subtract(point).normalize();
    }
}
