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

    /**
     * Constructs a camera with the specified location, view direction, and up direction.
     *
     * @param p0  The camera's location
     * @param vTo The vector representing the view direction
     * @param vUp The vector representing the up direction
     * @throws IllegalArgumentException if the view and up vectors are not orthogonal
     */
    public Camera(Point p0, Vector vTo, Vector vUp) {
        if (vUp.dotProduct(vTo) != 0)
            throw new IllegalArgumentException("The vectors are not orthogonal");

        this.p0 = p0;
        this.vTo = vTo.normalize();
        this.vUp = vUp.normalize();
        this.vRight = this.vTo.crossProduct(this.vUp);
    }

    /**
     * Returns the camera's location.
     *
     * @return The camera's location
     */
    public Point getPo() {
        return p0;
    }

    /**
     * Returns the vector representing the right direction.
     *
     * @return The vector representing the right direction
     */
    public Vector getvRight() {
        return vRight;
    }

    /**
     * Returns the vector representing the up direction.
     *
     * @return The vector representing the up direction
     */
    public Vector getvUp() {
        return vUp;
    }

    /**
     * Returns the vector representing the view direction.
     *
     * @return The vector representing the view direction
     */
    public Vector getvTo() {
        return vTo;
    }

    /**
     * Returns the height of the view plane.
     *
     * @return The height of the view plane
     */
    public double getHeight() {
        return height;
    }

    /**
     * Returns the width of the view plane.
     *
     * @return The width of the view plane
     */
    public double getWidth() {
        return width;
    }

    /**
     * Returns the distance between the camera and the view plane.
     *
     * @return The distance between the camera and the view plane
     */
    public double getDistance() {
        return distance;
    }

    /**
     * Sets the size of the view plane.
     *
     * @param width  The width of the view plane
     * @param height The height of the view plane
     * @return The camera object
     */
    public Camera setVPSize(double width, double height) {
        this.width = width;
        this.height = height;
        return this;
    }

    /**
     * Sets the distance between the camera and the view plane.
     *
     * @param distance The distance between the camera and the view plane
     * @return The camera object
     */
    public Camera setVPDistance(double distance) {
        this.distance = distance;
        return this;
    }

    /**
     * Constructs a ray for the specified pixel coordinates on the view plane.
     *
     * @param nX The number of pixels in the x-direction
     * @param nY The number of pixels in the y-direction
     * @param j  The x-coordinate of the pixel
     * @param i  The y-coordinate of the pixel
     * @return The constructed ray
     */
    public Ray constructRay(int nX, int nY, int j, int i) {
        Point Pc = p0.add(vTo.scale(distance));
        Point Pij = Pc;

        // Ratios
        double rY = height / nY;
        double rX = width / nX;

        // Offsets to reach Pij from
        double iY = -(i - (nY - 1d) / 2) * rY;
        double jX = (j - (nX - 1d) / 2) * rX;

        if (!isZero(jX))
            Pij = Pij.add(vRight.scale(jX));
        if (!isZero(iY))
            Pij = Pij.add(vUp.scale(iY));
        Vector Vij = Pij.subtract(p0);
        return new Ray(p0, Vij);
    }

    /**
     * Sets the image writer for the camera.
     *
     * @param imageWriter The image writer object
     * @return The camera object
     */
    public Camera setImageWriter(ImageWriter imageWriter) {
        this.imageWriter = imageWriter;
        return this;
    }
    private Color castRay(int j,int i){
        Ray ray = constructRay(
                this.imageWriter.getNx(),
                this.imageWriter.getNy(),
                j,
                i);
        return this.rayTracerBase.traceRay(ray);
}
    /**
     * Sets the ray tracer for the camera.
     *
     * @param rayTracer The ray tracer object
     * @return The camera object
     */
    public Camera setRayTracer(RayTracerBase rayTracer) {
        this.rayTracerBase = rayTracer;
        return this;
    }

    /**
     * Renders the image using the camera's settings.
     *
     * @throws MissingResourceException if any required values are not initialized
     */
    public void renderImage() {
        if (width == 0) {
            throw new MissingResourceException("ERROR: Some values are not initialized", "Camera", "width");
        }
        if (height == 0) {
            throw new MissingResourceException("ERROR: Some values are not initialized", "Camera", "height");
        }
        if (distance == 0) {
            throw new MissingResourceException("ERROR: Some values are not initialized", "Camera", "distance");
        }
        if (imageWriter == null) {
            throw new MissingResourceException("ERROR: Some values are not initialized", "Camera", "imageWriter");
        }
        if (rayTracerBase == null) {
            throw new MissingResourceException("ERROR: Some values are not initialized", "Camera", "rayTracerBase");
        }
        for (int i = 0; i < this.imageWriter.getNy(); i++) {
            for (int j = 0; j < this.imageWriter.getNy(); j++) {
                Color color = castRay(j, i);
                this.imageWriter.writePixel(j, i, color);
            }
        }
    }

    /**
     * Prints a grid on the image with the specified interval and color.
     *
     * @param interval The interval between grid lines
     * @param color    The color of the grid lines
     * @throws MissingResourceException if the image writer is not initialized
     */
    public void printGrid(int interval, Color color) {
        if (imageWriter == null) {
            throw new MissingResourceException("ERROR: Some values are not initialized", "Camera", "imageWriter");
        }

        // Loop for coloring the rows
        for (int i = 0; i < imageWriter.getNy(); i += interval) {
            for (int j = 0; j < imageWriter.getNx(); j++) {
                imageWriter.writePixel(j, i, color);
            }
        }

        // Loop for coloring the columns
        for (int j = 0; j < imageWriter.getNx(); j += interval) {
            for (int i = 0; i < imageWriter.getNy(); i++) {
                imageWriter.writePixel(j, i, color);
            }
        }
    }

    /**
     * Writes the image to the image file using the image writer.
     *
     * @throws MissingResourceException if the image writer is not initialized
     */
    public void writeToImage() {
        if (imageWriter == null) {
            throw new MissingResourceException("ERROR: Some values are not initialized", "Camera", "imageWriter");
        }

        imageWriter.writeToImage();
    }
}

