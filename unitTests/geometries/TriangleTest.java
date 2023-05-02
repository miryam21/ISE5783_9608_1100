package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class TriangleTest {
    /**
     * This class contains unit tests for the triangle class.
     */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: ensure the normal is correct
        Triangle tr = new Triangle(new Point(0,0,1),new Point(1,0,0),new Point(0,0,0));
        assertEquals(new Vector(0, 1, 0), tr.getNormal(new Point(0.3, 0, 0.3)), "ERROR - getNormal in triangle");
    }

}