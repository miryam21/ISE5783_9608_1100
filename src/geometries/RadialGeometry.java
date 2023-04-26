package geometries;

abstract public class RadialGeometry implements Geometry {
    protected final double radius;

    public RadialGeometry(double radius) {
        this.radius = radius;
    }
}
