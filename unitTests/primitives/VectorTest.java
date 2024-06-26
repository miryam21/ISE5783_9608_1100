package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

/**
 * Unit tests for the Vector class.
 */
class VectorTest {

    // Initialize three vectors for testing purposes
    Vector v1 = new Vector(1, 2, 3);
    Vector v2 = new Vector(0, 3, -2);
    Vector v3 = new Vector(-1, -2, -3);

    @Test
    public void testCrossProduct() {
        // Equivalence Partitions Tests
        // TC01: Test that length of cross-product is proper (orthogonal vectors taken
        // for simplicity)
        Vector vr = v1.crossProduct(v2);
        assertEquals(v1.length() * v2.length(), vr.length(), 0.00001, "crossProduct() wrong result length");
        // TC02: Test cross-product result orthogonality to its operands
        assertTrue(isZero(vr.dotProduct(v1)), "crossProduct() result is not orthogonal to 1st operand");
        assertTrue(isZero(vr.dotProduct(v2)), "crossProduct() result is not orthogonal to 2nd operand");

        // Boundary Values Tests
        // TC11: test zero vector from cross-product of co-lined vectors
        Vector v3 = new Vector(-2, -4, -6);
        assertThrows(IllegalArgumentException.class, () -> v1.crossProduct(v3),
                "crossProduct() for parallel vectors does not throw an exception");
    }


    @Test
    public void testNormalize() {
        // Initialize a vector and normalize it
        Vector v = new Vector(0, 3, 4);
        Vector n = v.normalize();

        // Equivalence Partitions Tests
        // TC01: normalize vector test
        assertEquals(1d, n.lengthSquared(), 0.00001, "wrong normalized vector length");
        // TC02: direction normalized vector test
        assertThrows(IllegalArgumentException.class,
                () -> v.crossProduct(n),
                "normalized vector is not in the same direction");
        assertEquals(new Vector(0, 0.6, 0.8), n, "wrong normalized vector");
    }

    @Test
    void testScale() {
        // ===============Equivalence Partitions Tests=====================
        // TC03: multiply by scalar test
        assertEquals(new Vector(2, 4, 6), v1.scale(2d), "ERROR- scale in vector(scalar mult)");

        // Boundary Values Tests
        // TC04: multiply by 0 test
        assertThrows(IllegalArgumentException.class, () -> v1.scale(0d), "ERROR- scale multi by 0 is vector zero");
    }

    @Test
    void testLength() {
        // ==================Equivalence Partitions Tests================
        // TC5: ensure the length of vector
        assertEquals(v1.length(), Math.sqrt(14), "ERROR-length in vector");
    }

    @Test
    void testDotProduct() {
        // ================Equivalence Partitions Tests========================
        // TC06:  dot product test
        assertEquals(-14d, v1.dotProduct(v3), "ERROR - dot product in vector");
        // TC07: dot product returns 0 test
        assertEquals(0d, v1.dotProduct(v2), "ERROR - dot product doesn't return 0");
    }

    @Test
    void testAdd() {
        // ================Equivalence Partitions Tests============================
        //TC08: add action on vector test
        assertEquals(new Vector(1, 5, 1), v1.add(v2), "ERROR- add in vector does not work");

        // =================Boundary Values Tests=====================
        //TC09: add action on vector returns vector zero test
        assertThrows(IllegalArgumentException.class, () -> v1.add(v3), "ERROR- vector zero in add function");
    }

    @Test
    void testLengthSquared() {
        // ============ Equivalence Partitions Tests ==============
        // //TC010: add action on vector testTest that the length squared of a vector is calculated correctly
        assertEquals(9d, new Vector(2, 2, 1).lengthSquared(), 0.00001, "ERROR - lengthSquared test in vector");
    }

    //todo: ask if subtract is needed here
    @Test
    void testSubtract() {
        // ============ Equivalence Partitions Tests ==============
        // //TC011: add action on vector testTest that subtracting two vectors returns the correct vector
        assertEquals(new Vector(1, -1, 5), v1.subtract(v2), "ERROR- add in vector dose not work");

        // ============ boundary values Tests ===============
        // //TC012: add action on vector testTest that subtracting a vector from itself throws an IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> v1.subtract(v1), "ERROR- vector zero in add function");
    }
}