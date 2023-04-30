package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
/** unit tests for point class */
class PointTest {
Point p1 = new Point(1,1,1);
Point p2 = new Point(3,2,1);
Point p3 = new Point(4,3,2);
    @Test
    void testDistanceSquared() {
        // ============ Equivalence Partitions Tests ==============

        assertEquals(3,p2.distanceSquared(p3),"ERROR- DistanceSquared test in point");

        // ============ boundary values Tests ===============

        assertEquals(0d,p2.distance(p2),0.0001,"ERROR- distance from point to itself must be zero");
    }

    @Test
    void testDistance() {
        // ============ Equivalence Partitions Tests ==============

        assertEquals(3,p2.distance(new Point(5,4,2)),"ERROR- Distance test in point");

        // ============ boundary values Tests ===============

        assertEquals(0d,p2.distance(p2),0.0001,"ERROR- distance from point to itself must be zero");
    }



    @Test
    void testAdd() {
        // ============ Equivalence Partitions Tests ==============

        assertEquals(p3,p2.add(new Vector(1,1,1)),"ERROR- add test in point");

        // ============ no boundary test==============
    }

    @Test
    void testSubtract() {
        // ============ Equivalence Partitions Tests ==============

    assertEquals(new Vector(1,1,1),p3.subtract(p2),"ERROR- subtract test in point ");

        // ============ boundary values Tests ==============
        assertThrows(IllegalArgumentException.class, ()->p1.subtract(p1),"ERROR- vector zero must throw exception");
    }
}