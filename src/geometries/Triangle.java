package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

import static primitives.Util.alignZero;

/**
 * The Triangle class represents a 3D triangle in Cartesian coordinate system using polygon.
 * It is defined by its three vertices.
 */
public class Triangle extends Polygon {

    /**
     * Constructs a Triangle object with the specified vertices.
     *
     * @param x The first vertex of the triangle.
     * @param y The second vertex of the triangle.
     * @param z The third vertex of the triangle.
     */
    public Triangle(Point x, Point y, Point z) {
        super(x, y, z); // Calls the Polygon constructor with the three vertices
    }

    /**
     * Calculates the intersection points between a given ray and the triangle.
     *
     * @param ray         The ray to intersect with the triangle.
     * @param maxDistance The maximum distance for intersection.
     * @return A list of GeoPoint objects representing the intersection points,
     * or null if there are no intersections.
     */
    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        List<GeoPoint> geoPointListFromPlane = plane.findGeoIntersections(ray, maxDistance);

        if (geoPointListFromPlane == null) {
            return null;
        }

        Vector v1 = vertices.get(0).subtract(ray.getP0());
        Vector v2 = vertices.get(1).subtract(ray.getP0());
        Vector v3 = vertices.get(2).subtract(ray.getP0());

        Vector n1 = v1.crossProduct(v2).normalize();
        Vector n2 = v2.crossProduct(v3).normalize();
        Vector n3 = v3.crossProduct(v1).normalize();

        double vn1 = alignZero(ray.getDir().dotProduct(n1));
        double vn2 = alignZero(ray.getDir().dotProduct(n2));
        double vn3 = alignZero(ray.getDir().dotProduct(n3));

        if ((vn1 > 0 && vn2 > 0 && vn3 > 0) || (vn1 < 0 && vn2 < 0 && vn3 < 0)) {
            List<GeoPoint> geoPointsTriangle = new ArrayList<>();
            for (GeoPoint geoPoint : geoPointListFromPlane) {
                geoPointsTriangle.add(new GeoPoint(this, geoPoint.point));
            }
            return geoPointsTriangle;
        }

        return null;
    }
}
