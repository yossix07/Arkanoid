package arkanoid.animation;
import arkanoid.sprites.SpriteCollection;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * The CountdownAnimation will display the given gameScreen, for numOfSeconds seconds, and on top of them it will show
 * a countdown from countFrom back to 1, where each number will appear on the screen for (numOfSeconds / countFrom)
 * seconds, before it is replaced with the next one.
 * @author Yossi Maatook.
 */
public class CountdownAnimation implements Animation {
    public static final int TEXT_X = 400;
    public static final int TEXT_SIZE = 30;
    public static final double MILLISECOND = 1.0 / 60;
    public static final int DEFAULT_NUM_OF_SECONDS = 2;
    public static final int DEFAULT_COUNT_FROM = 3;
    private double numOfSeconds;
    private double currentSeconds;
    private int countFrom;
    private int currentCount;
    private SpriteCollection gameScreen;
    private boolean stop;

    /**
     * Constructor.
     * @param numOfSeconds - number of seconds the animation will run.
     * @param countFrom - the starting number of the count.
     * @param gameScreen - the game sprites.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.currentSeconds = numOfSeconds / countFrom;
        this.currentCount = countFrom;
        this.gameScreen = gameScreen;
        this.stop = false;
    }

    /**
     * Constructor.
     * Sets default values to numOfSeconds and countFrom.
     * @param gameScreen - the game sprites.
     */
    public CountdownAnimation(SpriteCollection gameScreen) {
        this(DEFAULT_NUM_OF_SECONDS, DEFAULT_COUNT_FROM, gameScreen);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        //Draw the game screen sprites//
        this.gameScreen.drawAllOn(d);

        d.setColor(Color.ORANGE);

        //Draw the current count on the game screen//
        d.drawText(TEXT_X, d.getHeight() / 2, String.valueOf(this.currentCount), TEXT_SIZE);

        this.currentSeconds = this.currentSeconds - MILLISECOND;

        //In case current number reached it's time, move to next number in the count//
        if (this.currentSeconds <= 0) {

            //Decrease to the next number in the count//
            this.currentCount--;

            //Reset current number's count//
            this.currentSeconds = this.numOfSeconds / this.countFrom;
        }

        //In case the count has reached 0, stop animation//
        if (this.currentCount == 0) {
            this.stop = true;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
