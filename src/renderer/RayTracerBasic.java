package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;

import java.util.List;

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
        List<Point> pointList = this.scene.geometries.findIntersections(ray);
        if(pointList == null)
        {
            return scene.background;
        }
        Point closestPoint = ray.findClosestPoint(pointList);
        return calcColor(closestPoint);
    }
    private Color calcColor(Point point) {
        return scene.ambientLight.getIntensity();
    }

}
