package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;

/**
 * The Triangle class represents a 3D triangle in Cartesian coordinate system using polygon.
 * It is defined by its three vertices.
 */
public class Triangle extends Polygon {

    public Triangle(Point x, Point y, Point z) {
        super(x, y, z); // Calls the Polygon constructor with the three vertices
    }
    @Override
    public List<Point> findIntersections(Ray ray) {

        //check if the ray intersection the plan
        List<Point> pointList = plane.findIntersections(ray);

        if(pointList == null) { return null; }

        Vector v1= vertices.get(0).subtract(ray.getP0());
        Vector v2= vertices.get(1).subtract(ray.getP0());
        Vector v3= vertices.get(2).subtract(ray.getP0());

        Vector n1 =  v1.crossProduct(v2).normalize();
        Vector n2 =  v2.crossProduct(v3).normalize();
        Vector n3 =  v3.crossProduct(v1).normalize();

        double vn1 = alignZero(ray.getDir().dotProduct(n1));
        double vn2 = alignZero(ray.getDir().dotProduct(n2));
        double vn3 = alignZero(ray.getDir().dotProduct(n3));

        if((vn1 > 0 && vn2 > 0 && vn3 > 0) || (vn1 < 0 && vn2 < 0 && vn3 < 0))
            return pointList;
        return null;

    }
}
