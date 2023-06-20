package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * A point light source that extends the Light class and implements the LightSource interface.
 */
public class PointLight extends Light implements LightSource {
    private Point position;
    private double kC;
    private double kL;
    private double kQ;

    /**
     * Constructs a point light with the given intensity and position.
     *
     * @param intensity the intensity of the light
     * @param position  the position of the light source
     */
    public PointLight(Color intensity, Point position) {
        super(intensity);
        this.position = position;
        this.kC = 1;
        this.kL = 0;
        this.kQ = 0;
    }

    /**
     * Sets the constant attenuation factor of the point light.
     *
     * @param kC the constant attenuation factor to set
     * @return the updated PointLight object
     */
    public PointLight setkC(double kC) {
        this.kC = kC;
        return this;
    }

    /**
     * Sets the linear attenuation factor of the point light.
     *
     * @param kL the linear attenuation factor to set
     * @return the updated PointLight object
     */
    public PointLight setkL(double kL) {
        this.kL = kL;
        return this;
    }

    /**
     * Sets the quadratic attenuation factor of the point light.
     *
     * @param kQ the quadratic attenuation factor to set
     * @return the updated PointLight object
     */
    public PointLight setkQ(double kQ) {
        this.kQ = kQ;
        return this;
    }

    @Override
    public Color getIntensity(Point p) {
        return getIntensity().reduce(kC + kL * p.distance(position) + kQ * p.distanceSquared(position));
    }

    @Override
    public Vector getL(Point p) {
        return p.subtract(position).normalize();
    }

    @Override
    public double getDistance(Point point) {
        return position.distance(point);
    }
}
