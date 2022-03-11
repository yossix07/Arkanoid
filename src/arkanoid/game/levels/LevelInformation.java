package arkanoid.game.levels;
import arkanoid.geometry.Velocity;
import arkanoid.shapedObjects.Block;
import arkanoid.sprites.Sprite;
import java.util.List;

/**
 * LevelInformation is a level in the game.
 * @author Yossi Maatook.
 */
public interface LevelInformation {

    /**
     * Returns the number of balls in the level.
     * @return the number of balls in the level.
     */
    int numberOfBalls();
    // The initial velocity of each ball
    // Note that initialBallVelocities().size() == numberOfBalls()

    /**
     * Returns a list with The initial velocity of each ball.
     * @return list with The initial velocity of each ball.
     */
    List<Velocity> initialBallVelocities();

    /**
     * Returns the paddle speed.
     * @return paddle speed.
     */
    int paddleSpeed();

    /**
     * Returns the paddle width.
     * @return paddle width.
     */
    int paddleWidth();

    /**
     * Returns the paddle x axis.
     * @return paddle x axis.
     */
    int paddleAxisX();

    /**
     * Returns the paddle y axis.
     * @return paddle y axis.
     */
    int paddleAxisY();

    /**
     * Returns a string with the level name.
     * @return string with the level name.
     */
    String levelName();

    /**
     * Returns a sprite with the background of the level.
     * @return sprite with the background of the level.
     */
    Sprite getBackground();
    // The Blocks that make up this level, each block contains
    // its size, color and location.

    /**
     * Returns the list of blocks that appears in the level.
     * @return list of blocks that appears in the level.
     */
    List<Block> blocks();
    // Number of blocks that should be removed
    // before the level is considered to be "cleared".
    // This number should be <= blocks.size();

    /**
     * Returns the number of blocks that should be removed from the game.
     * @return the number of blocks that should be removed from the game.
     */
    int numberOfBlocksToRemove();
}

