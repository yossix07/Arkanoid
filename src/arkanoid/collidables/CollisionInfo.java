package arkanoid.collidables;
import arkanoid.geometry.Point;

/**
 * Arkanoid.Collidables.CollisionInfo hold the information about the collision such as the collision point and object.
 * @author Yossi Maatook.
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * Sets the fields of the Arkanoid.Collidables.CollisionInfo.
     * @param collisionPoint - the point which the collision accord at.
     * @param collisionObject - the object which the collision accord with.
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     * Return the point at which the collision occurs.
     * @return the point at which the collision occurs.
     */
    public Point getCollisionPoint() {
        return this.collisionPoint;
    }

    /**
     * Returns the collidable object involved in the collision.
     * @return the collidable object involved in the collision.
     */
    public Collidable getCollisionObject() {
        return this.collisionObject;
    }
}