package arkanoid.game;
import arkanoid.game.levels.GameLevel;
import counters.Counter;
import arkanoid.sprites.Sprite;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * Arkanoid.Game.ScoreIndicator draws the current score of the game on the gui.
 * @author Yossi Maatook.
 */
public class ScoreIndicator implements Sprite {
    private Counter score;

    /**
     * Constructor.
     * @param s - the counter of the score.
     */
    public ScoreIndicator(Counter s) {
        this.score = s;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.drawText(350, 12, "Score: " + this.score.getValue(), 15);
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
