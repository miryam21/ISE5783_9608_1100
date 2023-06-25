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
                ).setEmission(new Color(166,189,219)),
                //רצפה
        new Polygon(
                new Point(140, 0, -280),
                new Point(140, -400, -280),
                new Point(-120, -400, -280),
                new Point(-120, 0, -280)
        ).setEmission(new Color(gray)),
                //קיר שמאלי
        new Polygon(
                new Point(140, -400, -90),
                new Point(140, -400, -280),
                new Point(140, 0, -280),
                new Point(140, 0, -90)
        ).setEmission(new Color(128,166,206)),
                //קיר ימני
                new Polygon(
                        new Point(-120, -400, -90),
                        new Point(-120, 0, -90),
                        new Point(-120, 0, -280),
                        new Point(-120, -400, -280)
                ).setEmission(new Color(128,166,206)),
        //תקרה
        new Polygon(
                new Point(140, -400, -90),
                new Point(-120, -400, -90),
                new Point(-120, 0, -90),
                new Point(140, 0, -90)
        ).setEmission(new Color(LIGHT_GRAY)),


                //שיש
                new Polygon(
                        new Point(140, -400, -250),
                        new Point(-120, -400, -250),
                        new Point(-120, -230, -250),
                        new Point(140, -230, -250)
                ).setEmission(new Color(6,77,135)),
                //דופן שיש קידמי
        new Polygon(
                new Point(140, -230, -250),
                new Point(-120, -230, -250),
                new Point(-120, -230, -266),
                new Point(140, -230, -266)
        ).setEmission(new Color(158,116, 39)),

        //מדפים
        //מדף שמאל
                new Cuboid(
                        new Point(100, -300, -220), 80, 80, 5,new Color(158,116, 39)),
                new Cuboid(
                        new Point(100, -300, -190), 80, 80, 5,new Color(158,116, 39)),
                new Cuboid(
                        new Point(100, -300, -160), 80, 80, 5,new Color(158,116, 39)),
                new Cuboid(
                        new Point(100, -300, -130), 80, 80, 5,new Color(158,116, 39)),
        //מדף ימין
                new Cuboid(
                        new Point(-160, -300, -220), 80, 80, 5,new Color(158,116, 39)),
                new Cuboid(
                        new Point(-160, -300, -190), 80, 80, 5,new Color(158,116, 39)),
                new Cuboid(
                        new Point(-160, -300, -160), 80, 80, 5,new Color(158,116, 39)),
                new Cuboid(
                        new Point(-160, -300, -130), 80, 80, 5,new Color(158,116, 39)),

        //מראה מרכזית
        new Polygon(
                new Point(70, -399, -110),
                new Point(-50, -399, -110),
                new Point(-50, -399, -230),
                new Point(70, -399, -230)
        ).setEmission(new Color(black)).setMaterial(new Material().setKR(0.9)));
        //תאורה מרכזית


        //כיור
//        new Polygon(
//                new Point(50, -350, -251),
//                new Point(-50, -350, -251),
//                new Point(-50, -305, -251),
//                new Point(50, -305, -251)
//        ).setEmission(new Color(white)));


        ImageWriter imageWriter = new ImageWriter("Project1", 600, 600);
        camera.setImageWriter(imageWriter)
                .setRayTracer(new RayTracerBasic(scene))
                .renderImage()
                .writeToImage();
    }
}
