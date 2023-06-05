package primitives;
import java.util.*;
import java.security.PublicKey;
import geometries.Intersectable.GeoPoint;
/**
 * The Ray class represents a ray in 3D space, with a starting point and a direction.
 */
public class Ray {
    final protected Point p0; // the starting point of the ray
    final protected Vector dir; // the direction of the ray, as a normalized vector

    /**
     * Constructs a new Ray object with the given starting point and direction.
     * The direction vector is normalized so that it has a length of 1.
     *
     * @param p0  The starting point of the ray.
     * @param dir The direction of the ray, as a vector.
     */
    public Ray(Point p0, Vector dir) {
        this.p0 = p0;
        this.dir = dir.normalize();
    }

    /**
     * Returns the starting point of the ray.
     *
     * @return The starting point of the ray.
     */
    public Point getP0() {
        return p0;
    }

    /**
     * Returns the direction vector of the ray.
     *
     * @return The direction vector of the ray.
     */
    public Vector getDir() {
        return dir;
    }

    /**
     * Checks if this ray is equal to the given object.
     *
     * @param o The object to compare to.
     * @return True if the object is a Ray with the same starting point and direction, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ray ray)) return false;
        return dir.equals(ray.dir) && p0.equals(ray.p0);
    }
    public Point getPoint(double t){
        return getP0().add(getDir().scale(t));
    }
    /**
     * Returns a string representation of this Ray object.
     *
     * @return A string representing this Ray object, in the format "point of ray: [p0], vector of ray: [dir]".
     */
    @Override
    public String toString() {
        return "point of ray: " + p0 + ", vector of ray: " + dir;
    }

    /**
     * Finds the closest point to the start of ray from a list of points.
     *
     * @param lst The list of points to search for the closest point.
     * @return The closest point to the reference point, or null if the list is null or empty.
     */
    /*public Point findClosestPoint(List<Point> lst) {
        if (lst == null || lst.size() == 0) {
            // Return null if the list is null or empty
            return null;
        }

        // Set the first point as the initial closest point
        Point closestPoint = lst.get(0);

        // Calculate the initial distance between the closest point and the reference point (p0)
        double distance = closestPoint.distance(p0);

        // Iterate over the remaining points in the list
        for (Point point : lst) {
            // Calculate the distance between the current point and the reference point
            double currentDistance = point.distance(p0);

            // Check if the current point is closer to the reference point than the closest point
            if (currentDistance < distance) {
                // Update the closest point and the distance
                closestPoint = point;
                distance = currentDistance;
            }
        }

        // Return the closest point
        return closestPoint;
    }
    */
    public Point findClosestPoint(List<Point> points) {
        return points == null || points.isEmpty() ? null
                : findClosestGeoPoint(points.stream().map(p -> new GeoPoint(null, p)).toList()).point;
    }
    public GeoPoint findClosestGeoPoint(List<GeoPoint> GeoPointList)
    {
        if(GeoPointList == null)
            return null;
        GeoPoint closesPoint = GeoPointList.get(0);
        double minDistanceSquared = closesPoint.point.distanceSquared(this.p0);
        double distanceSquared;
        for (GeoPoint geoPoint: GeoPointList)
        {
            distanceSquared = geoPoint.point.distanceSquared(this.p0);
            if(distanceSquared < minDistanceSquared)
            {
                minDistanceSquared = distanceSquared;
                closesPoint = geoPoint;
            }

        }
        return closesPoint;
    }

}
