package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
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

        //TC01: Test that constructor doesn't accept two points equals
        assertThrows(IllegalArgumentException.class, () -> new Plane(p1, p1, p2),
                "the constructor must throw exception when two point equals");

        //TC02: Test that constructor doesn't accept three points that are co-lined
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

    @Test
    void testFindIntersections() {
        Point p1 = new Point(0, 0, 2);
        Point p2 = new Point(1, 0, 2);
        Point p3 = new Point(0, 1, 2);

        Plane plane = new Plane(p1, p2, p3);

        // ============ Equivalence Partitions Tests ==============
        // TC01: ray intersects plane
        Ray ray = new Ray(new Point(-1, 0, -1), new Vector(1, 0, 1));

        assertEquals(plane.findIntersections(ray).get(0), new Point(2, 0, 2), "ERROR - wrong value of intersection point ");
        assertEquals(plane.findIntersections(ray).size(), 1, "ERROR - wrong number of points intersect the plane ");

        //TC02: ray does not intersect plane
        ray = new Ray(new Point(4, 0, 3), new Vector(1, 0, 1));
        assertNull(plane.findIntersections(ray), "ERROR - existing intersection points when there is no intersect ");

        // =============== Boundary Values Tests ==================
        // TC01: ray parallel and included in plane
        ray = new Ray(new Point(3, 0, 2), new Vector(1, 0, 1));
        assertNull(plane.findIntersections(ray), "ERROR - Wrong number of points intersects the plane (Ray is parallel and included in the plane");

        //TC02: ray parallel and not included in plane
        ray = new Ray(new Point(3, 0, 3), new Vector(1, 1, 0));
        assertNull(plane.findIntersections(ray), "ERROR - Wrong number of points intersects the plane (Ray is parallel and not included in the plane");

        //TC03: ray orthogonal to the plane - before
        ray = new Ray(new Point(2, 3, 1), new Vector(0, 0, 1));
        assertEquals(plane.findIntersections(ray).get(0), new Point(2, 3, 2), "ERROR - Wrong value point intersects the plane when ray is orthogonal to plane before the plane");
        assertEquals(plane.findIntersections(ray).size(), 1, "ERROR -  Wrong number of points intersects the plane when ray is orthogonal to  plane before the plane");

        //TC04:  ray orthogonal to the planק - in
        ray = new Ray(new Point(5, 3, 2), new Vector(0, 0, 1));
        assertNull(plane.findIntersections(ray), "ERROR - Wrong number of points intersects the plane when ray is orthogonal to the plane in the plane");

        //TC05:  ray orthogonal to the plan - after
        ray = new Ray(new Point(0, 3, 4), new Vector(0, 0, 1));
        assertNull(plane.findIntersections(ray), "ERROR - Wrong number of points intersects the plane when ray is orthogonal to the plane after the plane");

        //TC06: Ray is neither orthogonal nor parallel to and begins at the plane
        ray = new Ray(new Point(1, 0, 2), new Vector(1, 1, 1));

        assertNull(plane.findIntersections(ray), "ERROR - Wrong number of points intersects the plane when ray is neither orthogonal nor parallel to and begins at the plane");
        //TC07: Ray is neither orthogonal nor parallel to the plane and begins in the same point which appears as reference point in the plane
        ray = new Ray(plane.getPoint(), new Vector(0, 1, 1));
        assertNull(plane.findIntersections(ray), "ERROR: Wrong number of points intersects the plane when  ray is neither orthogonal nor parallel to and begins in the same point which appears as reference point in the plane");
    }
}




