package primitives;

public class Ray {
    final Point p0;
    final Vector dir;
    public Point getP0() {
        return p0;
    }

    public Vector getDir() {
        return dir;
    }

    public Ray(Point p0, Vector dir) {
        this.p0 = p0;
        this.dir = dir.normalize();
    }
     @Override
     public boolean equals(Object o) {
         if (this == o) return true;
         return (o instanceof Ray ray)
                 && this.dir.equals(ray.dir)
                 && this.p0.equals(ray.p0);
     }
    @Override
    public String toString() {
        return "point of ray: " + p0 + "vector of ray: " + dir ;

    }
}
