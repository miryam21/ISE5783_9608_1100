package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.security.PrivateKey;
import java.util.MissingResourceException;

import static primitives.Util.isZero;

public class Camera {
    private Point p0;
    private Vector vRight;
    private Vector vUp;
    private Vector vTo;
    private double height;
    private double width;
    private double distance;
    private ImageWriter imageWriter;
    protected RayTracerBase rayTracerBase;

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

    public Camera setImageWriter(ImageWriter imageWriter) {
        this.imageWriter = imageWriter;
        return this;
    }
    public Camera setRayTracer(RayTracerBase rayTracer) {
        this.rayTracerBase = rayTracer;
        return this;
    }
    public void renderImage() {
        if( width == 0 )
        {  throw new MissingResourceException("ERROR- there are values that not Initialized","Camera" ,  "width");}
        if( height == 0)
        {   throw new MissingResourceException("ERROR- there are values that not Initialized","Camera" ,"height");}
        if(distance == 0)
        {     throw new MissingResourceException("ERROR- there are values that not Initialized","Camera" , "distance");}
        if(imageWriter == null)
        {   throw new MissingResourceException("ERROR- there are values that not Initialized","Camera" , "imageWriter");}
        if(rayTracerBase ==null) {
            throw new MissingResourceException("ERROR- there are values that not Initialized", "Camera", "rayTracerBase");
        }
    }
        public void printGrid(int interval, Color color){
            if(imageWriter == null)
            {   throw new MissingResourceException("ERROR - there are values that not Initialized","Camera" , "imageWriter");}

            // loop for coloring the rows
            for (int i =0; i<imageWriter.getNy(); i+=interval)
            {
                for (int j =0; j<imageWriter.getNx(); j++)
                {
                    imageWriter.writePixel(j, i, color);
                }
            }
            //loop for coloring the columns
            for (int j =0; j<imageWriter.getNx(); j+=interval)
            {
                for (int i =0; i<imageWriter.getNy(); i++)
                {
                    imageWriter.writePixel(j, i, color);
                }
            }
    }
    public void writeToImage()
    {
        //check if null
        if(imageWriter == null)
        {   throw new MissingResourceException("ERROR: one or more values are not Initialized","Camera" , "imageWriter");}

        imageWriter.writeToImage();
    }
}
