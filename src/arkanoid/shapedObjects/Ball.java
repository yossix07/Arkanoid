package arkanoid.shapedObjects;
import arkanoid.game.levels.GameLevel;
import arkanoid.geometry.Line;
import arkanoid.geometry.Point;
import arkanoid.geometry.Velocity;
import arkanoid.sprites.Sprite;
import arkanoid.collidables.CollisionInfo;
import arkanoid.collidables.GameEnvironment;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * Shapes.Ball has a center point, color, radius, velocity and a gameEnvironment.
 *
 * @author Yossi Maatook.
 */
public class Ball implements Sprite {

    //When dx or dy equals to 0, the ball isn't moving//
    public static final int NOT_MOVING = 0;
    private Point center;
    private java.awt.Color color;
    private int radius;
    private Velocity velocity;
    private GameEnvironment gameEnvironment;

    /**
     * Sets the new ball fields by the received data - center, radius, color and Arkanoid.Collidables.GameEnvironment.
     *
     * @param center - center point of the ball.
     * @param radius - radius of the ball.
     * @param color - color of the ball.
     * @param gameEnvironment - the game environment which the ball is in.
     */
    public Ball(Point center, int radius, java.awt.Color color, GameEnvironment gameEnvironment) {
        this.center = center;
        this.radius = radius;
        this.color = color;
        this.velocity = null;
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * Sets the new ball fields by the received data - x, y, radius, color and Arkanoid.Collidables.GameEnvironment.
     *
     * @param x - x position of center point of the ball.
     * @param y - y position of center point of the ball.
     * @param radius - radius of the ball.
     * @param color - color of the ball.
     * @param gameEnvironment - the game environment which the ball is in.
     */
    public Ball(double x, double y, int radius, java.awt.Color color, GameEnvironment gameEnvironment) {
        this(new Point(x, y), radius, color, gameEnvironment);

    }

    /**
     * Sets the new ball fields by the received data - x, y, radius and color.
     * Arkanoid.Collidables.GameEnvironment is set to be a new one.
     * @param x - x position of center point of the ball.
     * @param y - y position of center point of the ball.
     * @param radius - radius of the ball.
     * @param color - color of the ball.
     */
    public Ball(double x, double y, int radius, java.awt.Color color) {
        this(new Point(x, y), radius, color, new GameEnvironment());
    }

    /**
     * Sets the new ball fields by the received data - x, y, radius and color.
     * Arkanoid.Collidables.GameEnvironment is set to be a new one.
     * @param center - center point of the ball.
     * @param radius - radius of the ball.
     * @param color - color of the ball.
     */
    public Ball(Point center, int radius, java.awt.Color color) {
        this(center, radius, color, new GameEnvironment());
    }

    /**
     * Constructor.
     * Creates a copy of the received ball.
     * @param ball - the ball which will be copied.
     */
    public Ball(Ball ball) {
        this(ball.getCenter(), ball.getSize(), ball.getColor(), ball.getGameEnvironment());
    }

    /**
     * The method returns the x position of the center of the ball.
     * @return this.center.getX - the x position of the center of the ball.
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * The method returns the y position of the center of the ball.
     * @return this.center.getY - the y position of the center of the ball.
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * Return the ball's center point.
     * @return the center of the ball.
     */
    public Point getCenter() {
        return this.center;
    }

    /**
     * The method returns the radius size of the ball.
     * @return this.radius - the radius size of the ball.
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * The method returns the radius size of the ball.
     * @return this.radius - the radius size of the ball.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * Draws the ball on the given DrawSurface.
     * @param surface - The surface on which the ball will be drawn.
     */
    public void drawOn(DrawSurface surface) {
        //Set the color to black//
        surface.setColor(Color.black);

        //Draw the ball's circle//
        surface.drawCircle((int) this.center.getX(), (int) this.center.getY(), this.radius);

        //Set the color to the current ball color//
        surface.setColor(this.color);

        //Fill the ball's color//
        surface.fillCircle((int) this.center.getX(), (int) this.center.getY(), this.radius);
    }

    /**
     * This method adds current ball to the received game.
     * @param g - the game which we want to add the ball to.
     */
    public void addToGame(GameLevel g) {
        g.getSprites().addSprite(this);
        g.addToBallsList(this);
    }

    /**
     * This method removes current ball from the received game.
     * @param g - the game which we want to remove the ball from.
     */
    public void removeFromGame(GameLevel g) {
        g.getSprites().removeSprite(this);
    }

    @Override
    public void timePassed() {
        //Can't move the ball without setting velocity first//
        if (this.velocity == null) {
            throw new NullPointerException("Must Set Arkanoid.Gui.Velocity First!");
        }
        InsideObjects insideObjects = new InsideObjects();
        Point previousCenter = this.center;
        Line trajectory = new Line(this.center.getX(), this.center.getY(),
                this.center.getX() + this.velocity.getDx(), this.center.getY() + this.velocity.getDy());
        CollisionInfo info = this.gameEnvironment.getClosestCollision(trajectory);

        //In case the ball next movement isn't clear, change it's location to the collision point and it's velocity//
        if (info != null) {
            this.center = closeToCollision(trajectory, info.getCollisionPoint());
            this.setVelocity(info.getCollisionObject().hit(this, info.getCollisionPoint(), this.velocity));
        } else {
            this.center = this.getVelocity().applyToPoint(this.center);
        }
    }


    /**
     * Returns a close point to the received collision point, according to the ball velocity.
     * @param trajectory - the movement line of the ball.
     * @param collision - the collision point of the ball with another object.
     * @return a point close to the collision, according to the ball movement line and velocity.
     */
    private Point closeToCollision(Line trajectory, Point collision) {
        double x = collision.getX();
        double y = collision.getY();

        //In case dx is positive, decrease the x axis.
        if (this.velocity.getDx() > NOT_MOVING && !trajectory.getIsVertical()) {
            x = collision.getX() - this.radius;
        }

        //In case dx is negative, increase the x axis.
        if (this.velocity.getDx() < NOT_MOVING && !trajectory.getIsVertical()) {
            x = collision.getX() + this.radius;
        }

        //In case dy is positive, decrease the y axis.
        if (this.velocity.getDy() > NOT_MOVING && !trajectory.isHorizontal()) {
            y = collision.getY() - this.radius;
        }

        //In case dy is negative, increase the y axis.
        if (this.velocity.getDy() < NOT_MOVING && !trajectory.isHorizontal()) {
            y = collision.getY() + this.radius;
        }
        return new Point(x, y);
    }

    /**
     * Returns the Arkanoid.Collidables.GameEnvironment of the ball.
     * @return the Arkanoid.Collidables.GameEnvironment of the ball.
     */
    public GameEnvironment getGameEnvironment() {
        return this.gameEnvironment;
    }

    /**
     * Sets the received velocity as the velocity of the current ball.
     *
     * @param v - the wanted velocity to be set.
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * Sets the the received dx and dy as the velocity of the current ball.
     *
     * @param dx - the x position change.
     * @param dy - the y position change.
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * Returns the current ball's velocity.
     * @return this.velocity - the current ball's velocity.
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * Set the center of the ball to be the received point.
     * @param c - new center of the ball.
     */
    public void setCenter(Point c) {
        this.center = c;
    }

    /**
     * Set the center of the ball to be the x,y.
     * @param x - new x axis of the ball.
     * @param y - new y axis of the ball.
     */
    public void setCenter(double x, double y) {
        this.center = new Point(x, y);
    }
}
