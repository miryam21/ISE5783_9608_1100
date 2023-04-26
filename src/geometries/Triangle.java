package geometries;

import primitives.Point;

/**
 * The Triangle class represents a 3D triangle in Cartesian coordinate system using polygon.
 * It is defined by its three vertices.
 */
public class Triangle extends Polygon {

    public Triangle(Point x, Point y, Point z) {
        super(x, y, z); // Calls the Polygon constructor with the three vertices
    }
}
