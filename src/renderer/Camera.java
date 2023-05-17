package renderer;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.security.PrivateKey;

import static primitives.Util.isZero;

public class Camera {
    private Point p0;
    private Vector vRight;
    private Vector vUp;
    private Vector vTo;
    private double height;
    private double width;
    private double distance;

    public Camera(Point p0,Vector vTo, Vector vUp) {
        if(vUp.dotProduct(vTo)!=0)
            throw new IllegalArgumentException("the vectors are not orthogonal");
        this.p0=p0;
        this.vTo=vTo.normalize();
        this.vUp=vUp.normalize();

        this.vRight =this.vTo.crossProduct(this.vUp);
    }



    public Point getPo() {
        return p0;
    }

    public Vector getvRight() {
        return vRight;
    }

    public Vector getvUp() {
        return vUp;
    }

    public Vector getvTo() {
        return vTo;
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    public double getDistance() {
        return distance;
    }
    public Camera setVPSize(double width, double height) {
        this.width = width;
        this.height=height;
        return this;
    }
    public Camera setVPDistance(double distance){
        this.distance = distance;
        return this;
    }
    public Ray constructRay(int nX, int nY, int j, int i){
        Point Pc = p0.add(vTo.scale(distance));
        Point Pij = Pc;

        //ratios
        double rY = height / nY;
        double rX = width / nX;

        //offsets to reach Pij from
        double iY = -(i - (nY - 1d) / 2) * rY;
        double jX = (j - (nX - 1d) / 2) * rX;

        if (!isZero(jX))
            Pij = Pij.add(vRight.scale(jX));
        if (!isZero(iY))
            Pij = Pij.add(vUp.scale(iY));
        Vector Vij = Pij.subtract(p0);
        return new Ray(p0, Vij);

    }
}
