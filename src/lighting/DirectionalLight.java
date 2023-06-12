package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * A directional light source that extends the Light class and implements the LightSource interface.
 */
public class DirectionalLight extends Light implements LightSource {
    private Vector direction;

    /**
     * Constructs a directional light with the given intensity and direction.
     *
     * @param intensity  the intensity of the light
     * @param direction  the direction of the light source
     */
    public DirectionalLight(Color intensity, Vector direction) {
        super(intensity);
        this.direction = direction.normalize();
    }

    @Override
    public Color getIntensity(Point p) {
        return getIntensity();
    }

    @Override
    public Vector getL(Point p) {
        return direction;
    }
    @Override
    public double getDistance(Point point) {
        return Double.POSITIVE_INFINITY;
    }
}
