package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
/** unit tests for vector class*/
class VectorTest {

    @Test
    void testLength() {

    }

    @Test
    void testNormalize() {
    }

    @Test
    void testScale() {
    }

    @Test
    void testDotProduct() {
    }

    @Test
    void testAdd() {
    }

    @Test
    void testCrossProduct() {
    }

    @Test
    void testLengthSquared() {
        // ============ Equivalence Partitions Tests ==============

        assertEquals(9d,new Vector(2,2,1).lengthSquared(),0.00001,"ERROR - lengthSquared test in vector");

    }

    //todo: ask if  subtract is needed here
    @Test
    void testSubtract(){

    }
}