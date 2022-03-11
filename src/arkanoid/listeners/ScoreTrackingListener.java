package arkanoid.listeners;
import counters.Counter;
import arkanoid.shapedObjects.Ball;
import arkanoid.shapedObjects.Block;

/**
 * Arkanoid.Listeners.ScoreTrackingListener a listener that increase the score when a block has been hit.
 */
public class ScoreTrackingListener implements HitListener {
    public static final int BLOCK_HIT = 5;
    private Counter currentScore;

    /**
     * Contractor.
     * @param scoreCounter - the counter of the score in the game.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(BLOCK_HIT);
    }
}