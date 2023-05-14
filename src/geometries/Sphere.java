package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

import static primitives.Util.alignZero;

/**
 * The Sphere class represents a 3D sphere in Cartesian coordinate system.
 * It is defined by its radius and center point.
 */
public class Sphere extends RadialGeometry {
    final protected Point center; // The center point of the sphere

    /**
     * Constructs a sphere with the specified radius and center point.
     * @param radius The radius of the sphere.
     * @param center The center point of the sphere.
     */
    public Sphere(double radius, Point center) {
        super(radius); // Calls the RadialGeometry constructor to set the radius
        this.center = center; // Sets the center point of the sphere
    }

    /**
     * Returns the center point of the sphere.
     * @return The center point of the sphere.
     */
    public Point getCenter() {
        return center;
    }

    /**
     * Returns the normal vector to the sphere at the specified point.
     * @param point The point on the sphere to get the normal vector at.
     * @return The normal vector to the sphere at the specified point.
     */
    @Override
    public Vector getNormal(Point point) {
       return center.subtract(point).normalize();
    }


    @Override
    public List<Point> findIntersections(Ray ray) {
        double th;
        double tm;
        double t1 ;
        double t2;


        //o==p
        if(center.equals(ray.getP0())){
            th = radius;
            tm = 0;
        }

        else {
            Vector u = center.subtract(ray.getP0());
            tm = ray.getDir().dotProduct(u);
            double dSquared = u.lengthSquared() - tm * tm;

            //the ray outside the sphere
            if (dSquared >= radius * radius) {
                return null;
            }

            th = Math.sqrt(radius * radius - dSquared);
        }
         t1 = alignZero(tm + th);
         t2 = alignZero(tm - th);

        if (t1 <= 0 && t2 <= 0){return null;}

        List<Point> list=new ArrayList<Point>();

        if (t1 > 0)
            list.add(ray.getPoint(t1));

        if (t2 > 0)
            list.add(ray.getPoint(t2));
        return list;
    }

}
