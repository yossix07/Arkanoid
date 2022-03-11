package arkanoid.animation;
import biuoop.DrawSurface;
import counters.Counter;

/**
 * Print to the user that the game is over and it's current score.
 * @author Yossi Maatook.
 */
public class GameOver implements Animation {
    public static final int TEXT_X = 10;
    public static final int TEXT_SIZE = 32;
    private Counter score;

    /**
     * Constructor.
     * @param s - the game score counter.
     */
    public GameOver(Counter s) {
        this.score = s;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(TEXT_X, d.getHeight() / 2, "Game Over. Your score is " + this.score.getValue(), TEXT_SIZE);
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}
