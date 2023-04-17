package geometries;

import primitives.Point;

/**
 * The Triangle class represents a 3D triangle in Cartesian coordinate system.
 * It is defined by its three vertices.
 */
public class Triangle extends Polygon {
    /**
     * Constructs a triangle with the specified vertices.
     * @param x The first vertex of the triangle.
     * @param y The second vertex of the triangle.
     * @param z The third vertex of the triangle.
     */
    public Triangle(Point x, Point y, Point z) {
        super(x, y, z); // Calls the Polygon constructor with the three vertices
    }
}
