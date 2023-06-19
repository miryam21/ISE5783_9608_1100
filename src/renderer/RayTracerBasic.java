package renderer;

import lighting.LightSource;
import primitives.Color;
import primitives.*;
import primitives.Ray;
import scene.Scene;
import geometries.Intersectable.GeoPoint;
import java.util.List;
import static primitives.Util.*;

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
        GeoPoint geoPoint=findClosestIntersection(ray);
        if (geoPoint==null)
            return this.scene.background;
        return calcColor(geoPoint,ray);
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
        return scene.ambientLight.getIntensity().add(calcColor(geoPoint, ray,MAX_CALC_COLOR_LEVEL,INITIAL_K));
    }
    private Double3 transparency(GeoPoint geopoint,  Vector n, LightSource lightSource){
        Vector lightDirection = lightSource.getL(geopoint.point).scale(-1); // from point to light source
        Ray lightRay = new Ray(geopoint.point, lightDirection, n);

        List<GeoPoint> intersections = scene.geometries.
                findGeoIntersections(lightRay,
                        lightSource.getDistance(geopoint.point));
        if(intersections == null) return Double3.ONE;

        Double3 transparent = Double3.ONE; // full transparency
        for (GeoPoint gp : intersections) {
            transparent = transparent.product(gp.geometry.getMaterial().kT);
            if(transparent.subtract(new Double3(MIN_CALC_COLOR_K)).lowerThan(0))
                return Double3.ZERO; // no transparency
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
        Color color = Color.BLACK;

        if (nv == 0)
            return Color.BLACK;

        Material material = geoPoint.geometry.getMaterial();

        for (LightSource lightSource : scene.lights) {
            Vector lightVector = lightSource.getL(geoPoint.point);
            double nl = alignZero(n.dotProduct(lightVector));

            if (nl * nv > 0) {
                Double3 ktr= transparency(geoPoint, n, lightSource);
                if (!ktr.product(k).lowerThan(MIN_CALC_COLOR_K)) {
                    Color lightIntensity = lightSource.getIntensity(geoPoint.point).scale(ktr);
                    color = color.add(
                            calcDiffusive(material, lightVector, n, lightIntensity),
                            calcSpecular(material,  lightVector,n, v, lightIntensity));
                }
            }
        }

        return color;
    }

    private Color calcDiffusive(Material material, Vector light, Vector n, Color lightIntensity) {
        // the phong model formula for the diffusive effect: 𝒌𝑫 ∙| 𝒍 ∙ 𝒏 |∙ 𝑰
        return lightIntensity.scale(material.kD.scale(Math.abs(n.dotProduct(light))));
}

    /**
     * Calculates the specular component of light reflection on the surface of the object.
     *
     * @param material     The material of the object
     * @param normal       The surface normal vector
     * @param lightVector  The vector from the intersection point to the light source
     * @param nl           The dot product between the normal and light vectors
     * @param viewVector   The vector from the intersection point to the viewer
     * @return The specular component
     */
    private Double3 calcSpecular(Material material, Vector normal, Vector lightVector, double nl, Vector viewVector) {
        Vector reflectedVector = lightVector.subtract(normal.scale(2 * nl));
        double max = Math.max(0, viewVector.scale(-1).dotProduct(reflectedVector));
        return material.kS.scale(Math.pow(max, material.nShininess));
    }
    private Color calcSpecular(Material mat, Vector l, Vector n, Vector v, Color lightIntensity) {
        Vector r = reflectionVector(l, n);    // the specular ray
        // the phong model formula for the specular effect: ks ∙ ( 𝒎𝒂𝒙 (𝟎, −𝒗 ∙ 𝒓) ^ 𝒏𝒔𝒉 ) ∙ 𝑰
        return lightIntensity
                .scale(mat.kS.scale( alignZero( Math.pow( Math.max(0, v.scale(-1).dotProduct(r)),
                        mat.nShininess))));
    }

    private boolean unshaded(GeoPoint gp,LightSource light, Vector l, Vector n,double nl) {
        Vector lightDirection = l.scale(-1);

        Ray lightRay = new Ray(gp.point, lightDirection, n);

        double distance = light.getDistance(gp.point);

        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay, distance);

        if (intersections == null)
            return true;

        Double3 transperent= Double3.ONE;
        for (var geo : intersections){
            transperent= transperent.product(geo.geometry.getMaterial().kT);
            if (!transperent.lowerThan(MIN_CALC_COLOR_K))
                return false;

        }

    return true ;

    }
    private Color calcColor(GeoPoint geoPoint , Ray ray, int level, Double3 k)
    {
        Color color = calcLocalEffects(geoPoint, ray, k).add(geoPoint.geometry.getEmission());

        return 1 == level ? color : color.add(calcGlobalEffects(geoPoint, ray, level, k));

    }
    private GeoPoint findClosestIntersection(Ray ray){
        List<GeoPoint> intersectionGeoPoint = this.scene.geometries.findGeoIntersections(ray, Double.POSITIVE_INFINITY);
        return ray.findClosestGeoPoint(intersectionGeoPoint);
    }
    private Color calcGlobalEffects(GeoPoint gp, Ray ray, int level, Double3 k) {
        Color color = Color.BLACK;
        Vector n = gp.geometry.getNormal(gp.point);
        Material material = gp.geometry.getMaterial();

        Double3 kkr = k.product(material.kR);
        if (!kkr.lowerThan(MIN_CALC_COLOR_K)) // the color is effected by the reflection
            color = calcGlobalEffects(constructReflectedRay(gp.point, ray, n), level, material.kR, kkr);

        Double3 kkt = k.product(material.kT);
        if (!kkt.lowerThan(MIN_CALC_COLOR_K)) // the color is effected due to the transparency
            color = color.add(calcGlobalEffects(constructReflectedRay(gp.point, ray, n), level, material.kT, kkt));

        return color;
    }
    private Color calcGlobalEffects(Ray ray, int level, Double3 kx, Double3 kkx){
        GeoPoint geoPoint = findClosestIntersection(ray);
        return (geoPoint == null? scene.background : calcColor(geoPoint, ray, level-1, kkx).scale(kx));
    }
    private Ray constructReflectedRay(Point point, Ray ray, Vector n) {
        return new Ray(point, reflectionVector(ray.getDir(), n), n);
    }
    private Vector reflectionVector(Vector l, Vector n) {
        return l.subtract(n.scale(2 * l.dotProduct(n))).normalize();
    }
    // bla
}
