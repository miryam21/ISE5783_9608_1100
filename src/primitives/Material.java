package primitives;

/**
 * Represents the material properties of an object in a scene.
 */
public class Material {
    public Double3 kD = Double3.ZERO;
    public Double3 kS = Double3.ZERO;
    public int nShininess = 0;

    /**
     * Sets the diffuse reflection coefficient of the material.
     *
     * @param kD The diffuse reflection coefficient as a Double3 (RGB values).
     * @return The Material object with the updated diffuse reflection coefficient.
     */
    public Material setKD(Double3 kD) {
        this.kD = kD;
        return this;
    }

    /**
     * Sets the diffuse reflection coefficient of the material.
     *
     * @param doubleTokD The value of the diffuse reflection coefficient for all RGB channels.
     * @return The Material object with the updated diffuse reflection coefficient.
     */
    public Material setKD(double doubleTokD) {
        this.kD = new Double3(doubleTokD);
        return this;
    }

    /**
     * Sets the specular reflection coefficient of the material.
     *
     * @param kS The specular reflection coefficient as a Double3 (RGB values).
     * @return The Material object with the updated specular reflection coefficient.
     */
    public Material setKS(Double3 kS) {
        this.kS = kS;
        return this;
    }

    /**
     * Sets the specular reflection coefficient of the material.
     *
     * @param doubleTokS The value of the specular reflection coefficient for all RGB channels.
     * @return The Material object with the updated specular reflection coefficient.
     */
    public Material setKS(double doubleTokS) {
        this.kS = new Double3(doubleTokS);
        return this;
    }

    /**
     * Sets the shininess factor of the material.
     *
     * @param nShininess The shininess factor of the material.
     * @return The Material object with the updated shininess factor.
     */
    public Material setNShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }

}
