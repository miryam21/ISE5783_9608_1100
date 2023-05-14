/**
 * The Intersectable interface is implemented by any class that represents a
 * geometry that can intersect with a ray.
 */
package geometries;

import primitives.*;
import java.util.*;

public interface Intersectable {

    /**
     * Finds all the intersections between a given ray and this geometry.
     * @param ray The ray to intersect with this geometry.
     * @return A list of all the intersection points between the ray and this geometry.
     */
    List<Point> findIntersections(Ray ray);
}
