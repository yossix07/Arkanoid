package arkanoid.collidables;
import arkanoid.geometry.Line;
import arkanoid.geometry.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * Arkanoid.Collidables.GameEnvironment has a list of all the collidables object in a game.
 * @author Yossi Maatook.
 */
public class GameEnvironment {
    private java.util.List<Collidable> collidables;

    /**
     * Sets the list of collidables.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<Collidable>();
    }

    /**
     * Returns the list of collidables.
     * @return the list of collidables.
     */
    public List<Collidable> getCollidables() {
        return this.collidables;
    }

    /**
     * Adds the received collidable to the environment.
     * @param c - the collidable which we want to add.
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * Adds all the collidables in the received array to the environment.
     * @param array - the array which it's members we want to add.
     */
    public void addArrayCollidable(Collidable[] array) {
        for (Collidable c:array) {
            addCollidable(c);
        }
    }

    /**
     * Return the Arkanoid.Collidables.CollisionInfo of closest collision that is going to occur to the
     * received line with the environment members.
     * If no collision is going to occur, return null.
     * @param trajectory - the line which we check where it's closest collision.
     * @return null if there is no collision and the Arkanoid.Collidables.CollisionInfo of closest collision otherwise.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        int counter = 0;

        //Count how many collisions occur//
        for (int i = 0; i < this.collidables.size(); i++) {
            if (trajectory.closestIntersectionToStartOfLine(this.collidables.get(i).getCollisionRectangle()) != null) {
                counter++;
            }
        }

        //In case there no collisions occur//
        if (counter == 0) {
            return null;
        }

        Point[] intersectionPoints = new Point[counter];
        Collidable[] intersectionColliadble = new Collidable[counter];

        //Save the info of the collisions in two arrays//
        for (int i = 0, j = 0; i < this.collidables.size(); i++) {
            if (trajectory.closestIntersectionToStartOfLine(this.collidables.get(i).getCollisionRectangle()) != null) {
                intersectionColliadble[j] = this.collidables.get(i);
                intersectionPoints[j] =
                        trajectory.closestIntersectionToStartOfLine(this.collidables.get(i).getCollisionRectangle());
                j++;
            }
        }

        //Get the index of the closest collision//
        int wantedIndex = trajectory.whichPointIsTheClosestToStart(intersectionPoints);
        return new CollisionInfo(intersectionPoints[wantedIndex], intersectionColliadble[wantedIndex]);
    }
}