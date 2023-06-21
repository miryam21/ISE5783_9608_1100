/**
 *
 */
package renderer;

import geometries.Polygon;
import geometries.Sphere;
import geometries.Triangle;
import lighting.AmbientLight;
import lighting.SpotLight;
import org.junit.jupiter.api.Test;
import primitives.*;
import scene.Scene;

import static java.awt.Color.*;

/** Tests for reflection and transparency functionality, test for partial
 * shadows
 * (with transparency)
 * @author dzilb */
public class PictureTest {
    private Scene scene = new Scene("Test scene");
    @Test
    public void twoSpheresOnMirrors() {
        Camera camera = new Camera(new Point(0, 0, 10000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVPSize(2500, 2500).setVPDistance(10000); //

        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));

        scene.geometries.add( //
                new Sphere(400d, new Point(-950, -900, -1000)).setEmission(new Color(0, 50, 100)) //
                        .setMaterial(new Material().setKD(0.25).setKS(0.25).setNShininess(20)
                                .setKT(new Double3(0.5, 0, 0))),
                new Sphere(200d, new Point(-950, -900, -1000)).setEmission(new Color(100, 50, 20)) //
                        .setMaterial(new Material().setKD(0.25).setKS(0.25).setNShininess(20)));
//                new Triangle(new Point(1500, -1500, -1500), new Point(-1500, 1500, -1500),
//                        new Point(670, 670, 3000)) //
//                        .setEmission(new Color(20, 20, 20)) //
//                        .setMaterial(new Material().setKR(1)),
//                new Triangle(new Point(1500, -1500, -1500), new Point(-1500, 1500, -1500),
//                        new Point(-1500, -1500, -2000)) //
//                        .setEmission(new Color(20, 20, 20)) //
//                        .setMaterial(new Material().setKR(new Double3(0.5, 0, 0.4))));
        scene.geometries.add(new Polygon(new Point(1500, -1500, -1500),
                new Point(-1500, 1500, -1500),
                new Point(1500, -1500, -1500),
                new Point(-1500, 1500, -1500)).setEmission(new Color(GREEN)));

        scene.lights.add(new SpotLight(new Color(1020, 400, 400), new Point(-750, -750, -150), new Vector(-1, -1, -4)) //
                .setkL(0.00001).setkQ(0.000005));

        ImageWriter imageWriter = new ImageWriter("bla", 500, 500);
        camera.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage();//
        camera.writeToImage();
    }
}

    /** Produce a picture of a two triangles lighted by a spot light with a
     * partially
     * transparent Sphere producing partial shadow */
