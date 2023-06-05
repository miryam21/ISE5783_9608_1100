package scene;

import geometries.Geometries;
import lighting.AmbientLight;
import lighting.LightSource;
import primitives.Color;

import java.util.LinkedList;
import java.util.List;

/**
 * Represents a scene in a 3D environment.
 */
public class Scene {
    public String name;
    public Color background = Color.BLACK;
    public AmbientLight ambientLight = AmbientLight.NONE;

    public Geometries geometries = new Geometries();
    public List<LightSource> lights = new LinkedList<>();

    /**
     * Constructs a Scene object with the specified name.
     *
     * @param name The name of the scene
     */
    public Scene(String name) {
        this.name = name;
    }

    /**
     * Sets the background color of the scene.
     *
     * @param background The background color
     * @return The scene object
     */
    public Scene setBackground(Color background) {
        this.background = background;
        return this;
    }

    /**
     * Sets the ambient light of the scene.
     *
     * @param ambientLight The ambient light
     * @return The scene object
     */
    public Scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
        return this;
    }

    /**
     * Sets the geometries of the scene.
     *
     * @param geometries The geometries
     * @return The scene object
     */
    public Scene setGeometries(Geometries geometries) {
        this.geometries = geometries;
        return this;
    }

    /**
     * Sets the light sources of the scene.
     *
     * @param lights The light sources
     * @return The scene object
     */
    public Scene setLights(List<LightSource> lights) {
        this.lights = lights;
        return this;
    }
}
