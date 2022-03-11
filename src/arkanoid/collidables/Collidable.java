package arkanoid.collidables;
import arkanoid.geometry.Point;
import arkanoid.geometry.Velocity;
import arkanoid.shapedObjects.Ball;
import arkanoid.shapedObjects.Rectangle;

/**
 * Arkanoid.Collidables.Collidable represents object which are collidable.
 * @author Yossi Maatook.
 */
public interface Collidable {

    /**
     * Return the "collision shape" of the object.
     * @return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint with a given velocity.
     * The return is the new velocity expected after the hit (based on the force the object inflicted on us).
     * @param collisionPoint - the point which the collision accrued on.
     * @param currentVelocity - the current velocity of the ball(before impact).
     * @param hitter - the ball which is hitting.
     * @return new velocity of the ball after the impact.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}