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
 * abstract class RayTracerBasic
 */
public class    RayTracerBasic extends RayTracerBase{
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
        int i;
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(ray);
        return intersections == null ? scene.background : calcColor(ray.findClosestGeoPoint(intersections), ray);
    }
    private Color calcColor(GeoPoint geoPoint, Ray ray) {
        return scene.ambientLight.getIntensity().add(geoPoint.geometry.getEmission())
                .add(calcLocalEffects(geoPoint, ray));
    }
    private Color calcLocalEffects(GeoPoint geoPoint, Ray ray){
        Vector v= ray.getDir();
        Vector n= geoPoint.geometry.getNormal(geoPoint.point);
        double nv= alignZero(n.dotProduct(v));
        Color color= Color.BLACK;
        if(nv==0)
            return Color.BLACK;
        Material material= geoPoint.geometry.getMaterial();
        for(LightSource lightSource: scene.lights){
            Vector lightVector = lightSource.getL(geoPoint.point);
            double nl = alignZero(n.dotProduct(lightVector));
            if (nl * nv > 0) {
                Color lightIntensity = lightSource.getIntensity(geoPoint.point);
                color = color.add(lightIntensity.scale(calcDiffusive(material, nl)), lightIntensity.scale(calcSpecular(material, n, lightVector, nl, v)));
            }
        }
        return color;
    }
    private Double3 calcDiffusive(Material material, double nl) {
        return material.kD.scale(Math.abs(nl));
    }
    private Double3 calcSpecular(Material material, Vector normal, Vector lightVector, double nl, Vector vector) {
        Vector reflectedVector = lightVector.subtract(normal.scale(2 * nl));
        double max = Math.max(0, vector.scale(-1).dotProduct(reflectedVector));
        return material.kS.scale(Math.pow(max, material.nShininess));

    }

}
