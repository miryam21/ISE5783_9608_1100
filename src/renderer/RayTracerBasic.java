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
public class RayTracerBasic extends RayTracerBase{
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
        List<GeoPoint> pointList = scene.geometries.findGeoIntersections(ray);
        if(pointList == null) {
            return scene.background;
        }
        GeoPoint closestPoint = ray.findClosestGeoPoint(pointList);
        return calcColor(closestPoint);
    }
    private Color calcColor(GeoPoint geoPoint, Ray ray) {
        return scene.ambientLight.getIntensity().add(geoPoint.geometry.getEmission())
                .add(calcLocalEffects(geoPoint, ray));
    }
    private Color calcLocalEffects(GeoPoint geoPoint, Ray ray){
        Vector v= ray.getDir();
        Vector n= geoPoint.geometry.getNormal(geoPoint.point);
        double nv= alignZero(n.dotProduct(v));
        if(nv==0)
            return Color.BLACK;
        Material material= geoPoint.geometry.getMaterial();
        for(LightSource lightSource: scene.lights)


    }
}
