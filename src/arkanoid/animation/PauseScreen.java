package arkanoid.animation;
import biuoop.DrawSurface;

/**
 * Print a pause screen to the user.
 * @author Yossi Maatook.
 */
public class PauseScreen implements Animation {
    public static final int TEXT_X = 10;
    public static final int TEXT_SIZE = 32;

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(TEXT_X, d.getHeight() / 2, "paused -- press space to continue", TEXT_SIZE);
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}
