package arkanoid.background;
import arkanoid.game.levels.GameLevel;
import arkanoid.shapedObjects.Rectangle;
import arkanoid.sprites.Sprite;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * Arkanoid.Game.Background is the background of the game - a rectangle with color.
 * @author Yossi Maatook.
 */
public class Background implements Sprite {
    private Rectangle rectangle;
    private Color color;

    /**
     * Sets the fields of the background.
     * @param rectangle - rectangle of the background.
     * @param color - color of the background.
     */
    public Background(Rectangle rectangle, Color color) {
        this.rectangle = rectangle;
        this.color = color;
    }

    @Override
    public void drawOn(DrawSurface surface) {
        this.rectangle.drawOn(surface, this.rectangle, this.color);
    }

    @Override
    public void timePassed() {
        return;
    }

    /**
     * Adds current background to the game.
     * @param g - the game which we want to add the background to.
     */
    public void addToGame(GameLevel g) {
        g.getSprites().addSprite(this);
    }
}

