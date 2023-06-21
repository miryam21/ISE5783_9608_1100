/**
 *
 */
package renderer;

import geometries.Cuboid;
import geometries.Plane;
import geometries.Polygon;
import geometries.Sphere;
import lighting.SpotLight;
import org.junit.jupiter.api.Test;
import primitives.Color;
import primitives.Material;
import primitives.Point;
import primitives.Vector;
import scene.Scene;

import static java.awt.Color.*;

public class picture {
    private Scene scene = new Scene("Test scene").setBackground(new Color(WHITE));

    Camera camera = new Camera(
            new Point(-2, 0, -1),     // Move the camera further to the right (x-coordinate -2)
            new Vector(0, 0, -1),       // Direction vector remains unchanged
            new Vector(0, 1, 0))        // Up vector remains unchanged
            .setVPSize(150, 150)
            .setVPDistance(550);


    /**
     * Produce a picture of a sphere lighted by a spot light
     */
    @Test
    public void twoSpheres() {
        Color wallE = new Color(70,70,70);
        Color yellowLight = new Color(250, 250, 10);
        Color blue = new Color(10, 10, 300);
        Color yellow = new Color(180, 180, 0);
//        Point p1 = new Point(100, 0, -100);  // Top-left vertex of the floor plane
//        Point p2 = new Point(100, 0, -100);   // Top-right vertex of the floor plane
//        Point p3 = new Point(100, 0, 100);    // Bottom-right vertex of the floor plane
//        Point p4 = new Point(-100, 0, 100);   // Bottom-left vertex of the floor plane

        double floorWidth = 200;
        double floorDepth = 200;
        double floorHeight = 0;

// Compute the vertices of the floor plane relative to the camera

        //materials
        Material wallM = new Material().setKD(0.5).setKS(0.5).setNShininess(5);
//     scene.geometries.add(new Plane(new Point(0, 100, 0), new Vector(0, -1, 0)).setEmission(wallE).setMaterial(wallM));
//        scene.geometries.add(new Plane(new Point(100, 0, 0), new Vector(-1, 0, 0)).setEmission(wallE).setMaterial(wallM));
//        scene.geometries.add(new Plane(new Point(-100, 0, 0), new Vector(1, 0, 0)).setEmission(wallE).setMaterial(wallM));
//       scene.geometries.add(new Plane(new Point(0, 0, 0), new Vector(0, 0, 1)).setEmission(new Color(50, 50, 50)).setMaterial(wallM));
//        scene.geometries.add(new Plane(new Point(0, 0, 100), new Vector(0, 0, -1)).setEmission(wallE).setMaterial(wallM));

        //  משנה נקודת מבט מרחקים וכו
        scene.geometries.add(
                new Cuboid(
                        new Point(-100, 5, -1000),  // Move the cuboid a little bit to the left (y-coordinate and z-coordinate modified)
                        90,                         // Width remains unchanged
                        90,                        // Height remains unchanged
                        200,                        // Depth remains unchanged
                        new Color(PINK),            // Color remains unchanged
                        0.6)  );                  // Material properties remain unchanged

        scene.geometries.add(new Sphere(40, new Point(40,-40,-1000)).setEmission(new Color(GREEN))
                .setMaterial(new Material().setKD(0.4).setKS(0.3).setNShininess(100).setKT(0.3)));
//        new Polygon(
//                new Point(5, 5, -1000),
//                new Point(5, 5, -1060),
//                new Point(65, 5, -1060),
//                new Point(65, 5, -1000)
//        ).setEmission(new Color(ORANGE))
//       ,

        scene.lights.add(new SpotLight(new Color(1020, 400, 400), new Point(40, -40, -1000), new Vector(-1, -1, -4)) //
                .setkL(0.00001).setkQ(0.000005));

//        new Polygon(
//                new Point(5, -55, -1000),
//                new Point(5, -55, -1060),
//                new Point(65, -55, -1060),
//                new Point(65, -55, -1000)
//        ).setEmission(new Color(ORANGE))
//
//      ,
//        new Polygon(
//                new Point(5, 5, -1000),
//                new Point(5, -55, -1000),
//                new Point(65, -55, -1000),
//                new Point(65, 5, -1000)
//        ).setEmission(new Color(ORANGE))
//
//        ,
//        new Polygon(
//                new Point(5, 5, -1060),
//                new Point(5, -55, -1060),
//                new Point(65, -55, -1060),
//                new Point(65, 5, -1060)
//        ).setEmission(new Color(ORANGE))
//
//       ,
//        new Polygon(
//                new Point(5, 5, -1000),
//                new Point(5, -55, -1000),
//                new Point(5, -55, -1060),
//                new Point(5, 5, -1060)
//        ).setEmission(new Color(ORANGE)));
//


//                new Triangle(new Point(1500, -1500, -1500), new Point(-1500, 1500, -1500),
//                        new Point(670, 670, 3000)) //
//                        .setEmission(new Color(20, 20, 20)) //
//                        .setMaterial(new Material().setKR(1)),
//                new Triangle(new Point(1500, -1500, -1500), new Point(-1500, 1500, -1500),
//                        new Point(-1500, -1500, -2000)) //
//                        .setEmission(new Color(20, 20, 20)) //
//                        .setMaterial(new Material().setKR(new Double3(0.5, 0, 0.4))),
//        new Polygon(
//                new Point(65, 5, -1000),
//                new Point(65, -55, -1000),
//                new Point(65, -55, -1060),
//                new Point(65, 5, -1060)
//        ).setEmission(new Color(ORANGE)));
//                new Sphere(50d,new Point(0, 0, -50)).setEmission(new Color(BLUE)) //
//                       .setMaterial(new Material().setKD(0.4).setKS(0.3).setNShininess(100).setKT(0.3)),
//                new Triangle(new Point(-50, -40, 0), new Point(-50, -70, 0), new Point(-68, -68, -4)).setEmission(new Color(YELLOW))
//                        .setMaterial(new Material().setKD(0.4).setKS(0.3).setNShininess(100)),
//                new Polygon(
//                        new Point(-5, 5, -900),
//                        new Point(-5, 25, -900),
//                        new Point(-45, 25, -930),
//                        new Point(-45, 5, -930))
//                        .setEmission(new Color(BLUE))
//                        .setMaterial(
//                                new Material()
//                                        .setKD(0.4)
//                                        .setKS(0.3)
//                                        .setNShininess(100)));
//        new Polygon(
//                new Point(-45, 25, -930),
//                new Point(-75, 25, -970),
//                new Point(-75, 5, -970),
//                new Point(-45, 5, -930))
//                .setEmission(new Color(BLUE))
//                .setMaterial(
//                        new Material()
//                                .setKD(0.4)
//                                .setKS(0.3)
//                                .setNShininess(100));
//
//                new Polygon(new Point (-16, 0, 0) ,new Point(-16, 0,-32), new Point(16, -0, -32) ,
//                        new Point(16, 0, 0)).setEmission(new Color(BLUE)).setMaterial(new Material()
//                        .setKD(0.4).setKS(0.3).setNShininess(100)));

//                new Polygon(new Point(-80,-10,20),new Point(-80,-80,20),
//                        new Point(40,10,100),new Point(40,80,100)).setEmission(new Color(PINK))
//                        .setMaterial(new Material().setKD(0.4).setKS(0.3).setNShininess(100)),
//                new Sphere(25d,new Point(80, 0, -50)).setEmission(new Color(RED)) //
//                        .setMaterial(new Material().setKD(0.5).setKS(0.5).setNShininess(100)));
        scene.lights.add( //
                new SpotLight(new Color(1000, 600, 0), new Point(-100, -100, 500), new Vector(-1, -1, -2)) //
                        .setkL(0.0004).setkQ(0.0000006));


        camera.setImageWriter(new ImageWriter("picture", 500, 500)) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage() //
                .writeToImage();

    }
}