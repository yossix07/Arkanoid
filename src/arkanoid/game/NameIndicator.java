package arkanoid.game;
import arkanoid.game.levels.GameLevel;
import arkanoid.sprites.Sprite;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * Draws the current level name on the GUI.
 * @author Yossi Maatook.
 */
public class NameIndicator implements Sprite {
    private String name;

    /**
     * Constructor.
     * @param n - the level name.
     */
    public NameIndicator(String n) {
        this.name = n;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.drawText(500, 12, "Level Name: " + this.name, 15);
    }

    @Override
    public void timePassed() {
        return;
    }

    /**
     * Add current Arkanoid.Game.ScoreIndicator to the received game.
     * @param g - the game which we want to add the Arkanoid.Game.ScoreIndicator to.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
