package primitives;

public class Vector extends Point{
    public Vector(double x, double y , double z) {
        super(x,y,z);
        if(xyz.equals((Double3.ZERO))){
            throw new IllegalArgumentException("vector can't be zero");
        }
    }
    Vector(Double3 double3) {

        this(double3.d1,double3.d2, double3.d3);
        if(double3.equals(Double3.ZERO)){
            throw new IllegalArgumentException("vector can't be zero");
        }

    }
    /**public  double lengthSquared(){
        double dx= xyz.d1;
        double dy= xyz.d2;
        double dz= xyz.d3;
        return dx*dx + dy*dy + dz*dz;
    }
     */
    public double length() {
        return Math.sqrt((lengthSquared())) ;
    }


    @Override
    public boolean equals(Object o){
     if (this==o) return true;
     if (!(o instanceof Vector vector)) return false;
     return  xyz.equals(vector.xyz);
    }

    public Vector normalize() {
        double len= length();
        return new Vector(xyz.reduce(len));
    }
    public Vector scale( double num ) {
        return new Vector(xyz.scale(num));
    }

    public double dotProduct(Vector v1) {
       return ( this.xyz.d1*v1.xyz.d1+
               this.xyz.d2*v1.xyz.d2+
               this.xyz.d3*v1.xyz.d3);
    }
public Vector add(Vector vector){
        return new Vector(xyz.add(vector.xyz));
}

    @Override
    public String toString() {
        return "vector:" + xyz ;
    }

    public Vector crossProduct(Vector v) {
        return new Vector(this.xyz.d2*v.xyz.d3-this.xyz.d3*v.xyz.d2,
                this.xyz.d3*v.xyz.d1-this.xyz.d1*v.xyz.d3,
                this.xyz.d1*v.xyz.d2-this.xyz.d2*v.xyz.d1);
    }

    public double lengthSquared()
    {
       return (this.dotProduct(this));
    }
        public double length(Vector other){
        return  Math.sqrt(this.lengthSquared());
    }
}
