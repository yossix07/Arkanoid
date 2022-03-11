package arkanoid.game;
import arkanoid.game.levels.GameLevel;
import arkanoid.sprites.Sprite;
import biuoop.DrawSurface;
import counters.Counter;
import java.awt.Color;

/**
 * Arkanoid.Game.LivesIndicator draws the current amount of live in the game on the gui.
 * @author Yossi Maatook.
 */
public class LivesIndicator implements Sprite {
    private Counter lives;

    /**
     * Constructor.
     * @param l - the counter of the lives.
     */
    public LivesIndicator(Counter l) {
        this.lives = l;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.drawText(180, 12, "Lives: " + this.lives.getValue(), 15);
    }

    @Override
    public void timePassed() {
        return;
    }

    /**
     * Add current Arkanoid.Game.LivesIndicator to the received game.
     * @param g - the game which we want to add the Arkanoid.Game.LivesIndicator to.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
