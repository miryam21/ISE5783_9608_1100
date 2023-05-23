package primitives;

import java.util.Objects;

/**
 * The Point class represents a 3D point in Cartesian coordinate system.
 * It is defined by its x, y, and z coordinates.
 */
public class Point {
    public static final Point ZERO = new Point(0,0,0);
    final Double3 xyz; // The x, y, and z coordinates of the point

    /**
     * Constructs a point with the specified x, y, and z coordinates.
     * @param x The x coordinate of the point.
     * @param y The y coordinate of the point.
     * @param z The z coordinate of the point.
     */
    public Point(double x, double y, double z) {
        xyz = new Double3(x, y, z); // Initializes the x, y, and z coordinates of the point
    }

    // Private constructor used to create a Point object from a Double3 object
    private Point(Double3 double3) {
        this(double3.d1, double3.d2, double3.d3);
    }

    /**
     * Returns true if this point is equal to the specified object.
     * @param o The object to compare with this point.
     * @return true if this point is equal to the specified object, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point point)) return false;
        return xyz.equals(point.xyz);
    }

    /**
     * Returns the hash code of this point.
     * @return The hash code of this point.
     */
    @Override
    public int hashCode() {
        return Objects.hash(xyz);
    }

    /**
     * Returns a string representation of this point.
     * @return A string representation of this point.
     */
    @Override
    public String toString() {
        return "Point:" + xyz;
    }

    /**
     * Returns the square of the distance between this point and the specified point.
     * @param other The point to calculate the distance to.
     * @return The square of the distance between this point and the specified point.
     */
    public double distanceSquared(Point other) {
        return (other.xyz.d1 - xyz.d1) * (other.xyz.d1 - xyz.d1) +
                (other.xyz.d2 - xyz.d2) * (other.xyz.d2 - xyz.d2) +
                (other.xyz.d3 - xyz.d3) * (other.xyz.d3 - xyz.d3);
    }

    /**
     * Returns the distance between this point and the specified point.
     * @param other The point to calculate the distance to.
     * @return The distance between this point and the specified point.
     */
    public double distance(Point other) {
        return Math.sqrt(distanceSquared(other));
    }

    /**
     * Returns a new point that is the result of adding the specified vector to this point.
     * @param vector The vector to add to this point.
     * @return A new point that is the result of adding the specified vector to this point.
     */
    public Point add(Vector vector) {
        return new Point(xyz.add(vector.xyz));
    }

    /**
     * Returns the vector that is the result of subtracting the specified point from this point.
     * @param point The point to subtract from this point.
     * @return The vector that is the result of subtracting the specified point from this point.
     */
    public Vector subtract(Point point) {
        return new Vector(xyz.subtract(point.xyz));
    }
    public double getX() {
        return xyz.d1;
    }

    public double getY() {
        return xyz.d2;
    }
    public double getZ(){ return xyz.d3;}
}
