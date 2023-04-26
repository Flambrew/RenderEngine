package src.data;

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

    public double deg() {
        return degrees;
    }

    public double rad() {
        return radians;
    }

    public void deg(double deg) {
        this.degrees = deg;
        this.radians = Math.toRadians(deg);
    }

    public void rad(double rad) {
        this.radians = rad;
        this.degrees = Math.toDegrees(rad);
    }
}
