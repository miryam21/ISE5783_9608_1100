package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class TriangleTest {
    /**
     * This class contains unit tests for the triangle class.
     */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: ensure the normal is correct
        Triangle tr = new Triangle(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 0, 0));
        assertEquals(new Vector(0, 1, 0), tr.getNormal(new Point(0.3, 0, 0.3)), "ERROR - getNormal in triangle");
    }

    @Test
    void testFindIntersections() {
        Triangle triangle = new Triangle(new Point(0, -1, 2), new Point(3, 3, 2), new Point(-3, 3, 2));

        //all the tests are only if the ray intersects with the plane

        // ============ Equivalence Partitions Tests ==============
        //TC01: the point inside the triangle
        Ray ray = new Ray(new Point(2, 0, 0), new Vector(-1, 2, 2));
        assertEquals(triangle.findIntersections(ray).size(), 1, "ERROR - Wrong number of points intersects the sphere when the point Inside the triangle");

        assertEquals(triangle.findIntersections(ray).get(0), new Point(1, 2, 2), "ERROR - Wrong number of points intersects the sphere when the point Inside the triangle");

        //TC02: the point outside against edge
        ray = new Ray(new Point(2, 0, 0), new Vector(-4, 1, 2));
        //אם מוחזר null
        assertNull(triangle.findIntersections(ray), "ERROR - Wrong number of points intersects the sphere when the point outside against edge the triangle");


        //TC03: the point outside against vertex
        ray = new Ray(new Point(2, 0, 0), new Vector(-2, -3, 2));
        //אם מוחזר null
        assertNull(triangle.findIntersections(ray), "ERROR -  Wrong number of points intersects the sphere when the point outside against vertex the triangle");


        // =============== Boundary Values Tests ==================
        //TC04: the point on edge of the triangle
        ray = new Ray(new Point(2, 0, 0), new Vector(-2, 3, 2));
        assertNull(triangle.findIntersections(ray), "ERROR - Wrong number of points intersects the sphere when the point on edge of the triangle");

        //TC05: the point in vertex of the triangle
        ray = new Ray(new Point(2, 0, 0), new Vector(-5, 3, 2));
        assertNull(triangle.findIntersections(ray), "ERROR - Wrong number of points intersects the sphere when the point in vertex of the triangle");

        //TC06: the point on edge's continuation
        ray = new Ray(new Point(2, 0, 0), new Vector(1, -5, 2));
        assertNull(triangle.findIntersections(ray), "ERROR - Wrong number of points intersects the sphere when the point on edge's continuation");
    }

}