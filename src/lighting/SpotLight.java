package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * Represents a spotlight source that emits light from a specific position in a given direction.
 * Extends the PointLight class.
 */
public class SpotLight extends PointLight {

    private Vector direction;

    /**
     * Constructs a SpotLight object with the specified intensity, position, and direction.
     *
     * @param intensity The color and brightness of the light.
     * @param position  The position of the light source.
     * @param direction The direction in which the light is emitted.
     */
    public SpotLight(Color intensity, Point position, Vector direction) {
        super(intensity, position);
        this.direction = direction.normalize();
    }

    /**
     * Calculates the intensity of the light at a given point.
     *
     * @param p The point at which to calculate the intensity.
     * @return The color and brightness of the light at the specified point.
     */
    @Override
    public Color getIntensity(Point p) {
        double maxDir = Math.max(0, direction.dotProduct(getL(p)));
        return super.getIntensity(p).scale(maxDir);
    }
}
