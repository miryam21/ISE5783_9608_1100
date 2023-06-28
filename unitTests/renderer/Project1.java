package renderer;
import geometries.*;
import lighting.AmbientLight;
import lighting.DirectionalLight;
import lighting.PointLight;
import lighting.SpotLight;
import org.junit.jupiter.api.Test;
import primitives.*;
import scene.Scene;

import static java.awt.Color.*;
public class Project1 {
    private Scene scene = new Scene("Test scene").setBackground(new Color(WHITE)).setBackground(new Color(GRAY));
    @Test
    public void room() {
//         Camera camera = new Camera(new Point(0, -150, 800), new Vector(0, 0, -1), new Vector(0, 1, 0))
//              .setVPSize(200, 200).setVPDistance(400);

//          Camera camera = new Camera(new Point(1100, -50, -250), new Vector(-1, 0, 0), new Vector(0, 0, 1))
//        .setVPSize(200, 200).setVPDistance(400);

//        Camera camera = new Camera(new Point(10, 1200, -200), new Vector(0, -1, 0), new Vector(1 / 2, 0, 1))
//        .setVPSize(200, 200).setVPDistance(1000);
// מצלמה קידמית
        Camera camera = new Camera(new Point(10, 1200, -200), new Vector(0, -1, 0), new Vector(1 / 2, 0, 1))
                .setVPSize(200, 200).setVPDistance(700);

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
                //שורה1
                new Polygon(
                        new Point(140, -400, -279),
                        new Point(100, -400, -279),
                        new Point(100, -360, -279),
                        new Point(140, -360, -279)
                ).setEmission(new Color(BLACK)),
                new Polygon(
                        new Point(140, -320, -279),
                        new Point(100, -320, -279),
                        new Point(100, -280, -279),
                        new Point(140, -280, -279)
                ).setEmission(new Color(BLACK)),
                new Polygon(
                        new Point(140, -240, -279),
                        new Point(100, -240, -279),
                        new Point(100, -200, -279),
                        new Point(140, -200, -279)
                ).setEmission(new Color(BLACK)),
                new Polygon(
                        new Point(140, -160, -279),
                        new Point(100, -160, -279),
                        new Point(100, -120, -279),
                        new Point(140, -120, -279)
                ).setEmission(new Color(BLACK)),
                new Polygon(
                        new Point(140, -80, -279),
                        new Point(100, -80, -279),
                        new Point(100, -40, -279),
                        new Point(140, -40, -279)
                ).setEmission(new Color(BLACK)),
                //שורה 2
                       new Polygon(
                        new Point(100, -360, -279),
                        new Point(60, -360, -279),
                        new Point(60, -320, -279),
                        new Point(100, -320, -279)
                ).setEmission(new Color(BLACK)),
                new Polygon(
                        new Point(100, -280, -279),
                        new Point(60, -280, -279),
                        new Point(60, -240, -279),
                        new Point(100, -240, -279)
                ).setEmission(new Color(BLACK)),
                new Polygon(
                        new Point(100, -200, -279),
                        new Point(60, -200, -279),
                        new Point(60, -160, -279),
                        new Point(100, -160, -279)
                ).setEmission(new Color(BLACK)),
                new Polygon(
                        new Point(100, -120, -279),
                        new Point(60, -120, -279),
                        new Point(60, -80, -279),
                        new Point(100, -80, -279)
                ).setEmission(new Color(BLACK)),
                new Polygon(
                        new Point(100, -40, -279),
                        new Point(60, -40, -279),
                        new Point(60, -1, -279),
                        new Point(100, -1, -279)
                ).setEmission(new Color(BLACK)),
                //שורה3
                new Polygon(
                        new Point(60, -400, -279),
                        new Point(20, -400, -279),
                        new Point(20, -360, -279),
                        new Point(60, -360, -279)
                ).setEmission(new Color(BLACK)),
                new Polygon(
                        new Point(60, -320, -279),
                        new Point(20, -320, -279),
                        new Point(20, -280, -279),
                        new Point(60, -280, -279)
                ).setEmission(new Color(BLACK)),
                new Polygon(
                        new Point(60, -240, -279),
                        new Point(20, -240, -279),
                        new Point(20, -200, -279),
                        new Point(60, -200, -279)
                ).setEmission(new Color(BLACK)),
                new Polygon(
                        new Point(60, -160, -279),
                        new Point(20, -160, -279),
                        new Point(20, -120, -279),
                        new Point(60, -120, -279)
                ).setEmission(new Color(BLACK)),
                new Polygon(
                        new Point(60, -80, -279),
                        new Point(20, -80, -279),
                        new Point(20, -40, -279),
                        new Point(60, -40, -279)
                ).setEmission(new Color(BLACK)),
                //שורה4
                new Polygon(
                        new Point(20, -360, -279),
                        new Point(-20, -360, -279),
                        new Point(-20, -320, -279),
                        new Point(20, -320, -279)
                ).setEmission(new Color(BLACK)),
                new Polygon(
                        new Point(20, -280, -279),
                        new Point(-20, -280, -279),
                        new Point(-20, -240, -279),
                        new Point(20, -240, -279)
                ).setEmission(new Color(BLACK)),
                new Polygon(
                        new Point(20, -200, -279),
                        new Point(-20, -200, -279),
                        new Point(-20, -160, -279),
                        new Point(20, -160, -279)
                ).setEmission(new Color(BLACK)),
                new Polygon(
                        new Point(20, -120, -279),
                        new Point(-20, -120, -279),
                        new Point(-20, -80, -279),
                        new Point(20, -80, -279)
                ).setEmission(new Color(BLACK)),
                new Polygon(
                        new Point(20, -40, -279),
                        new Point(-20, -40, -279),
                        new Point(-20, -1, -279),
                        new Point(20,-1, -279)
                ).setEmission(new Color(BLACK)),
                //שורה5
                new Polygon(
                        new Point(-20, -400, -279),
                        new Point(-60, -400, -279),
                        new Point(-60, -360, -279),
                        new Point(-20, -360, -279)
                ).setEmission(new Color(BLACK)),
                new Polygon(
                        new Point(-20, -320, -279),
                        new Point(-60, -320, -279),
                        new Point(-60, -280, -279),
                        new Point(-20, -280, -279)
                ).setEmission(new Color(BLACK)),
                new Polygon(
                        new Point(-20, -240, -279),
                        new Point(-60, -240, -279),
                        new Point(-60, -200, -279),
                        new Point(-20, -200, -279)
                ).setEmission(new Color(BLACK)),
                new Polygon(
                        new Point(-20, -160, -279),
                        new Point(-60, -160, -279),
                        new Point(-60, -120, -279),
                        new Point(-20, -120, -279)
                ).setEmission(new Color(BLACK)),
                new Polygon(
                        new Point(-20, -80, -279),
                        new Point(-60, -80, -279),
                        new Point(-60, -40, -279),
                        new Point(-20, -40, -279)
                ).setEmission(new Color(BLACK)),
                //שורה6
                new Polygon(
                            new Point(-60, -360, -279),
                            new Point(-100, -360, -279),
                            new Point(-100, -320, -279),
                            new Point(-60, -320, -279)
                    ).setEmission(new Color(BLACK)),
                    new Polygon(
                            new Point(-60, -280, -279),
                            new Point(-100, -280, -279),
                            new Point(-100, -240, -279),
                            new Point(-60, -240, -279)
                    ).setEmission(new Color(BLACK)),
                    new Polygon(
                            new Point(-60, -200, -279),
                            new Point(-100, -200, -279),
                            new Point(-100, -160, -279),
                            new Point(-60, -160, -279)
                    ).setEmission(new Color(BLACK)),
                    new Polygon(
                            new Point(-60, -120, -279),
                            new Point(-100, -120, -279),
                            new Point(-100, -80, -279),
                            new Point(-60, -80, -279)
                    ).setEmission(new Color(BLACK)),
                    new Polygon(
                            new Point(-60, -40, -279),
                            new Point(-100, -40, -279),
                            new Point(-100, 0, -279),
                            new Point(-60, 0, -279)
                    ).setEmission(new Color(BLACK)),
                //שורה7
                     new Polygon(
                        new Point(-100, -400, -279),
                        new Point(-120, -400, -279),
                        new Point(-120, -360, -279),
                        new Point(-100, -360, -279)
                ).setEmission(new Color(BLACK)),
                new Polygon(
                        new Point(-100, -320, -279),
                        new Point(-120, -320, -279),
                        new Point(-120, -280, -279),
                        new Point(-100, -280, -279)
                ).setEmission(new Color(BLACK)),
                new Polygon(
                        new Point(-100, -240, -279),
                        new Point(-120, -240, -279),
                        new Point(-120, -200, -279),
                        new Point(-100, -200, -279)
                ).setEmission(new Color(BLACK)),
                new Polygon(
                        new Point(-100, -160, -279),
                        new Point(-120, -160, -279),
                        new Point(-120, -120, -279),
                        new Point(-100, -120, -279)
                ).setEmission(new Color(BLACK)),
                new Polygon(
                        new Point(-100, -80, -279),
                        new Point(-120, -80, -279),
                        new Point(-120, -40, -279),
                        new Point(-100, -40, -279)
                ).setEmission(new Color(BLACK)),


//                קיר שמאלי
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
                            new Point(90, -300, -200), 50, 70, 5,new Color(158,116, 39)),
                    new Cuboid(
                            new Point(90, -300, -170), 50, 70, 5,new Color(158,116, 39)),
                    new Cuboid(
                            new Point(90, -300, -140), 50, 70, 5,new Color(158,116, 39)),
        //מדף ימין
                new Cuboid(
                        new Point(-115, -300, -200), 50, 70, 5,new Color(158,116, 39)),
                new Cuboid(
                        new Point(-115, -300, -170), 50, 70, 5,new Color(158,116, 39)),
                new Cuboid(
                        new Point(-115, -300, -140), 50, 70, 5,new Color(158,116, 39)),

        //מראה מרכזית
                new Polygon(
                new Point(50, -399, -130),
                new Point(-30, -399, -130),
                new Point(-30, -399, -200),
                new Point(50, -399, -200)
        ).setEmission(new Color(BLACK)).setMaterial(new Material().setKR(0.9)),
        new Triangle(new Point(50, -399, -130),new Point(-30, -399, -130),new Point(10, -399, -115))
                .setEmission(new Color(BLACK)).setMaterial(new Material().setKR(0.9)),
        new Triangle(new Point(50, -399, -200),new Point(-30, -399, -200),new Point(10, -399, -215))
                        .setEmission(new Color(BLACK)).setMaterial(new Material().setNShininess(30).setKR(0.9)),


//        כיור
        new Polygon(
                new Point(65, -390, -234),
                new Point(-45, -390, -234),
                new Point(-45, -290, -234),
                new Point(65, -290, -234)
        ).setEmission(new Color(234, 235, 240)),
        new Polygon(
                new Point(65, -350, -240),
                new Point(65, -350, -225),
                new Point(-45, -350, -225),
                new Point(-45, -350, -240)
        ).setEmission(new Color(234, 235, 240)),
        new Polygon(
                new Point(65, -390, -244),
                new Point(65, -390, -226),
                new Point(65, -290, -226),
                new Point(65, -290, -244)
        ).setEmission(new Color(234, 235, 241)),
        new Polygon(
                new Point(-45, -390, -244),
                new Point(-45, -390, -226),
                new Point(-45, -290, -226),
                new Point(-45, -290, -244)
        ).setEmission(new Color(234, 235, 240)),
        new Polygon(
                new Point(65, -290, -244),
                new Point(-45, -290, -244),
                new Point(-45, -290, -226),
                new Point(65, -290, -226)
        ).setEmission(new Color(234, 235, 240)),
                new Cuboid(
                        new Point(-43, -290, -226), 106,98 , 15,new Color(146,206,223)),
//                ברזים
                new Cylinder(new Ray(new Point(43,-400,-212), new Vector(0,1,0)),4,30),
                new Cylinder(new Ray(new Point(-28,-400,-212), new Vector(0,1,0)),4,30),
                new Sphere(4, new Point(43, -370, -213)).setEmission(new Color(BLACK)),
                new Sphere(4, new Point(-28, -370, -213)).setEmission(new Color(black)),
                //ספוטים:
                //שמאל:
                new Sphere(4, new Point(-37, -400, -135))
                        .setMaterial(new Material().setKD(0.2).setKS(0.2).setNShininess(1).setKT(0.6)),
                new Sphere(4, new Point(-37, -400, -150))
                        .setMaterial(new Material().setKD(0.2).setKS(0.2).setNShininess(1).setKT(0.6)),
                new Sphere(4, new Point(-37, -400, -165))
                        .setMaterial(new Material().setKD(0.2).setKS(0.2).setNShininess(1).setKT(0.6)),
                new Sphere(4, new Point(-37, -400, -180))
                        .setMaterial(new Material().setKD(0.2).setKS(0.2).setNShininess(1).setKT(0.6)),
                new Sphere(4, new Point(-37, -400, -195))
                        .setMaterial(new Material().setKD(0.2).setKS(0.2).setNShininess(1).setKT(0.6)),



// left
                new Sphere(4, new Point(57, -400, -135))
                        .setMaterial(new Material().setKD(0.4).setKS(0.3).setNShininess(100).setKT(0.3)),
                new Sphere(4, new Point(57, -400, -150))
                        .setMaterial(new Material().setKD(0.2).setKS(0.2).setNShininess(1).setKT(0.6)),
                new Sphere(4, new Point(57, -400, -165))
                        .setMaterial(new Material().setKD(0.2).setKS(0.2).setNShininess(1).setKT(0.6)),
                new Sphere(4, new Point(57, -400, -180))
                        .setMaterial(new Material().setKD(0.2).setKS(0.2).setNShininess(1).setKT(1)),
                new Sphere(4, new Point(57, -400, -195))
                        .setMaterial(new Material().setKD(0.2).setKS(0.2).setNShininess(1).setKT(0.6)),


// כדור בדולח
                new Sphere(13, new Point(120, -400, -190))
                        .setMaterial(new Material().setKD(0.5).setKS(0.2).setKT(0.9)),

                new Sphere(13, new Point(-100, -400, -190))
                        .setMaterial(new Material().setKD(0.5).setKS(0.2).setKT(0.9)),
        //מנורה 1
                new Sphere(11, new Point(15, -140, -150)).setEmission(new Color(BLUE))
                        .setMaterial(new Material().setKD(0.2).setKS(0.2).setNShininess(40).setKT(0.6)),
                new Cylinder( new Ray(new Point(15,-140,-150),new Vector(0,0,1)),0.7,41)
                        .setEmission( new Color(BLACK)),
        // מנורה קטנה
                new Sphere(8, new Point(25, -300, -123)).setEmission(new Color(BLUE))
                        .setMaterial(new Material().setKD(0.2).setKS(0.2).setNShininess(40).setKT(0.6)),
                new Cylinder( new Ray(new Point(25,-300,-123),new Vector(0,0,1)),0.7,25)
                        .setEmission( new Color(BLACK)),
//                new Cuboid(new Point(110, -300, -160),20,20,10,new Color(GRAY)),
        // מנורה 3
                new Sphere(15d, new Point(0, -230, -130)).setEmission(new Color(BLUE))
                        .setMaterial(new Material().setKD(0.2).setKS(0.2).setNShininess(40).setKT(0.6)),
                 new Cylinder( new Ray(new Point(0,-230,-100),new Vector(0,0,-1)),0.7,15)
                         .setEmission( new Color(BLACK)),
//גלילי נייר שמאל
                new Cylinder( new Ray(new Point(129,-330,-128),new Vector(0,0,-1)),10,15)
                        .setEmission( new Color(white)),
                new Cylinder( new Ray(new Point(100,-330,-128),new Vector(0,0,-1)),10,15)
                        .setEmission( new Color(white)),
                new Cylinder( new Ray(new Point(115,-330,-115),new Vector(0,0,-1)),10,15)
                        .setEmission( new Color(white)),
//גלילי נייר שמאל
                new Cylinder( new Ray(new Point(-75,-330,-128),new Vector(0,0,-1)),10,15)
                        .setEmission( new Color(white)),
                new Cylinder( new Ray(new Point(-105,-330,-128),new Vector(0,0,-1)),10,15)
                        .setEmission( new Color(white)),
                new Cylinder( new Ray(new Point(-89,-330,-115),new Vector(0,0,-1)),10,15)
                        .setEmission( new Color(white)),
//  מדף סבון בקבוק
                new Cylinder( new Ray(new Point(125,-330,-160),new Vector(0,0,-1)),6,12)
                        .setEmission( new Color(9,28,87)),
                new Sphere(6, new Point(125,-330,-160)).setEmission(new Color(9,28,87)),
                new Cylinder(new Ray(new Point(125,-330,-150),new Vector(0,0,-1)),2,4)
                        .setEmission(new Color(9,28,87)),

                new Cylinder( new Ray(new Point(110,-330,-160),new Vector(0,0,-1)),6,12)
                        .setEmission( new Color(9,28,87)).setMaterial(new Material().setKT(0.6)),
//  מדף סבון בקבוק
                new Cylinder( new Ray(new Point(-105,-330,-160),new Vector(0,0,-1)),6,12)
                        .setEmission( new Color(9,28,87)),
                new Sphere(6, new Point(-105,-330,-160)).setEmission(new Color(9,28,87)),
                new Cylinder(new Ray(new Point(-105,-330,-150),new Vector(0,0,-1)),2,4)
                        .setEmission(new Color(9,28,87)),

                new Cylinder( new Ray(new Point(-90,-330,-160),new Vector(0,0,-1)),6,12)
                        .setEmission( new Color(9,28,87)).setMaterial(new Material().setKT(0.6)),

// בועות סבון
                new Sphere(4, new Point(0, -300, -215)).setEmission(new Color(CYAN))
                        .setMaterial(new Material().setKD(0.2).setKS(0.2).setNShininess(40).setKT(0.8)),
                new Sphere(5, new Point(10, -300, -207)).setEmission(new Color(CYAN))
                        .setMaterial(new Material().setKD(0.2).setKS(0.2).setNShininess(40).setKT(0.8)),
                new Sphere(3, new Point(8, -300, -220)).setEmission(new Color(CYAN))
                        .setMaterial(new Material().setKD(0.2).setKS(0.2).setNShininess(40).setKT(0.8)),

//סבון בקבוק
                new Cylinder( new Ray(new Point(90,-240,-225),new Vector(0,0,-1)),8,15)
                        .setEmission( new Color(BLACK)),
                new Sphere(8, new Point(90,-240,-225)).setEmission(new Color(BLACK)),
                new Cylinder(new Ray(new Point(90,-240,-215),new Vector(0,0,-1)),4,6)
                        .setEmission(new Color(BLACK)));


        //  אור 1
//        scene.lights.add( //
//                new SpotLight(new Color(1000, 600, 0), new Point(15, -140, -150), new Vector(-1, -1, -2)) //
//                        .setkL(0.0004).setkQ(0.0000006));
        scene.lights.add(new PointLight(new Color(white), new Point(15,-140,-150)).setkL(0.0004).setkQ(0.0000006));

//        scene.lights.add( //   //אור מנורה 2
//                new SpotLight(new Color(1000, 600, 0), new Point(15, -300, -123), new Vector(-1, -1, -2)) //
//                        .setkL(0.0004).setkQ(0.0000006));
        scene.lights.add(new PointLight(new Color(white), new Point(15,-300,-123)).setkL(0.0004).setkQ(0.0000006));

//        scene.lights.add( //   //אור מנורה 3
//                new SpotLight(new Color(1000, 600, 0), new Point(0, -230, -130), new Vector(-1, -1, -2)) //
//                        .setkL(0.0004).setkQ(0.0000006));
        scene.lights.add(new PointLight(new Color(white), new Point(0,-230,-130)).setkL(0.0004).setkQ(0.0000006));




        scene.lights.add(new SpotLight(new Color(GREEN), new Point(10, 0, -100), new Vector(0,-1,-0.5))
               .setkL(4E-4).setkQ(2E-5));


        //ספוטים
        scene.lights.add(new PointLight(new Color(1000,600,0), new Point(-37, -400, -135))
                .setkL(0.001).setkQ(0.0002));
        scene.lights.add(new PointLight(new Color(1000,600,0), new Point(-37, -400, -150))
                .setkL(0.001).setkQ(0.0002));
        scene.lights.add(new PointLight(new Color(1000, 600, 0), new Point(-37, -400, -165))
                .setkL(0.001).setkQ(0.0002));
        scene.lights.add(new PointLight(new Color(1000, 600, 0), new Point(-37, -400, -180))
                .setkL(0.001).setkQ(0.0002));
        scene.lights.add(new PointLight(new Color(1000, 600, 0), new Point( -37, -400, -195))
                .setkL(0.001).setkQ(0.0002));

        scene.lights.add(new PointLight(new Color(1000, 600, 0), new Point(57, -400, -135))
                .setkL(0.001).setkQ(0.0002));
        scene.lights.add(new PointLight(new Color(1000,600,0), new Point(-37, -400, -150))
                .setkL(0.001).setkQ(0.0002));
        scene.lights.add(new PointLight(new Color(1000, 600, 0), new Point(57, -400, -165))
                .setkL(0.001).setkQ(0.0002));
        scene.lights.add(new PointLight(new Color(1000, 600, 0), new Point(57, -400, -180))
                .setkL(0.001).setkQ(0.0002));
        scene.lights.add(new PointLight(new Color(1000, 600, 0), new Point( 57, -400, -195))
                .setkL(0.001).setkQ(0.0002));

        scene.lights.add(new PointLight(new Color(1000,600,0),new Point(120, -399, -110)).
                        setkQ(0.000000002).setkL(0.0001));



        ImageWriter imageWriter = new ImageWriter("Project1", 600, 600);
        camera.setImageWriter(imageWriter)
                .setRayTracer(new RayTracerBasic(scene))
                .renderImage()
                .writeToImage();
    }
}
