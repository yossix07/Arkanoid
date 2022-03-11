package arkanoid.sprites;
import biuoop.DrawSurface;
import java.util.ArrayList;

/**
 * Arkanoid.Sprites.SpriteCollection has a list of all Sprites in the game.
 * @author Yossi Maatook.
 */
public class SpriteCollection {
    private java.util.ArrayList<Sprite> sprites;

    /**
     * Sets a new list of Sprites.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<Sprite>();
    }

    /**
     * Add the received Arkanoid.Sprites.Sprite to the list.
     * @param s - the Arkanoid.Sprites.Sprite which we want to add.
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }

    /**
     * Removes the received Arkanoid.Sprites.Sprite from the list.
     * @param s - the Arkanoid.Sprites.Sprite which we want to remove.
     */
    public void removeSprite(Sprite s) {
        this.sprites.remove(s);
    }

    /**
     * Add the received array of Arkanoid.Sprites.Sprite to the list.
     * @param array - the Sprites which we want to add.
     */
    public void addSpriteArray(Sprite[] array) {
        for (Sprite s:array) {
            addSprite(s);
        }
    }

    /**
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        ArrayList<Sprite> copy = new ArrayList<>(this.sprites);
        for (Sprite s:copy) {
            s.timePassed();
        }
    }

    /**
     * call drawOn(d) on all sprites.
      * @param d - the DrawSurface on which we want to draw.
     */
    public void drawAllOn(DrawSurface d) {
        ArrayList<Sprite> copy = new ArrayList<>(this.sprites);
        for (Sprite s:copy) {
            s.drawOn(d);
        }
    }
}