package arkanoid.geometry;

/**
 * Arkanoid.Gui.Velocity specifies the change in position on the `x` and the `y` axes.
 * @author Yossi Maatook
 */
public class Velocity {
    public static final int ANGLE_SHIFT = 90;
    private double dx;
    private double dy;

    /**
     * This method sets the dx and dy fields of the object according to
     * the dx and dy that has been received.
     *
     * @param dx - change in the x position
     * @param dy - change in the y position
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * This method sets the dx and dy fields of the Arkanoid.Gui.Velocity object and returns it.
     *
     * @param angle - the angle(direction) change in the x and y positions.
     * @param speed - the speed rate of the change
     * @return a new Arkanoid.Gui.Velocity object.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        angle = angle - ANGLE_SHIFT;

        //Sets the change in the x position by the received angle and speed//
        double dy = Math.sin(Math.toRadians(angle)) * speed;

        //Sets the change in the y position by the received angle and speed//
        double dx = Math.cos(Math.toRadians(angle)) * speed;
        return new Velocity(dx, dy);
    }

    /**
     * The method returns the value of the dx field in the object.
     *
     * @return this.dx - the value of the dx field in the object
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * The method returns the value of the dy field in the object.
     *
     * @return this.dy - the value of the dx field in the object
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * The method receives a point and apply the change in it's
     * x and y location by it's velocity.
     * @param p - a point with position (x,y)
     * @return moved - a point with position (x+dx,y+dy).
     */
    public Point applyToPoint(Point p) {
        double newX = p.getX() + this.dx;
        double newY = p.getY() + this.dy;
        Point moved = new Point(newX, newY);
        return moved;
    }

    /**
     * Return the speed of current velocity.
     * @return speed of current velocity.
     */
    public double getSpeed() {
        return Math.sqrt(Math.pow(this.dx, 2) + Math.pow(this.dy, 2));
    }
}