package src.data;

import java.awt.Dimension;

/** Represents a 4x4 matrix
 * @since 26 Apr 2023
 * @author Andrew Matherne (Flambrew) */
public class Matrix4 {
    public Vector4 a, b, c, d;

    public Matrix4(double[][] matrix) {
        if (matrix.length == 4) {
            this.a = new Vector4(matrix[0]);
            this.b = new Vector4(matrix[1]);
            this.c = new Vector4(matrix[2]);
            this.d = new Vector4(matrix[3]);
        }
    }

    public class Vector4 {
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

    /** Creates a transformation matrix for translating 3D space into a perspective view
     * @param windowSize
     * @param fov
     * @param maxDistViewable
     * @param minDistViewable
     * @return projection matrix */
    public static Matrix4 PROJECTION_MATRIX(final Dimension windowSize, final Angle fov, final double maxDistViewable, final double minDistViewable) {
        return new Matrix4(new double[][] { //
                { windowSize.height / (windowSize.width * Math.tan(fov.rad() / 2)), 0, 0, 0 }, //
                { 0, 1 / Math.tan(fov.rad() / 2), 0, 0 }, //
                { 0, 0, maxDistViewable / (maxDistViewable - minDistViewable), 1 }, //
                { 0, 0, -maxDistViewable * minDistViewable / (maxDistViewable - minDistViewable), 0 } //
        });
    }

    /** Creates a transformation matrix for rotating a point around {0, 0, 0} about the z axis
     * @param angle
     * @return rotation matrix */
    public static Matrix4 Z_ROTATE(final double angle) {
        return new Matrix4(new double[][] { //
                { Math.cos(angle * .5), -Math.sin(angle * .5), 0, 0 }, //
                { Math.sin(angle * .5), Math.cos(angle * .5), 0, 0 }, //
                { 0, 0, 1, 0 }, //
                { 0, 0, 0, 1 } //
        });
    }

    /** Creates a transformation matrix for rotating a point around {0, 0, 0} about the x axis
     * @param angle
     * @return rotation matrix */
    public static Matrix4 X_ROTATE(final double angle) {
        return new Matrix4(new double[][] { //
                { 1, 0, 0, 0 }, //
                { 0, Math.cos(angle), -Math.sin(angle), 0 }, //
                { 0, Math.sin(angle), Math.cos(angle), 0 }, //
                { 0, 0, 0, 1 } //
        });
    }
}
