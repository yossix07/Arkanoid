package arkanoid.listeners;
import counters.Counter;
import arkanoid.game.levels.GameLevel;
import arkanoid.shapedObjects.Ball;
import arkanoid.shapedObjects.Block;

/**
 * Arkanoid.Listeners.BallRemover is in charge of removing balls from the game, as well as keeping count
 * of the number of balls that remain.
 * @author Yossi Maatook.
 */
public class BallRemover implements HitListener {
    public static final int BALL_REMOVED = 1;
    private GameLevel gameLevel;
    private Counter counter;

    /**
     * Constructor.
     * @param g - Arkanoid.Game.Game which the ball is part of.
     * @param c - Number of balls in the game.
     */
    public BallRemover(GameLevel g, Counter c) {
        this.gameLevel = g;
        this.counter = c;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        //decrement the number of balls in the game//
        this.counter.decrease(BALL_REMOVED);

        //Remove the ball from the game//
        hitter.removeFromGame(this.gameLevel);
    }
}
