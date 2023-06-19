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
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(ray);
        return intersections == null ? scene.background : calcColor(ray.findClosestGeoPoint(intersections), ray);
    }

    /**
     * Calculates the color of the intersected object at the given intersection point.
     *
     * @param geoPoint The intersection point on the geometry
     * @param ray      The ray that intersected the object
     * @return The color of the intersected object
     */
    private Color calcColor(GeoPoint geoPoint, Ray ray) {
        return scene.ambientLight.getIntensity().add(geoPoint.geometry.getEmission())
                .add(calcLocalEffects(geoPoint, ray));
    }

    /**
     * Calculates the local effects (diffuse and specular) of light on the intersected object.
     *
     * @param geoPoint The intersection point on the geometry
     * @param ray      The ray that intersected the object
     * @return The color of the local effects
     */
    private Color calcLocalEffects(GeoPoint geoPoint, Ray ray) {
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
                if (unshaded(geoPoint, lightSource, lightVector, n, nl)) {
                    Color lightIntensity = lightSource.getIntensity(geoPoint.point);
                    color = color.add(lightIntensity.scale(calcDiffuse(material, nl)),
                            lightIntensity.scale(calcSpecular(material, n, lightVector, nl, v)));
                }
            }
        }

        return color;
    }

    /**
     * Calculates the diffuse component of light reflection on the surface of the object.
     *
     * @param material The material of the object
     * @param nl       The dot product between the normal and light vectors
     * @return The diffuse component
     */
    private Double3 calcDiffuse(Material material, double nl) {
        return material.kD.scale(Math.abs(nl));
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

    private boolean unshaded(GeoPoint gp,LightSource light, Vector l, Vector n,double nl) {
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

    return true ;

    }

    private Double3 transparency(GeoPoint geopoint,  Vector n, LightSource lightSource){
        Vector lightDirection = lightSource.getL(geopoint.point).scale(-1);
        Ray lightRay = new Ray(geopoint.point, lightDirection, n);

        List<GeoPoint> intersections = scene.geometries.
                findGeoIntersections(lightRay,
                        lightSource.getDistance(geopoint.point));
        if(intersections == null) return Double3.ONE;

        Double3 transparent = Double3.ONE;
        for (GeoPoint gp : intersections) {
            transparent = transparent.product(gp.geometry.getMaterial().kT);
            if(transparent.subtract(new Double3(MIN_CALC_COLOR_K)).lowerThan(0))
                return Double3.ZERO;
        }
        return transparent;
    }
}
