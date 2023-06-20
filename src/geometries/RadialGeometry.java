package geometries;

public abstract class RadialGeometry extends Geometry {
    protected final double radius;

    /**
     * Constructs a `RadialGeometry` object with the specified radius.
     *
     * @param radius The radius of the radial geometry.
     * @throws IllegalArgumentException if the radius is not positive.
     */
    public RadialGeometry(double radius) {
        if (radius <= 0)
            throw new IllegalArgumentException("radius must be positive");
        this.radius = radius;
    }

    /**
     * Returns the radius of the radial geometry.
     *
     * @return The radius.
     */
    public double getRadius() {
        return radius;
    }
}
