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
                ).setEmission(new Color(WHITE)),
                //רצפה
        new Polygon(
                new Point(140, 0, -280),
                new Point(140, -400, -280),
                new Point(-120, -400, -280),
                new Point(-120, 0, -280)
        ).setEmission(new Color(PINK)),
                //קיר שמאלי
        new Polygon(
                new Point(140, -400, -90),
                new Point(140, -400, -280),
                new Point(140, 0, -280),
                new Point(140, 0, -90)
        ).setEmission(new Color(LIGHT_GRAY)),
                //קיר ימני
                new Polygon(
                        new Point(-120, -400, -90),
                        new Point(-120, 0, -90),
                        new Point(-120, 0, -280),
                        new Point(-120, -400, -280)
                ).setEmission(new Color(LIGHT_GRAY)),
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
                ).setEmission(new Color(BLACK)),
                //דופן שיש קידמי
        new Polygon(
                new Point(140, -230, -250),
                new Point(-120, -230, -250),
                new Point(-120, -230, -270),
                new Point(140, -230, -270)
        ).setEmission(new Color(BLACK)),

        //מדפים
        //מדף שמאל
        new Polygon(
                new Point(140, -400, -210),
                new Point(100, -400, -210),
                new Point(100, -300, -210),
                new Point(140, -300, -210)
        ).setEmission(new Color(PINK)),
        new Polygon(
                new Point(140, -300, -210),
                new Point(100, -300, -210),
                new Point(100, -300, -215),
                new Point(140, -300, -215)
        ).setEmission(new Color(PINK)),
                new Polygon(
                        new Point(100, -400, -210),
                        new Point(100, -400, -215),
                        new Point(100, -300, -215),
                        new Point(100, -300, -210)
                ).setEmission(new Color(PINK)),

                new Polygon(
                        new Point(140, -400, -180),
                        new Point(100, -400, -180),
                        new Point(100, -300, -180),
                        new Point(140, -300, -180)
                ).setEmission(new Color(PINK)),
                new Polygon(
                        new Point(140, -300, -180),
                        new Point(100, -300, -180),
                        new Point(100, -300, -185),
                        new Point(140, -300, -185)
                ).setEmission(new Color(PINK)),
                new Polygon(
                        new Point(100, -400, -180),
                        new Point(100, -400, -185),
                        new Point(100, -300, -185),
                        new Point(100, -300, -180)
                ).setEmission(new Color(PINK)));




        ImageWriter imageWriter = new ImageWriter("Project1", 600, 600);
        camera.setImageWriter(imageWriter)
                .setRayTracer(new RayTracerBasic(scene))
                .renderImage()
                .writeToImage();
    }
}
