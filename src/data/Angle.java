package src.data;

/**
 * Defines an angle represented in both degrees and radians
 * 
 * @since 26 Apr 2023 
 * @author Andrew Matherne (Flambrew)
 */
public class Angle {
    private double degrees, radians;

    public Angle(double d, boolean radians) {
        if (radians) {
            this.radians = d;
            this.degrees = Math.toDegrees(d);
        } else {
            this.degrees = d;
            this.radians = Math.toRadians(d);
        }
    }

    /**
     * Returns the degree value of the angle 
     * 
     * @return degrees
     */
    public double deg() {
        return degrees;
    }

    /**
     * Returns the radian value of the angle
     * 
     * @return radians
     */
    public double rad() {
        return radians;
    }

    /**
     * Sets the value of the angle based off a degree value
     * 
     * @param deg
     */
    public void deg(double deg) {
        this.degrees = deg;
        this.radians = Math.toRadians(deg);
    }

    /**
     * Sets the value of the angle based off a radian value
     * 
     * @param rad
     */
    public void rad(double rad) {
        this.radians = rad;
        this.degrees = Math.toDegrees(rad);
    }
}
