package arkanoid.geometry;

/**
 * Arkanoid.Gui.Point has x axis location and y axis location.
 *
 * @author Yossi Maatook.
 */
public class Point {
    private double x;
    private double y;

    /**
     * Sets the x and y fields of the new point.
     * @param x - the x axis location of the new point.
     * @param y - the y axis location of the new point.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the distance between current point to the received point.
     * @param other - the received point.
     * @return the distance between the points.
     */
    public double distance(Point other) {
        double distanceOfX = this.x - other.getX();
        double distanceOfY = this.y - other.getY();

        //Returns the distance by the equation for distance between two points//
        return Math.sqrt((distanceOfX * distanceOfX) + (distanceOfY * distanceOfY));
    }

    /**
     * Returns true if current point and the received point are the same point. If not, returns false.
     * @param other - the received point to be compared.
     * @return true if both points are the same and false otherwise.
     */
    public boolean equals(Point other) {
        double epsilon = Math.pow(10, -13);
        if (Math.abs(this.x - other.getX()) < epsilon && Math.abs(this.y - other.getY()) < epsilon) {
            return true;
        }
        return false;
    }

    /**
     * Returns the x axis location of the current point.
     * @return he x axis location of the current point.
     */
    public double getX() {
        return this.x;
    }

    /**
     * Returns the y axis location of the current point.
     * @return he y axis location of the current point.
     */
    public double getY() {
        return this.y;
    }
}