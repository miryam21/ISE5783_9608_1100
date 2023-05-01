package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;
import static primitives.Util.isZero;
import static org.junit.jupiter.api.Assertions.*;

class PlaneTest {
    Point p1 = new Point(1, 0, 0);
    Point p2 = new Point(0, 2, 1);
    Point p3 = new Point(2, 0, 1);
    @Test
    void testGetNormal() {
        Plane plane = new Plane(p1, p2, p3);
        Vector vec = plane.getNormal();
        // ensure |result| = 1
        assertEquals(1, vec.length(), 0.00000001, "ERROR - Plane's normal is not a unit vector");
        // ensure the result is orthogonal to the all the vectors of the plane
        Vector v1 = p1.subtract(p2);
        Vector v2 = p1.subtract(p3);
        Vector v3 = p2.subtract(p3);
        assertTrue(isZero(vec.dotProduct(v1)) && isZero(vec.dotProduct(v2)) && isZero(vec.dotProduct(v3)),
                "ERROR - Plane's normal is not orthogonal to one of the vectors of the plane");
    }
}