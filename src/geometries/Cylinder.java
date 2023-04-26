/**
 * The Cylinder class represents a geometric cylinder in three-dimensional space.
 */
package geometries;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

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
    public Vector getNormal(Point point){
        return null; // TODO: implement this method
    }
}