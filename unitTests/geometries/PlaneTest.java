package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;
import static primitives.Util.isZero;
import static org.junit.jupiter.api.Assertions.*;

class PlaneTest {
    /**
     * This class contains unit tests for the plane class.
     */
    Point p1 = new Point(1, 0, 0);
    Point p2 = new Point(0, 2, 1);
    Point p3 = new Point(2, 0, 1);

    @Test
    void testConstructor() {
        // =============== Boundary Values Tests ==================
        // Define boundary test points
        Point p1 = new Point(1, 2, 3);
        Point p2 = new Point(4, 5, 6);

        //TC11: Test that constructor doesn't accept two points equals
        assertThrows(IllegalArgumentException.class, () -> new Plane(p1, p1, p2),
                "the constructor must throw exception when two point equals");

        //TC12: Test that constructor doesn't accept three points that are co-lined
        Point p4 = new Point(2, 4, 6);
        Point p5 = new Point(4, 8, 12);
        assertThrows(IllegalArgumentException.class, () -> new Plane(p1, p4, p5),
                "the constructor must throw exception when three points are co-lined");
    }

    @Test
    void testGetNormal() {
        // =============== Equivalence Partitions Tests ==================
        // Create a plane from three points

        Plane plane = new Plane(p1, p2, p3);
        // Get the normal vector
        Vector vec = plane.getNormal();

        // TC01: ensure the result is the unit vector
        assertEquals(1, vec.length(), 0.00000001, "ERROR - Plane's normal is not a unit vector");

        // TC02:ensure the result is orthogonal to the all the vectors of the plane
        Vector v1 = p1.subtract(p2);
        Vector v2 = p1.subtract(p3);
        Vector v3 = p2.subtract(p3);
        assertTrue(isZero(vec.dotProduct(v1)) && isZero(vec.dotProduct(v2)) && isZero(vec.dotProduct(v3)),
                "ERROR - Plane's normal is not orthogonal to one of the vectors of the plane");
    }
}