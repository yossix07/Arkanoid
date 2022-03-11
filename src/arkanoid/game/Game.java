package arkanoid.game;
import arkanoid.animation.AnimationRunner;
import biuoop.GUI;
import biuoop.KeyboardSensor;

/**
 * Game holds the game's gui, keyboard sensor, and animation runner.
 * @author Yossi Maatook.
 */
public class Game {
    public static final int FRAME_WIDTH = 800;
    public static final int FRAME_HEIGHT = 600;
    private GUI gui;
    private KeyboardSensor keyboardSensor;
    private AnimationRunner animationRunner;

    /**
     * Constructor.
     * Create the fields instances.
     */
    public Game() {
        this.gui = new GUI("arkanoid", FRAME_WIDTH, FRAME_HEIGHT);
        this.keyboardSensor = this.gui.getKeyboardSensor();
        this.animationRunner = new AnimationRunner(this.gui);
    }

    /**
     * GUI getter.
     * @return the gui of the game.
     */
    public GUI getGui() {
        return this.gui;
    }

    /**
     * KeyboardSensor getter.
     * @return the KeyboardSensor of the game.
     */
    public KeyboardSensor getKeyboardSensor() {
        return this.keyboardSensor;
    }

    /**
     * AnimationRunner getter.
     * @return the AnimationRunner of the game.
     */
    public AnimationRunner getAnimationRunner() {
        return this.animationRunner;
    }
}
