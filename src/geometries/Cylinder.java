package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

import static primitives.Util.alignZero;

/**
 *A class that implements a cylinder, it is a finite pipe and therefore has a height.
 * The class inherits from Tube.
 */
public class Cylinder extends Tube {

    /**
     * height of the cylinder
     */
    final private double height;


    /**
     * ctr for Cylinder
     * @param radius
     * @param axisRay
     * @param height
     */
    public Cylinder(double radius, Ray axisRay, double height) {
        super( axisRay,radius);
        this.height = height;
    }

    /**
     * get the height of the Cylinder
     * @return
     */
    public double getHeight() {
        return height;
    }


    // region getNormal
    @Override
    public Vector getNormal(Point point) {
        Vector v = axisRay.getDir();
        Point p0 = axisRay.getP0();
        Point upperPoint = p0.add(v.scale(height));

        if (point.equals(p0) || point.equals(upperPoint) || v.dotProduct(point.subtract(p0)) == 0 || v.dotProduct(point.subtract(upperPoint)) == 0)
            return axisRay.getDir();
        return super.getNormal(point);
    }

    //endregion

    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray,double maxDistance) {
        List<GeoPoint> intersections = super.findGeoIntersectionsHelper(ray,maxDistance);

        List<GeoPoint> pointList = new ArrayList<>();

        if(intersections != null) {
            for (GeoPoint geoPoint : intersections) {
                double projection = geoPoint.point.subtract(axisRay.getP0()).dotProduct(axisRay.getDir());
                if (alignZero(projection) > 0 && alignZero(projection - this.height) < 0)
                    pointList.add(new GeoPoint(this,geoPoint.point));
            }
        }

        // intersect with base
        Circle base = new Circle(axisRay.getP0(), radius, axisRay.getDir());
        intersections = base.findGeoIntersectionsHelper(ray,maxDistance);
        if(intersections != null)
            pointList.add(new GeoPoint(this,intersections.get(0).point));

        base = new Circle(axisRay.getPoint(height), radius, axisRay.getDir());
        intersections = base.findGeoIntersectionsHelper(ray,maxDistance);
        if(intersections != null)
            pointList.add(new GeoPoint(this, intersections.get(0).point));

        if (pointList.size() == 0)
            return null;
        return pointList;
    }

    @Override
    public String toString() {
        return "height=" + height +
                ", axisRay=" + axisRay +
                ", radius=" + radius ;
    }
}
