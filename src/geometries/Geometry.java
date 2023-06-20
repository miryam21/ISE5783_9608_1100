package geometries;

import primitives.Color;
import primitives.Material;
import primitives.Point;
import primitives.Vector;

/**
 * The Geometry interface represents a geometric shape in three-dimensional space.
 * <p>
 * It provides a method for obtaining the normal vector to the surface of the shape
 * at a given point.
 */
public abstract class Geometry extends Intersectable {
    protected Color emission = Color.BLACK;
    private Material material = new Material();

    /**
     * Calculates the normal vector to the surface of the shape at the given point.
     *
     * @param point The point on the shape's surface.
     * @return The normal vector at the given point.
     */
    public abstract Vector getNormal(Point point);

    /**
     * Gets the emission color of the shape.
     *
     * @return The emission color.
     */
    public Color getEmission() {
        return emission;
    }

    /**
     * Sets the emission color of the shape.
     *
     * @param emission The emission color to set.
     * @return The Geometry object with the updated emission color.
     */
    public Geometry setEmission(Color emission) {
        this.emission = emission;
        return this;
    }

    /**
     * Gets the material of the shape.
     *
     * @return The material.
     */
    public Material getMaterial() {
        return material;
    }

    /**
     * Sets the material of the shape.
     *
     * @param material The material to set.
     * @return The Geometry object with the updated material.
     */
    public Geometry setMaterial(Material material) {
        this.material = material;
        return this;
    }
}
