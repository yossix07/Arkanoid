package arkanoid.listeners;
import counters.Counter;
import arkanoid.game.levels.GameLevel;
import arkanoid.shapedObjects.Ball;
import arkanoid.shapedObjects.Block;

/**
 * Arkanoid.Listeners.BlockRemover is in charge of removing blocks from the game, as well as keeping count
 * of the number of blocks that remain.
 * @author Yossi Maatook.
 */
public class BlockRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBlocks;

    /**
     * Constructor.
     * @param g - Arkanoid.Game.Game which the block is part of.
     * @param c - Number of blocks in the game.
     */
    public BlockRemover(GameLevel g, Counter c) {
        this.gameLevel = g;
        this.remainingBlocks = c;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.remainingBlocks.decrease(1);
        beingHit.removeHitListener(this);
        beingHit.removeFromGame(this.gameLevel);
    }
}