package lighting;

import primitives.Color;
import primitives.Double3;

import java.security.PublicKey;

/**
 * Represents ambient light in a scene.
 */
public class AmbientLight {

    /**
     * The intensity of the ambient light.
     */
    Color intensity;

    /**
     * A constant representing no ambient light.
     */
    public static final AmbientLight NONE = new AmbientLight(Color.BLACK, Double3.ZERO);

    /**
     * Constructs an ambient light with the specified intensity and coefficient.
     *
     * @param Ia The intensity of the ambient light.
     * @param Ka The coefficient for scaling the intensity.
     */
    public AmbientLight(Color Ia, Double3 Ka) {
        this.intensity = Ia.scale(Ka);
    }

    /**
     * Constructs an ambient light with the specified intensity and coefficient.
     *
     * @param Ia The intensity of the ambient light.
     * @param Ka The coefficient for scaling the intensity.
     */
    public AmbientLight(Color Ia, double Ka) {
        this.intensity = Ia.scale(Ka);
    }

    /**
     * Returns the intensity of the ambient light.
     *
     * @return The intensity of the ambient light.
     */
    public Color getIntensity() {
        return intensity;
    }
}
