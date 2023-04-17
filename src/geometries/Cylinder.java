package geometries;

import primitives.Point;
import primitives.Vector;

public class Cylinder {
    final protected double height;

    public Cylinder(double height) {
        this.height = height;
    }

    public double getHeight() {
        return height;
    }
    public Vector getNormal(Point point){
        return null;
    }
}
