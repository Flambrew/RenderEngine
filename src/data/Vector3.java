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
    
    public Vector3 sum(Vector3 other) {
        return new Vector3(x + other.x, y + other.y, z + other.z);
    }

    public Vector3 scale(double n) {
        return new Vector3(x * n, y * n, z * n);
    }

    public Vector3 applyMatrix(Matrix4 matrix) {
        double W = x * matrix.a.d + y * matrix.b.d + z * matrix.c.d + matrix.d.d;

        double X = (x * matrix.a.a + y * matrix.b.a + z * matrix.c.a + matrix.d.a) / (W != 0 ? W : 1);
        double Y = (x * matrix.a.b + y * matrix.b.b + z * matrix.c.b + matrix.d.b) / (W != 0 ? W : 1);
        double Z = (x * matrix.a.c + y * matrix.b.c + z * matrix.c.c + matrix.d.c) / (W != 0 ? W : 1);

        return new Vector3(X, Y, Z);
    }
}