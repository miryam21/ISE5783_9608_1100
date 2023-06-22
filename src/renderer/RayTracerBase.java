package renderer;

import primitives.Color;
import primitives.Ray;
import scene.Scene;

public abstract class RayTracerBase {
    protected Scene scene;

    /**
     * Constructs RayTracerBase object with the specified scene.
     *
     * @param scene The scene to be rendered
     */
    public RayTracerBase(Scene scene) {
        this.scene = scene;
    }

    /**
     * Traces a ray and calculates the color of the intersected object.
     *
     * @param ray The ray to be traced
     * @return The color of the intersected object
     */
    public abstract Color traceRay(Ray ray);

}

