package arkanoid.shapedObjects;
import arkanoid.collidables.GameEnvironment;
import arkanoid.geometry.Point;

/**
 * Arkanoid.ShapedObjects.InsideObjects has methods regarding to balls locations - when a ball is inside anther object.
 * @author Yossi Maatook.
 */
public class InsideObjects {

    /**
     * Return true if the received ball is inside the received rectangle and false otherwise.
     * @param ball - the ball which we want to know if it is inside the rectangle.
     * @param rectangle - the rectangle which we want to know if the ball is in it.
     * @return true if the ball is in the rectangle and false otherwise.
     */
    public boolean isBallInRectangle(Ball ball, Rectangle rectangle) {
        return isPointInRectangle(ball.getCenter(), rectangle);
    }

    /**
     * Return true if the received point is inside the received rectangle and false otherwise.
     * @param point - the point which we want to know if it is inside the rectangle.
     * @param rectangle - the rectangle which we want to know if the point is in it.
     * @return true if the received point is inside the received rectangle and false otherwise.
     */
    public boolean isPointInRectangle(Point point, Rectangle rectangle) {
        double paddleStartingX = rectangle.getUpperLeft().getX();
        double paddleStartingY = rectangle.getUpperLeft().getY();

        //Returns true if the point is inside the rectangle//
        if (point.getX() > paddleStartingX
                && point.getX() < paddleStartingX + rectangle.getWidth()
                && point.getY() < paddleStartingY
                && point.getY() > paddleStartingY - rectangle.getHeight()) {
            return true;
        }
        return false;
    }

    /**
     * If the ball is inside a rectangle from the game,
     * returns the index of it's collidable in the Arkanoid.Collidables.GameEnvironment's collidables list.
     * Otherwise, returns -1.
     * @param ball - The wanted ball.
     * @return the index of the collidable in the Arkanoid.Collidables.GameEnvironment list
     * and -1 if the ball isn't in a rectangle.
     */
    public int isBallInAnyRectangle(Ball ball) {
        return isPointInAnyRectangle(ball.getCenter(), ball.getGameEnvironment());
    }

    /**
     * If the point is inside a rectangle from the Arkanoid.Collidables.GameEnvironment's collidables list,
     * returns it's index.
     * Otherwise returns -1.
     * @param point - checks if this point is inside any rectangle.
     * @param environment - the list of collidables that we want to know if the ball is in.
     * @return the index of the rectangle from the Arkanoid.Collidables.GameEnvironment's collidables list,
     * that the ball is in.
     * If the ball isn't in any of them, returns -1.
     */
    public int isPointInAnyRectangle(Point point, GameEnvironment environment) {
        for (int i = 0; i < environment.getCollidables().size(); i++) {

            //In case the ball is inside current rectangle, returns the index//
            if (this.isPointInRectangle(point, environment.getCollidables().get(i).getCollisionRectangle())) {
                return i;
            }
        }
        return -1;
    }
}
