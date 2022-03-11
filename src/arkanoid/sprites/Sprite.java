package arkanoid.sprites;
import arkanoid.game.levels.GameLevel;
import biuoop.DrawSurface;

/**
 * Arkanoid.Sprites.Sprite represents object which are drawable in the game.
 */
public interface Sprite {

    /**
     * draw the sprite on the received DrawSurface.
     * @param d - the DrawSurface which we want to draw on.
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has passed.
     */
    void timePassed();

    /**
     * Add the sprite to the game.
     * @param gameLevel - the game to be add to.
     */
    void addToGame(GameLevel gameLevel);
}