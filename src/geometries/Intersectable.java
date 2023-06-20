/**
 * The Intersectable interface is implemented by any class that represents a
 * geometry that can intersect with a ray.
 */
package geometries;

import primitives.*;
import java.util.*;

/**
 * The Intersectable class is an abstract class that represents an intersectable geometry.
 * It provides methods to find intersections with rays and defines a GeoPoint class to represent a geographical point with associated geometry.
 */
public abstract class Intersectable {

    /**
     * Finds all the intersections between a given ray and this geometry.
     *
     * @param ray The ray to intersect with this geometry.
     * @return A list of all the intersection points between the ray and this geometry.
     */
    public List<Point> findIntersections(Ray ray) {
        var geoList = findGeoIntersections(ray);
        return geoList == null ? null : geoList.stream().map(gp -> gp.point).toList();
    }

    /**
     * Represents a geographical point with associated geometry.
     */
    public static class GeoPoint {

        /**
         * The geometry associated with the geographical point.
         */
        public Geometry geometry;

        /**
         * The actual point coordinates.
         */
        public Point point;

        /**
         * Constructs a new GeoPoint object with the specified geometry and point.
         *
         * @param geometry the geometry associated with the geographical point
         * @param point    the actual point coordinates
         */
        public GeoPoint(Geometry geometry, Point point) {
            this.geometry = geometry;
            this.point = point;
        }

        /**
         * Checks if this GeoPoint object is equal to the specified object.
         *
         * @param o the object to compare
         * @return true if the objects are equal, false otherwise
         */
        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;
            GeoPoint geoPoint = (GeoPoint) o;
            return geometry.equals(geoPoint.geometry) && point.equals(geoPoint.point);
        }

        /**
         * Returns a string representation of this GeoPoint object.
         *
         * @return a string representation of the object
         */
        @Override
        public String toString() {
            return "GeoPoint{" +
                    "geometry=" + geometry +
                    ", point=" + point +
                    '}';
        }
    }

    /**
     * Finds the intersections between a given ray and this geometry within a specified maximum distance.
     *
     * @param ray         The ray to intersect with this geometry.
     * @param maxDistance The maximum distance for valid intersections.
     * @return A list of GeoPoint objects representing the intersection points, or null if no intersections found.
     */
    public final List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance) {
        return findGeoIntersectionsHelper(ray, maxDistance);
    }

    /**
     * Finds the intersections between a given ray and this geometry with no maximum distance limit.
     *
     * @param ray The ray to intersect with this geometry.
     * @return A list of GeoPoint objects representing the intersection points, or null if no intersections found.
     */
    public final List<GeoPoint> findGeoIntersections(Ray ray) {
        return findGeoIntersections(ray, Double.POSITIVE_INFINITY);
    }

    /**
     * Helper method to find the intersections between a given ray and this geometry within a specified maximum distance.
     * Subclasses must implement this method to provide the actual intersection logic.
     *
     * @param ray         The ray to intersect with this geometry.
     * @param maxDistance The maximum distance for valid intersections.
     * @return A list of GeoPoint objects representing the intersection points, or null if no intersections found.
     */
    protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance);

    /**
     * Finds the closest intersection point between a given ray and this geometry.
     *
     * @param ray The ray to intersect with this geometry.
     * @return The closest GeoPoint object representing the intersection point, or null if no intersections found.
     */
    public GeoPoint findClosestIntersection(Ray ray) {
        List<GeoPoint> intersectionPoints = findGeoIntersections(ray);
        return ray.findClosestGeoPoint(intersectionPoints);
    }
}


