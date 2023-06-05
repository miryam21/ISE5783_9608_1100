package geometries;

 public abstract class RadialGeometry extends Geometry {
    protected final double radius;

    public RadialGeometry(double radius) {
        if (radius<=0)
            throw new IllegalArgumentException("radius must be positive");
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }
}
