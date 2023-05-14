package primitives;

/**
 * The Ray class represents a ray in 3D space, with a starting point and a direction.
 */
public class Ray {
    final protected Point p0; // the starting point of the ray
    final protected Vector dir; // the direction of the ray, as a normalized vector

    /**
     * Constructs a new Ray object with the given starting point and direction.
     * The direction vector is normalized so that it has a length of 1.
     *
     * @param p0  The starting point of the ray.
     * @param dir The direction of the ray, as a vector.
     */
    public Ray(Point p0, Vector dir) {
        this.p0 = p0;
        this.dir = dir.normalize();
    }

    /**
     * Returns the starting point of the ray.
     *
     * @return The starting point of the ray.
     */
    public Point getP0() {
        return p0;
    }

    /**
     * Returns the direction vector of the ray.
     *
     * @return The direction vector of the ray.
     */
    public Vector getDir() {
        return dir;
    }

    /**
     * Checks if this ray is equal to the given object.
     *
     * @param o The object to compare to.
     * @return True if the object is a Ray with the same starting point and direction, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ray ray)) return false;
        return dir.equals(ray.dir) && p0.equals(ray.p0);
    }
    public Point getPoint(double t){
        return getP0().add(getDir().scale(t));
    }
    /**
     * Returns a string representation of this Ray object.
     *
     * @return A string representing this Ray object, in the format "point of ray: [p0], vector of ray: [dir]".
     */
    @Override
    public String toString() {
        return "point of ray: " + p0 + ", vector of ray: " + dir;
    }
}
