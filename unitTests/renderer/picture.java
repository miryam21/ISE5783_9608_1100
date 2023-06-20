/**
 *
 */
package renderer;

import geometries.Polygon;
import org.junit.jupiter.api.Test;
import primitives.Color;
import primitives.Material;
import primitives.Point;
import primitives.Vector;
import scene.Scene;

import static java.awt.Color.BLUE;
import static java.awt.Color.GREEN;

public class picture {
    private Scene scene = new Scene("Test scene");

    /**
     * Produce a picture of a sphere lighted by a spot light
     */
    @Test
    public void twoSpheres() {
        //  משנה נקודת מבט מרחקים וכו
        scene.geometries.add( //
//                new Sphere(50d,new Point(0, 0, -50)).setEmission(new Color(BLUE)) //
//                       .setMaterial(new Material().setKD(0.4).setKS(0.3).setNShininess(100).setKT(0.3)),
//                new Triangle(new Point(-50, -40, 0), new Point(-50, -70, 0), new Point(-68, -68, -4)).setEmission(new Color(YELLOW))
//                        .setMaterial(new Material().setKD(0.4).setKS(0.3).setNShininess(100)),
                new Polygon(
                        new Point(-5, 5, -900),
                        new Point(-5, 25, -900),
                        new Point(-45, 25, -930),
                        new Point(-45, 5, -930))
                        .setEmission(new Color(BLUE))
                        .setMaterial(
                                new Material()
                                        .setKD(0.4)
                                        .setKS(0.3)
                                        .setNShininess(100)));
        new Polygon(
                new Point(-45, 25, -930),
                new Point(-75, 25, -970),
                new Point(-75, 5, -970),
                new Point(-45, 5, -930))
                .setEmission(new Color(BLUE))
                .setMaterial(
                        new Material()
                                .setKD(0.4)
                                .setKS(0.3)
                                .setNShininess(100));

//                new Polygon(new Point (-16, 0, 0) ,new Point(-16, 0,-32), new Point(16, -0, -32) ,
//                        new Point(16, 0, 0)).setEmission(new Color(BLUE)).setMaterial(new Material()
//                        .setKD(0.4).setKS(0.3).setNShininess(100)));

//                new Polygon(new Point(-80,-10,20),new Point(-80,-80,20),
//                        new Point(40,10,100),new Point(40,80,100)).setEmission(new Color(PINK))
//                        .setMaterial(new Material().setKD(0.4).setKS(0.3).setNShininess(100)),
//                new Sphere(25d,new Point(80, 0, -50)).setEmission(new Color(RED)) //
//                        .setMaterial(new Material().setKD(0.5).setKS(0.5).setNShininess(100)));
//        scene.lights.add( //
//                new SpotLight(new Color(1000, 600, 0), new Point(-100, -100, 500), new Vector(-1, -1, -2)) //
//                        .setkL(0.0004).setkQ(0.0000006));
        Camera camera = new Camera(
                new Point(0, 0, -1),
                new Vector(0, 0, -1),
                new Vector(0, 1, 0)) //
                .setVPSize(150, 150)
                .setVPDistance(1000);

        camera.setImageWriter(new ImageWriter("picture", 500, 500)) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage() //
                .writeToImage();

    }
}
