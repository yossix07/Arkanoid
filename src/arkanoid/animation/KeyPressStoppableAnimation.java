package arkanoid.animation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * Animation which can be stopped by a keyboard key press.
 * @author Yossi Maatook.
 */
public class KeyPressStoppableAnimation implements Animation {
    private Animation decorator;
    private KeyboardSensor keyboard;
    private String key;
    private boolean stop;
    private boolean isAlreadyPressed;

    /**
     * Constructor.
     * @param animation - the animation to be run.
     * @param sensor - senor of the keyboard.
     * @param k - the key that stops the animation.
     */
    public KeyPressStoppableAnimation(Animation animation, KeyboardSensor sensor, String k) {
        this.decorator = animation;
        this.keyboard = sensor;
        this.key = k;
        this.stop = false;
        this.isAlreadyPressed = true;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        decorator.doOneFrame(d);

        //In case the relevant key is pressed, stop animation//
        if (this.keyboard.isPressed(this.key) && !this.isAlreadyPressed) {
            this.stop = true;
        }
        if (!this.keyboard.isPressed(this.key)) {
            this.isAlreadyPressed = false;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
