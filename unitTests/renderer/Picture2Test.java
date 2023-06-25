package renderer;


import geometries.*;
import lighting.AmbientLight;
import lighting.SpotLight;
import org.junit.jupiter.api.Test;
import primitives.Color;
import primitives.Material;
import primitives.Point;
import primitives.Vector;
import scene.Scene;

import static java.awt.Color.*;

public class Picture2Test {
    private Scene scene = new Scene("Test scene").setBackground(new Color(WHITE)).setBackground(new Color(GRAY));
    @Test
    public void heart() {
//         Camera camera = new Camera(new Point(0, -150, 800), new Vector(0, 0, -1), new Vector(0, 1, 0))
//              .setVPSize(200, 200).setVPDistance(1000);

//          Camera camera = new Camera(new Point(1100, -50, -250), new Vector(-1, 0, 0), new Vector(0, 0, 1))
//        .setVPSize(200, 200).setVPDistance(1000);
//        Camera camera = new Camera(new Point(10, 1200, -200), new Vector(0, -1, 0), new Vector(1 / 2, 0, 1))
//        .setVPSize(200, 200).setVPDistance(1000);

        Camera camera = new Camera(new Point(10, 1200, -200), new Vector(0, -1, 0), new Vector(1 / 2, 0, 1))
                .setVPSize(200, 200).setVPDistance(1000);

        scene.setAmbientLight(new AmbientLight(new Color(WHITE), 0.15));

        scene.geometries.add(

                //מראות
                new Triangle(new Point(150, -200, -300), new Point(-270, -400, -350),
                        new Point(150, -200, 200)) //
                        .setEmission(new Color(BLACK)) //
                        .setMaterial(new Material().setKR(0.2)),
                new Triangle(new Point(-160, -150, -300), new Point(270, -400, -350),
                        new Point(-160, -150, 200)) //
                        .setEmission(new Color(BLACK)) //
                        .setMaterial(new Material().setKR(0.2)),
//
                //front
                new Polygon(
                        new Point(-40, -100, -210),
                        new Point(-40, -100, -260),
                        new Point(23, -100, -260),
                        new Point(23, -100, -210)
                ).setEmission(new Color(RED)),

                //Back Rectangle:
                new Polygon(
                        new Point(-40, -160, -200),
                        new Point(-40, -160, -250),
                        new Point(23, -160, -250),
                        new Point(23, -160, -200)
                ).setEmission(new Color(BLUE)),

                // Top Rectangle:
                new Polygon(
                        new Point(-40, -160, -200),
                        new Point(-40, -100, -210),
                        new Point(23, -100, -210),
                        new Point(23, -160, -200)
                ).setEmission(new Color(PINK)),

                //Bottom Rectangle:
                new Polygon(
                        new Point(23, -160, -250),
                        new Point(-40, -160, -250),
                        new Point(-40, -100, -260),
                        new Point(23, -100, -260)
                ).setEmission(new Color(WHITE)),

                //Left Rectangle:
                new Polygon(
                        new Point(-40, -100, -210),
                        new Point(-40, -160, -200),
                        new Point(-40, -160, -250),
                        new Point(-40, -100, -260)
                ).setEmission(new Color(GREEN)),

                //Right Rectangle:
                new Polygon(
                        new Point(23, -100, -210),
                        new Point(23, -160, -200),
                        new Point(23, -160, -250),
                        new Point(23, -100, -260)
                ).setEmission(new Color(YELLOW)),


                new Sphere(10,new Point(-10,-130,-200)).setEmission(new Color(RED))
                        .setMaterial(new Material().setKD(0.5).setKS(0.5).setNShininess(0)),

                new Sphere(20,new Point(-10,-130,-150)).setEmission(new Color(RED))
                        .setMaterial(new Material().setKD(0.5).setKS(0.5).setNShininess(0)),
                new Polygon( //פס
                        new Point(-25, -135, -223),
                        new Point(-25, -135, -225),
                        new Point(20, -135, -225),
                        new Point(20, -135, -223)
                ).setEmission(new Color(BLACK)));
        

        scene.lights.add(new SpotLight(new Color(245, 0, 179), new Point(75, 20, 100), new Vector(0, 0, -2))
                .setkL(4E-5).setkQ(2E-7));
        scene.lights.add( //
                new SpotLight(new Color(245, 222, 179), new Point(40, 40, 115), new Vector(-1, -1, -4)) //
                        .setkL(4E-4).setkQ(2E-6));
//        scene.lights.add(new SpotLight(new Color(WHITE),new Point(-10,-130,-150), new Vector(0,-1,0)));

        ImageWriter imageWriter = new ImageWriter("pictureToTest", 600, 600);
        camera.setImageWriter(imageWriter)
                .setRayTracer(new RayTracerBasic(scene))
                .renderImage()
                .writeToImage();
    }
}
