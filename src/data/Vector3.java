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
     * Returns a clone of this vector
     * 
     * @return clone
     */
    public Vector3 clone() {
        return new Vector3(x, y, z);
    }

    /**
     * Adds other to this
     * 
     * @param other
     * @return this
     */
    public Vector3 sum(Vector3 other) {
        x += other.x;
        y += other.y;
        z += other.z;
        return this;
    }

    /**
     * Scales this vector by n
     * 
     * @param n
     * @return this
     */
    public Vector3 scale(double n) {
        x *= n;
        y *= n;
        z *= n;
        return this;
    }

    /**
     * Normalizes this vector
     * 
     * @return this
     */
    public Vector3 normalize() {
        return scale(1 / Math.sqrt(x * x + y * y + z * z));
    }

    /**
     * Returns the dot product of this and other
     * 
     * @param other
     * @return dot rpoduct
     */
    public double dotProduct(Vector3 other) {
        return x * other.x + y * other.y + z * other.z;  
    }

    /**
     * Returns the cross product of this and other as a new vector
     * 
     * @param other
     * @return cross product
     */
    public Vector3 crossProduct(Vector3 other) {
        return new Vector3(y * other.z - z * other.y, z * other.x - x * other.z, x * other.y - y * other.x);
    }

    /**
     * Transforms this vector by a transformation matrix
     * 
     * @param matrix
     * @return this
     */
    public Vector3 transform(Matrix4 matrix) {
        double w = x * matrix.a.d + y * matrix.b.d + z * matrix.c.d + matrix.d.d;

        double X = (x * matrix.a.a + y * matrix.b.a + z * matrix.c.a + matrix.d.a) / (w != 0 ? w : 1);
        double Y = (x * matrix.a.b + y * matrix.b.b + z * matrix.c.b + matrix.d.b) / (w != 0 ? w : 1);
        double Z = (x * matrix.a.c + y * matrix.b.c + z * matrix.c.c + matrix.d.c) / (w != 0 ? w : 1);

        x = X;
        y = Y;
        z = Z;

        return this;
    }

    public String toString() {
        return String.format("(%.3f, %.3f, %.3f)", x, y, z);
    }
}