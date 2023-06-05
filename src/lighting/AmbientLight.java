package lighting;

import primitives.Color;
import primitives.Double3;

import java.security.PublicKey;

/**
 * Represents ambient light in a scene.
 */
public class AmbientLight extends Light {

    /**
     * A constant representing no ambient light.
     */
    public static final AmbientLight NONE = new AmbientLight(Color.BLACK, Double3.ZERO);

    /**
     * Default constructor (use super constructor)
     */
    public AmbientLight(Color iA  , Double3 kA) {
        super(iA.scale(kA));
    }

    /**
     * Default constructor (use super constructor)
     */
    public AmbientLight() {
        super(Color.BLACK);
    }

}
