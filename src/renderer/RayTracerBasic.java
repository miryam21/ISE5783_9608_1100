package renderer;

import geometries.Intersectable.GeoPoint;
import lighting.LightSource;
import primitives.*;
import scene.Scene;

import java.util.List;

import static primitives.Util.alignZero;

/**
 * The RayTracerBasic class represents a basic ray tracer implementation.
 * It traces rays and calculates the color of intersected objects in a scene.
 */
public class RayTracerBasic extends RayTracerBase {

    private static final double DELTA = 0.1;
    private static final int MAX_CALC_COLOR_LEVEL = 10;
    private static final double MIN_CALC_COLOR_K = 0.001;
    private static final Double3 INITIAL_K = Double3.ONE;

    /**
     * Constructs a RayTracerBasic object with the specified scene.
     *
     * @param scene The scene to be rendered
     */
    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    /**
     * Traces a ray and calculates the color of the intersected object.
     *
     * @param ray The ray to be traced
     * @return The color of the intersected object (returns null in this implementation)
     */
    @Override
    public Color traceRay(Ray ray) {
        GeoPoint geoPoint = scene.geometries.findClosestIntersection(ray);

        if (geoPoint == null)
            return this.scene.background;

        return calcColor(geoPoint, ray);
        //List<GeoPoint> intersections = scene.geometries.findGeoIntersections(ray);
        // return intersections == null ? scene.background : calcColor(ray.findClosestGeoPoint(intersections), ray);
    }

    /**
     * Calculates the color of the intersected object at the given intersection point.
     *
     * @param geoPoint The intersection point on the geometry
     * @param ray      The ray that intersected the object
     * @return The color of the intersected object
     */
    private Color calcColor(GeoPoint geoPoint, Ray ray) {
        return scene.ambientLight.getIntensity().add(calcColor(geoPoint, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K));
    }

    //    private Double3 transparency(GeoPoint geopoint,  Vector n,Vector l, LightSource lightSource){
//        Vector lightDirection = l.scale(-1); // from point to light source
//        Ray lightRay = new Ray(geopoint.point, lightDirection, n);
//
//        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay, lightSource.getDistance(geopoint.point));
//        if(intersections == null)
//            return Double3.ONE;
//
//        Double3 ktr = Double3.ONE; // full transparency
//        for (GeoPoint geoPoint : intersections) {
//            ktr = ktr.product(geoPoint.geometry.getMaterial().kT);
//
//            if(ktr.lowerThan(MIN_CALC_COLOR_K))
//                return Double3.ZERO; // no transparency
//        }
//        return ktr;
//    }
    private Double3 transparency(GeoPoint geopoint, Vector n, LightSource lightSource) {
        Vector lightDirection = lightSource.getL(geopoint.point).scale(-1); // from point to light source
        Ray lightRay = new Ray(geopoint.point, lightDirection, n);
        double lightDistance = lightSource.getDistance(geopoint.point);
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay);
        if (intersections == null) return Double3.ONE;

        Double3 transparent = Double3.ONE; // full transparency
        for (GeoPoint gp : intersections) {
            if (alignZero(gp.point.distance(geopoint.point) - lightDistance) <= 0) {
                transparent = transparent.product(gp.geometry.getMaterial().kT); //the more transparency the less shadow
                if (transparent.lowerThan(MIN_CALC_COLOR_K)) return Double3.ZERO;
            }
        }
        return transparent;
    }

    /**
     * Calculates the local effects (diffuse and specular) of light on the intersected object.
     *
     * @param geoPoint The intersection point on the geometry
     * @param ray      The ray that intersected the object
     * @return The color of the local effects
     */
    private Color calcLocalEffects(GeoPoint geoPoint, Ray ray, Double3 k) {
        Vector v = ray.getDir();
        Vector n = geoPoint.geometry.getNormal(geoPoint.point);
        double nv = alignZero(n.dotProduct(v));
        Color color = geoPoint.geometry.getEmission();

        if (nv == 0)
            return color;
        Double3 KD = geoPoint.geometry.getMaterial().kD;
        Double3 KS = geoPoint.geometry.getMaterial().kS;
        double nShininess = geoPoint.geometry.getMaterial().nShininess;
        Material material = geoPoint.geometry.getMaterial();

        for (LightSource lightSource : scene.lights) {
            Vector lightVector = lightSource.getL(geoPoint.point);
            double nl = alignZero(lightVector.dotProduct(n));
            Color intensity = lightSource.getIntensity(geoPoint.point);

            if (nl * nv > 0) {
                Double3 ktr = transparency(geoPoint, n, lightSource);

                if (!ktr.product(k).lowerThan(MIN_CALC_COLOR_K)) {
                    Double3 effects = calcDiffuse(nl, KD).add(calcSpecular(lightVector, n, v, nShininess, KS));
                    color = color.add(intensity.scale(effects).scale(ktr));
                }
            }
        }

        return color;
    }

    private Color calcDiffusive(Material material, Vector light, Vector n, Color lightIntensity) {
        // the phong model formula for the diffusive effect: 𝒌𝑫 ∙| 𝒍 ∙ 𝒏 |∙ 𝑰
        return lightIntensity.scale(material.kD.scale(Math.abs(n.dotProduct(light))));
    }

    private Double3 calcDiffuse(double ln, Double3 Kd) {
        double abs = Math.abs(ln);
        return Kd.scale(abs);
    }


    private Double3 calcSpecular(Vector l, Vector n, Vector v, double nShininess, Double3 ks) {
        Vector r = reflectionVector(l, n);
        double vr = v.scale(-1).dotProduct(r);
        double max = Math.max(0, vr);
        double pow = Math.pow(max, nShininess);

        return ks.scale(alignZero(pow));
    }

    private boolean unshaded(GeoPoint gp, LightSource light, Vector l, Vector n) {
        Vector lightDirection = l.scale(-1);

        Ray lightRay = new Ray(gp.point, lightDirection, n);

        double distance = light.getDistance(gp.point);

        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay, distance);

        if (intersections == null)
            return true;

        for (GeoPoint geoPoint : intersections) {
            if (geoPoint.geometry.getMaterial().kT.lowerThan(MIN_CALC_COLOR_K)) {
                return false;
            }
        }

        return true;

    }

    private Color calcColor(GeoPoint geoPoint, Ray ray, int level, Double3 k) {
        Color color = calcLocalEffects(geoPoint, ray, k);

        return 1 == level ? color : color.add(calcGlobalEffects(geoPoint, ray, level, k));

    }

    private GeoPoint findClosestIntersection(Ray ray) {
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(ray);
        if (intersections == null)
            return null;
        return ray.findClosestGeoPoint(intersections);
    }

    private Color calcGlobalEffects(GeoPoint gp, Ray ray, int level, Double3 k) {
        Color color = Color.BLACK;
        Vector n = gp.geometry.getNormal(gp.point);
        Vector v = ray.getDir();
        Material material = gp.geometry.getMaterial();
        Double3 kkr = material.kR.product(k);

        if (!kkr.lowerThan(MIN_CALC_COLOR_K)) // the color is effected by the reflection
            color = color.add(calcGlobalEffects(constructReflectedRay(gp.point, v, n), level, material.kR, kkr));

        Double3 kkt = material.kT.product(k);
        if (!kkt.lowerThan(MIN_CALC_COLOR_K)) // the color is effected due to the transparency
            color = color.add(calcGlobalEffects(constructRefractedRay(gp.point, v, n), level, material.kT, kkt));

        return color;
    }

    private Color calcGlobalEffects(Ray ray, int level, Double3 kx, Double3 kkx) {
        GeoPoint geoPoint = findClosestIntersection(ray);
        return (geoPoint == null ? scene.background : calcColor(geoPoint, ray, level - 1, kkx).scale(kx));
    }

    private Ray constructRefractedRay(Point point, Vector v, Vector n) {
        return new Ray(point, v, n);
    }

    private Ray constructReflectedRay(Point point, Vector v, Vector n) {
        return new Ray(point, reflectionVector(v, n), n);
    }

    private Vector reflectionVector(Vector l, Vector n) {

        return l.subtract(n.scale(2 * l.dotProduct(n))).normalize();
    }
    // bla
}
