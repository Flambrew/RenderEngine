package src.data;

/**
 * Represents a point or vector in 3D space.
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
     * Returns a new Vector3 sum of <code>this</code> and <code>other</code>
     * 
     * @param other
     * @return new Vector3
     */
    public Vector3 sum(Vector3 other) {
        return new Vector3(x + other.x, y + other.y, z + other.z);
    }

    /**
     * Returns <code>this</code> translated by <code>other</code>
     * 
     * @param other
     * @return <code>this</code> Vector3
     */
    public Vector3 translate(Vector3 other) {
        x += other.x;
        y += other.y;
        z += other.z;
        return this;
    }

    public Vector3 scale(double n) {
        return new Vector3(x * n, y * n, z * n);
    }
}