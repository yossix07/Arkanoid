package arkanoid.animation;
import biuoop.DrawSurface;

/**
 * Animation is in charge of doing a frame animation in the game.
 * @author Yossi Maatook.
 */
public interface Animation {

    /**
     * Draws the relevant animation on the received DrawSurface.
     * @param d - the DrawSurface to be drawn on.
     */
    void doOneFrame(DrawSurface d);

    /**
     * Returns true if the animation should stop running and false otherwise.
     * @return true if the animation should stop running and false otherwise.
     */
    boolean shouldStop();
}