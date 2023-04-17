package geometries;

import primitives.Point;
import primitives.Vector;
/**

 The Geometry interface represents a geometric shape in three-dimensional space.

 It provides a method for obtaining the normal vector to the surface of the shape

 at a given point.
 */
public interface Geometry {
    Vector getNormal(Point point);
}





