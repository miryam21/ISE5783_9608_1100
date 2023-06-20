package lighting;

import primitives.Color;

/**
 * The abstract class representing a light source in a scene.
 */
abstract class Light {
    private Color intensity;

    /**
     * Constructs a light with the given intensity.
     *
     * @param intensity the intensity of the light
     */
    protected Light(Color intensity) {
        this.intensity = intensity;
    }

    /**
     * Retrieves the intensity of the light.
     *
     * @return the intensity of the light
     */
    public Color getIntensity() {
        return intensity;
    }
}
