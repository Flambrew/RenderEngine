package src.data;

/**
 * Represents a point or vector in 3D space
 * 
 * @since 26 Apr 2023
 * @author Andrew Matherne (Flambrew) 
 */
public class Vector3 {
    public double x, y, z;

    public Vector3() {
        this(0, 0);
    }

    public Vector3(double x, double y) {
        this(x, y, 0);
    }

    public Vector3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Returns a new vector of the sum of this and another vector
     * 
     * @param other
     * @return sum
     */
    public Vector3 sum(Vector3 other) {
        return new Vector3(x + other.x, y + other.y, z + other.z);
    }

    /**
     * Returns a new vector of this vector scaled by an scaling factor
     * 
     * @param n
     * @return scale
     */
    public Vector3 scale(double n) {
        return new Vector3(x * n, y * n, z * n);
    }

    /**
     * Returns a new vector of the normalization of this vector
     * 
     * @return normalization
     */
    public Vector3 normalize() {
        return scale(1 / Math.sqrt(x * x + y * y + z * z));
    }

    /**
     * Returns the dot product of this and another vector
     * 
     * @param other
     * @return dot rpoduct
     */
    public double dotProduct(Vector3 other) {
        return x * other.x + y * other.y + z * other.z;  
    }

    /**
     * Returns a new vector of the cross product of this and another vector
     * 
     * @param other
     * @return cross product
     */
    public Vector3 crossProduct(Vector3 other) {
        return new Vector3(y * other.z - z * other.y, z * other.x - x * other.z, x * other.y - y * other.x);
    }

    /**
     * Returns a new vector of this vector after applying a transformation matrix
     * 
     * @param matrix
     * @return translated vector
     */
    public Vector3 applyMatrix(Matrix4 matrix) {
        double W = x * matrix.a.d + y * matrix.b.d + z * matrix.c.d + matrix.d.d;

        double X = (x * matrix.a.a + y * matrix.b.a + z * matrix.c.a + matrix.d.a) / (W != 0 ? W : 1);
        double Y = (x * matrix.a.b + y * matrix.b.b + z * matrix.c.b + matrix.d.b) / (W != 0 ? W : 1);
        double Z = (x * matrix.a.c + y * matrix.b.c + z * matrix.c.c + matrix.d.c) / (W != 0 ? W : 1);

        return new Vector3(X, Y, Z);
    }

    public String toString() {
        return String.format("(%.3f, %.3f, %.3f)", x, y, z);
    }
}