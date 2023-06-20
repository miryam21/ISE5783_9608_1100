package primitives;

public class Vector extends Point { // A class representing a 3D vector, which inherits from Point class
    public Vector(double x, double y, double z) { // Constructor with three double parameters for the vector's components
        super(x, y, z); // Call the constructor of Point with the same parameters
        if (xyz.equals((Double3.ZERO))) { // Check if the vector is a zero vector
            throw new IllegalArgumentException("vector can't be zero"); // Throw an exception if it's a zero vector
        }
    }

    Vector(Double3 double3) { // Constructor with a Double3 parameter for the vector's components
        this(double3.d1, double3.d2, double3.d3); // Call the first constructor with the Double3 components
        if (double3.equals(Double3.ZERO)) { // Check if the vector is a zero vector
            throw new IllegalArgumentException("vector can't be zero"); // Throw an exception if it's a zero vector
        }

    }

    /**
     * public  double lengthSquared(){
     * double dx= xyz.d1;
     * double dy= xyz.d2;
     * double dz= xyz.d3;
     * return dx*dx + dy*dy + dz*dz;
     * }
     */

    public double length() { // Method to calculate the length of the vector
        return Math.sqrt((lengthSquared())); // Return the square root of the length squared
    }

    @Override
    public boolean equals(Object o) { // Override the equals method to check if two vectors are equal
        if (this == o) return true; // If the two objects are the same object, they are equal
        if (!(o instanceof Vector vector))
            return false; // If the object is not an instance of Vector, they are not equal
        return xyz.equals(vector.xyz); // Check if the Double3 components of the vectors are equal
    }

    public Vector normalize() { // Method to normalize the vector
        double len = length(); // Get the length of the vector
        return new Vector(xyz.reduce(len)); // Return a new vector with the components reduced by the length
    }

    public Vector scale(double num) { // Method to scale the vector by a given factor
        return new Vector(xyz.scale(num)); // Return a new vector with the components scaled by the factor
    }

    public double dotProduct(Vector v1) { // Method to calculate the dot product between two vectors
        return (this.xyz.d1 * v1.xyz.d1 + // Multiply the components of the two vectors and sum them
                this.xyz.d2 * v1.xyz.d2 +
                this.xyz.d3 * v1.xyz.d3);
    }

    public Vector add(Vector vector) { // Method to add two vectors
        return new Vector(xyz.add(vector.xyz)); // Return a new vector with the components added
    }

    @Override
    public String toString() { // Override the toString method to return a string representation of the vector
        return "vector:" + xyz; // Return a string with the word "vector" and the Double3 components of the vector
    }

    public Vector crossProduct(Vector v) { // Method to calculate the cross product between two vectors
        return new Vector(this.xyz.d2 * v.xyz.d3 - this.xyz.d3 * v.xyz.d2, // Calculate the components of the resulting vector
                this.xyz.d3 * v.xyz.d1 - this.xyz.d1 * v.xyz.d3,
                this.xyz.d1 * v.xyz.d2 - this.xyz.d2 * v.xyz.d1);
    }

    public double lengthSquared() {
        double dx = xyz.d1;
        double dy = xyz.d2;
        double dz = xyz.d3;
        return dx * dx + dy * dy + dz * dz;
    }


}
