package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TubeTest {


    /**
     * This class contains unit tests for the tube class.
     */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        Tube tube = new Tube(new Ray(new Point(0, 0, 1), new Vector(0, -1, 0)), 1.0);
        Vector normal = tube.getNormal(new Point(0, 8, 2));
        double check = normal.dotProduct(tube.getAxisRay().getDir());
        boolean firstNormal = new Vector(0, 0, 1).equals(normal);
        boolean secondNormal = new Vector(0, 0, -1).equals(normal);

        // TC01: ensure the normal is orthogonal to tube
        assertEquals(0d, check, "ERROR - normal is not orthogonal to the tube");


        assertTrue(firstNormal || secondNormal, "ERROR - wrong normal to tube");

        // =============== Boundary Values Tests ==================
        normal = tube.getNormal(new Point(0, 0, 2));
        check = normal.dotProduct(tube.getAxisRay().getDir());
        firstNormal = new Vector(0, 0, 1).equals(normal);
        secondNormal = new Vector(0, 0, -1).equals(normal);


        assertEquals(0d, check, "ERROR - normal is not orthogonal to the tube");
        assertTrue(firstNormal || secondNormal, "ERROR - wrong normal to tube");
    }

}