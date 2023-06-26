package renderer;
import geometries.*;
import lighting.AmbientLight;
import lighting.SpotLight;
import org.junit.jupiter.api.Test;
import primitives.*;
import scene.Scene;

import static java.awt.Color.*;
public class Project1 {
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
                //קיר אחורי
                new Polygon(
                        new Point(-120, -400, -90),
                        new Point(-120, -400, -280),
                        new Point(140, -400, -280),
                        new Point(140, -400, -90)
                ).setEmission(new Color(109,141,175)),
                //רצפה
        new Polygon(
                new Point(140, 0, -280),
                new Point(140, -400, -280),
                new Point(-120, -400, -280),
                new Point(-120, 0, -280)
        ).setEmission(new Color(white)),
                //קיר שמאלי
        new Polygon(
                new Point(140, -400, -90),
                new Point(140, -400, -280),
                new Point(140, 0, -280),
                new Point(140, 0, -90)
        ).setEmission(new Color(185,202,222)),
                //קיר ימני
                new Polygon(
                        new Point(-120, -400, -90),
                        new Point(-120, 0, -90),
                        new Point(-120, 0, -280),
                        new Point(-120, -400, -280)
                ).setEmission(new Color(185,202,222)),
        //תקרה
        new Polygon(
                new Point(140, -400, -90),
                new Point(-120, -400, -90),
                new Point(-120, 0, -90),
                new Point(140, 0, -90)
        ).setEmission(new Color(LIGHT_GRAY)),


                //שיש
                new Cuboid(
                        new Point(-120, -230, -240), 260, 162, 5,new Color(9,28,87)),
              //דופן שיש קידמי
        new Polygon(
                new Point(140, -230, -240),
                new Point(-120, -230, -240),
                new Point(-120, -230, -260),
                new Point(140, -230, -260)
        ).setEmission(new Color(158,116, 39)),

        //מדפים
        //מדף שמאל
                new Cuboid(
                        new Point(100, -300, -200), 80, 80, 5,new Color(158,116, 39)),
                new Cuboid(
                        new Point(100, -300, -170), 80, 80, 5,new Color(158,116, 39)),
                new Cuboid(
                        new Point(100, -300, -140), 80, 80, 5,new Color(158,116, 39)),
        //מדף ימין
                new Cuboid(
                        new Point(-160, -300, -200), 80, 80, 5,new Color(158,116, 39)),
                new Cuboid(
                        new Point(-160, -300, -170), 80, 80, 5,new Color(158,116, 39)),
                new Cuboid(
                        new Point(-160, -300, -140), 80, 80, 5,new Color(158,116, 39)),

        //מראה מרכזית
        new Polygon(
                new Point(70, -399, -110),
                new Point(-50, -399, -110),
                new Point(-50, -399, -210),
                new Point(70, -399, -210)
        ).setEmission(new Color(black)).setMaterial(new Material().setKR(0.9)),
        //תאורה מרכזית


//        כיור
        new Polygon(
                new Point(65, -350, -240),
                new Point(-45, -350, -240),
                new Point(-45, -150, -240),
                new Point(65, -150, -240)
        ).setEmission(new Color(234, 235, 240)),
        new Polygon(
                new Point(65, -350, -240),
                new Point(65, -350, -225),
                new Point(-45, -350, -225),
                new Point(-45, -350, -240)
        ).setEmission(new Color(234, 235, 240)),
        new Polygon(
                new Point(65, -350, -240),
                new Point(65, -350, -225),
                new Point(65, -150, -225),
                new Point(65, -150, -240)
        ).setEmission(new Color(234, 235, 240)),
        new Polygon(
                new Point(-45, -350, -240),
                new Point(-45, -350, -225),
                new Point(-45, -150, -225),
                new Point(-45, -150, -240)
        ).setEmission(new Color(234, 235, 240)),
        new Polygon(
                new Point(65, -150, -240),
                new Point(-45, -150, -240),
                new Point(-45, -150, -225),
                new Point(65, -150, -225)
        ).setEmission(new Color(234, 235, 240)));

        scene.geometries.add(
                new Sphere(15d, new Point(0, -230, -110)).setEmission(new Color(BLUE))
                        .setMaterial(new Material().setKD(0.2).setKS(0.2).setNShininess(30).setKT(0.6)));

        scene.lights.add(new SpotLight(new Color(white), new Point(0, -230, -110), new Vector(0, 0, -1)) //
                .setkL(4E-5).setkQ(2E-7));


        ImageWriter imageWriter = new ImageWriter("Project1", 600, 600);
        camera.setImageWriter(imageWriter)
                .setRayTracer(new RayTracerBasic(scene))
                .renderImage()
                .writeToImage();
    }
}
