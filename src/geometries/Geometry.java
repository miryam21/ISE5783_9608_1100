package geometries;

import primitives.Color;
import primitives.Point;
import primitives.Vector;
/**

 The Geometry interface represents a geometric shape in three-dimensional space.

 It provides a method for obtaining the normal vector to the surface of the shape

 at a given point.
 */
public abstract class Geometry  extends Intersectable {
    protected Color emission= Color.BLACK;
    public abstract Vector getNormal(Point point);

    public Color getEmission() {
        return emission;
    }

    public Geometry setEmission(Color emission) {
        this.emission = emission;
        return this;
    }
}





