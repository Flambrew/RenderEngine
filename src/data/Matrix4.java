package src.data;

import java.awt.Dimension;

/**
 * Represents a 4x4 matrix
 */
public class Matrix4 {
    Vector4 a, b, c, d;

    public Matrix4(double[][] matrix) {
        if (matrix.length == 4) {
            this.a = new Vector4(matrix[0]);
            this.b = new Vector4(matrix[1]);
            this.c = new Vector4(matrix[2]);
            this.d = new Vector4(matrix[3]);
        }
    }

    private class Vector4 {
        public double a, b, c, d;

        public Vector4(double[] vector) {
            if (vector.length == 4) {
                this.a = vector[0];
                this.b = vector[1];
                this.c = vector[2];
                this.d = vector[3];
            }
        }
    }

    public static Matrix4 PROJECTION_MATRIX(final Dimension windowSize, final Angle fov, final double maxDistViewable, final double minDistViewable) {
        return new Matrix4(new double[][] { { windowSize.height / (windowSize.width * Math.tan(fov.rad() / 2)), 0, 0, 0 },
                { 0, 1 / Math.tan(fov.rad() / 2), 0, 0 }, { 0, 0, maxDistViewable / (maxDistViewable - minDistViewable), 1 },
                { 0, 0, -maxDistViewable * minDistViewable / (maxDistViewable - minDistViewable), 0 } });
    }
}
