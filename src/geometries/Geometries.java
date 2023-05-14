package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Geometries implements Intersectable {

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
    public List<Point> findIntersections(Ray ray) {
        List<Point> listOfAllThePoint = new ArrayList<>();

        for (Intersectable geometry : list) {
            List<Point> pointList = geometry.findIntersections(ray);
            if (pointList == null) continue;
            for (Point point : pointList) {
                listOfAllThePoint.add(point);
            }
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
