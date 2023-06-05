package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * The interface representing a light source in a scene.
 */
public interface LightSource {

    /**
     * Retrieves the intensity of the light at a given point.
     *
     * @param p the point at which to calculate the intensity
     * @return the intensity of the light at the specified point
     */
    public Color getIntensity(Point p);

    /**
     * Retrieves the direction vector from the light source to a given point.
     *
     * @param p the point at which to calculate the direction vector
     * @return the direction vector from the light source to the specified point
     */
    public Vector getL(Point p);
}
