package arkanoid.listeners;
import arkanoid.shapedObjects.Ball;
import arkanoid.shapedObjects.Block;

/**
 * Arkanoid.Listeners.HitListener is a listener to hits on objects.
 * @author Yossi Maatook.
 */
public interface HitListener {

    /**
     * This method is called whenever the beingHit object is hit.
     * @param beingHit - the block that is being hit.
     * @param hitter - the Shapes.Ball that's doing the hitting.
     */
    void hitEvent(Block beingHit, Ball hitter);
}