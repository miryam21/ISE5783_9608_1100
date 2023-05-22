package primitives;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RayTest {

    @Test
    void testFindClosestPoint() {

            Point p1 = new Point(0, 0, 1);
            Point p2 = new Point(0, 0, 2);
            Point p3 = new Point(0, 0, 3);
            Ray ray = new Ray(new Point(0, 0, 0.5), new Vector(0, 0, 1));

            // ============ Equivalence Partitions Tests ==============
            //TC01: point is in the middle of the list
            List<Point> pointList = new ArrayList<Point>();
            pointList.add(p2);
            pointList.add(p1);
            pointList.add(p3);
            assertEquals(p1, ray.findClosestPoint(pointList), "wrong point");

            // =============== Boundary Values Tests ==================
            //TC02: list is empty
            pointList= new ArrayList<Point>();

            assertNull(ray.findClosestPoint(pointList), "wrong point");
            //TC03: point is in the beginning of the list

            pointList=new ArrayList<Point>();
            pointList.add(p1);
            pointList.add(p2);
            pointList.add(p3);
            assertEquals(p1, ray.findClosestPoint(pointList), "wrong point");
            //TC04: point is in the end of the list

            pointList=new ArrayList<Point>();
            pointList.add(p2);
            pointList.add(p3);
            pointList.add(p1);
            assertEquals(p1, ray.findClosestPoint(pointList), "wrong point");
    }
}