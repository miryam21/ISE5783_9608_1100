package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains unit tests for the Point class.
 */
class PointTest {

    // Create three Point objects for use in the tests
    Point p1 = new Point(1,1,1);
    Point p2 = new Point(3,2,1);
    Point p3 = new Point(4,3,2);

    @Test
    void testDistanceSquared() {
        // ============ Equivalence Partitions Tests ==============

        // Test that the distance squared between two points is calculated correctly
        assertEquals(3, p2.distanceSquared(p3), "ERROR - DistanceSquared test in Point");

        // ============ Boundary Values Tests ===============

        // Test that the distance between a point and itself is zero
        assertEquals(0d, p2.distance(p2), 0.0001, "ERROR - Distance from point to itself must be zero");
    }

    @Test
    void testDistance() {
        // ============ Equivalence Partitions Tests ==============

        // Test that the distance between two points is calculated correctly
        assertEquals(3, p2.distance(new Point(5,4,2)), "ERROR - Distance test in Point");

        // ============ Boundary Values Tests ===============

        // Test that the distance between a point and itself is zero
        assertEquals(0d, p2.distance(p2), 0.0001, "ERROR - Distance from point to itself must be zero");
    }

    @Test
    void testAdd() {
        // ============ Equivalence Partitions Tests ==============

        // Test that adding a vector to a point produces the expected result
        assertEquals(p3, p2.add(new Vector(1,1,1)), "ERROR - Add test in Point");

        // ============ No Boundary Tests ==============
    }

    @Test
    void testSubtract() {
        // ============ Equivalence Partitions Tests ==============

        // Test that subtracting one point from another produces the expected result
        assertEquals(new Vector(1,1,1), p3.subtract(p2), "ERROR - Subtract test in Point");

        // ============ Boundary Values Tests ===============

        // Test that subtracting a point from itself throws an IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> p1.subtract(p1), "ERROR - Vector zero must throw exception");
    }
}