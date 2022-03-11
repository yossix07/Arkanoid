package arkanoid.animation;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * Runs the game's animation on the gui.
 * @author Yossi Maatook.
 */
public class AnimationRunner {
    public static final int DEFAULT_FRAMES_PER_SECOND = 60;
    public static final int TOTAL_FRAMES = 1000;
    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper;

    /**
     * Constructor.
     * @param g - the gui which the game will be showed on.
     */
    public AnimationRunner(GUI g) {
        this.gui = g;
        this.framesPerSecond = DEFAULT_FRAMES_PER_SECOND;
        this.sleeper = new Sleeper();
    }

    /**
     * Runs the received animation on the gui of the game until it should be stopped.
     * @param animation - the animation will be showed to the user on the gui.
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = TOTAL_FRAMES / this.framesPerSecond;

        //As long as the animation should run, show it to the user//
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = this.gui.getDrawSurface();

            //Run current frame//
            animation.doOneFrame(d);

            //Show current frame to the user//
            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}