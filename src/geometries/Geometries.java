package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Geometries extends Intersectable {

     private List<Intersectable> list;

    public Geometries() {
        list = new LinkedList<>();
    }

    public Geometries(Intersectable... geometries){
        list = new LinkedList<>();
        for (Intersectable geometry : geometries) {
            list.add(geometry);
        }
    }
    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        List<GeoPoint> listOfAllThePoint = new ArrayList<>();

        for (Intersectable geometry : list ) {
            List<GeoPoint> pointList = geometry.findGeoIntersectionsHelper(ray, maxDistance);
            if (pointList == null) continue;
            listOfAllThePoint.addAll(pointList);
        }

        if (listOfAllThePoint.size() == 0)
            return null;
        return listOfAllThePoint;
    }
    public void add(Intersectable... geometries){
        for (Intersectable geometry : geometries) {
            list.add(geometry);
        }
    }
}
