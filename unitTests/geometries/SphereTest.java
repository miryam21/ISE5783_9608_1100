package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class SphereTest {
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        //TC01: Test that the normal is proper
        Sphere sph = new Sphere(1,new Point(0, 0, 1));
        assertEquals(new Vector(0, 0, -1), sph.getNormal(new Point(0, 0, 2)), "ERROR -  getNormal in sphere");
    }

}