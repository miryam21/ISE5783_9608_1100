package renderer;
import static java.awt.Color.*;
import lighting.SpotLight;
import org.junit.jupiter.api.Test;
import static java.awt.Color.*;
import org.junit.jupiter.api.Test;
import geometries.Sphere;
import geometries.Triangle;
import lighting.AmbientLight;
import lighting.SpotLight;
import primitives.*;
import renderer.*;
import scene.Scene;

/** Test rendering a basic image
 * @author Dan */
public class picture {
    @Test
    public void twoSpheres() {
        Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVPSize(150, 150).setVPDistance(1000);

        scene.geometries.add( //
                new Sphere(50d,new Point(0, 0, -50)).setEmission(new Color(BLUE)) //
                        .setMaterial(new Material().setKD(0.4).setKS(0.3).setNShininess(100).setKT(0.3)),
                new Sphere(25d,new Point(0, 0, -50)).setEmission(new Color(RED)) //
                        .setMaterial(new Material().setKD(0.5).setKS(0.5).setNShininess(100)));
        scene.lights.add( //
                new SpotLight(new Color(1000, 600, 0), new Point(-100, -100, 500), new Vector(-1, -1, -2)) //
                        .setkL(0.0004).setkQ(0.0000006));

        camera.setImageWriter(new ImageWriter("refractionTwoSpheres", 500, 500)) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage(); //
        camera .writeToImage();
    }
}