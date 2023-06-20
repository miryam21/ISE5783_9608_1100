/**
 * The Geometries class represents a collection of intersectable geometries.
 * It extends the Intersectable class and implements methods to find intersections with rays.
 */
package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Geometries extends Intersectable {

    /**
     * The list of intersectable geometries.
     */
    private List<Intersectable> list;

    /**
     * Constructs an empty Geometries object with an empty list.
     */
    public Geometries() {
        list = new LinkedList<>();
    }

    /**
     * Constructs a Geometries object with the given intersectable geometries.
     * @param geometries The intersectable geometries to add.
     */
    public Geometries(Intersectable... geometries) {
        list = new LinkedList<>();
        for (Intersectable geometry : geometries) {
            list.add(geometry);
        }
    }

    /**
     * Finds the intersection points between a ray and the geometries in this collection.
     * @param ray The ray to find intersections with.
     * @param maxDistance The maximum distance for valid intersections.
     * @return A list of GeoPoint objects representing the intersection points, or null if no intersections found.
     */
    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        List<GeoPoint> listOfAllThePoint = new ArrayList<>();

        for (Intersectable geometry : list) {
            List<GeoPoint> pointList = geometry.findGeoIntersectionsHelper(ray, maxDistance);
            if (pointList == null) continue;
            listOfAllThePoint.addAll(pointList);
        }

        if (listOfAllThePoint.size() == 0)
            return null;
        return listOfAllThePoint;
    }

    /**
     * Adds the given intersectable geometries to this collection.
     * @param geometries The intersectable geometries to add.
     */
    public void add(Intersectable... geometries) {
        for (Intersectable geometry : geometries) {
            list.add(geometry);
        }
    }
}
