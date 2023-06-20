/**
 * The Cylinder class represents a geometric cylinder in three-dimensional space.
 */
package geometries;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import primitives.Util;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

import java.util.List;

public class Cylinder extends Tube{

    /**
     * The height of the cylinder.
     */
    final protected double height;

    /**
     * Constructs a new Cylinder object with the given height.
     *
     * @param height the height of the cylinder
     */
    public Cylinder(double height, Ray exisRay, double radius) {
        super(exisRay,radius);
        this.height = height;
    }

    /**
     * Returns the height of the cylinder.
     *
     * @return the height of the cylinder
     */
    public double getHeight() {
        return height;
    }

    /**
     * Returns the normal vector of the cylinder surface at the specified point.
     *
     * @param point the point on the surface of the cylinder
     * @return the normal vector of the cylinder surface at the specified point
     */
    @Override
    public Vector getNormal(Point point) {
        Point p0 = axisRay.getP0();
        Vector v = axisRay.getDir();//  vector direction of ray

        if (point.equals(p0))
            return v;

        // projection of P-p0 on the ray:
        Vector u = point.subtract(p0);

        // distance from p0 to the o who is in from of point
        double t = alignZero(u.dotProduct(v));

        // if the point is at a base
        if (t == 0 || isZero(height - t))
            return v;

        //the other point on the axis facing the given point
        Point o = p0.add(v.scale(t));

        return point.subtract(o).normalize();
    }
}