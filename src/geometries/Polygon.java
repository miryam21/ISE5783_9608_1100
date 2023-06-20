/**
 * The Polygon class represents a two-dimensional polygon in a 3D Cartesian coordinate system.
 * It extends the Geometry class and implements methods for finding intersections with rays.
 */
package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

public class Polygon extends Geometry {
    /**
     * List of polygon's vertices
     */
    protected final List<Point> vertices;
    /**
     * Associated plane in which the polygon lies
     */
    protected final Plane plane;
    protected final int size;

    /**
     * Polygon constructor based on vertices list. The list must be ordered by edge
     * path. The polygon must be convex.
     *
     * @param vertices list of vertices according to their order by edge path
     * @throws IllegalArgumentException in any case of illegal combination of vertices:
     *                                  - Less than 3 vertices
     *                                  - Consequent vertices are in the same point
     *                                  - The vertices are not in the same plane
     *                                  - The order of vertices is not according to edge path
     *                                  - Three consequent vertices lay in the same line (180° angle between two consequent edges)
     *                                  - The polygon is concave (not convex)
     */
    public Polygon(Point... vertices) {
        if (vertices.length < 3)
            throw new IllegalArgumentException("A polygon can't have less than 3 vertices");
        this.vertices = List.of(vertices);
        size = vertices.length;

        // Generate the plane according to the first three vertices and associate the polygon with this plane.
        // The plane holds the invariant normal (orthogonal unit) vector to the polygon
        plane = new Plane(vertices[0], vertices[1], vertices[2]);
        if (size == 3) return; // no need for more tests for a Triangle

        Vector n = plane.getNormal();
        // Subtracting any subsequent points will throw an IllegalArgumentException because of Zero Vector if they are in the same point
        Vector edge1 = vertices[vertices.length - 1].subtract(vertices[vertices.length - 2]);
        Vector edge2 = vertices[0].subtract(vertices[vertices.length - 1]);

        // Cross Product of any subsequent edges will throw an IllegalArgumentException because of Zero Vector if they connect three vertices that lay in the same line.
        // Generate the direction of the polygon according to the angle between last and first edge being less than 180°. It is held by the sign of its dot product with the normal.
        // If all the rest consequent edges will generate the same sign, the polygon is convex.
        boolean positive = edge1.crossProduct(edge2).dotProduct(n) > 0;
        for (var i = 1; i < vertices.length; ++i) {
            // Test that the point is in the same plane as calculated originally
            if (!isZero(vertices[i].subtract(vertices[0]).dotProduct(n)))
                throw new IllegalArgumentException("All vertices of a polygon must lie in the same plane");
            // Test that the consequent edges have the same sign
            edge1 = edge2;
            edge2 = vertices[i].subtract(vertices[i - 1]);
            if (positive != (edge1.crossProduct(edge2).dotProduct(n) > 0))
                throw new IllegalArgumentException("All vertices must be ordered and the polygon must be convex");
        }
    }

    /**
     * Returns the normal vector to the surface of this polygon at the specified point.
     * Since this is a flat polygon, the same normal is returned regardless of the point.
     *
     * @param point The point at which to compute the normal vector.
     * @return The normal vector to the surface of this polygon at the specified point.
     */
    @Override
    public Vector getNormal(Point point) {
        return plane.getNormal();
    }

    /**
     * Helper method to find the intersections between a ray and this polygon.
     *
     * @param ray         The ray to intersect with this polygon.
     * @param maxDistance The maximum allowed distance for intersections.
     * @return A list of GeoPoint objects representing the intersections between the ray and this polygon.
     */
    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        List<GeoPoint> geoPointListFromPlane = plane.findGeoIntersectionsHelper(ray, maxDistance);

        if (geoPointListFromPlane == null) {
            return null;
        }

        Point p0 = ray.getP0();
        Point p1 = vertices.get(1);
        Point p2 = vertices.get(0);
        Vector dir = ray.getDir();

        Vector vector1 = p1.subtract(p0);
        Vector vector2 = p2.subtract(p0);

        double check = alignZero(dir.dotProduct(vector1.crossProduct(vector2)));
        if (isZero(check)) {
            return null;
        }
        boolean isPositive = check > 0;

        for (int i = vertices.size() - 1; i > 0; --i) {
            vector1 = vector2;
            vector2 = vertices.get(i).subtract(p0);
            check = alignZero(dir.dotProduct(vector1.crossProduct(vector2)));

            if (isZero(check)) {
                return null;
            }
            if (isPositive != (check > 0)) {
                return null;
            }
        }

        return List.of(new GeoPoint(this, geoPointListFromPlane.get(0).point));
    }
}
