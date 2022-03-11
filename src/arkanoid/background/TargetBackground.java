package arkanoid.background;
import arkanoid.shapedObjects.Rectangle;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * Background with target.
 * @author Yossi Maatook.
 */
public class TargetBackground extends Background {
    public static final int NUM_OF_CIRCLES = 3;
    public static final int CIRCLE_CENTER_X = 407;
    public static final int CIRCLE_CENTER_Y = 157;
    public static final int SMALL_RADIUS = 50;
    public static final int RADIUS_GAP = 25;
    public static final int LEFT_LINE_X_ONE = 425;
    public static final int LEFT_LINE_X_TWO = 545;
    public static final int RIGHT_LINE_X_ONE = 270;
    public static final int RIGHT_LINE_X_TWO = 390;
    public static final int LEFT_RIGHT_Y = 156;
    public static final int UPPER_LINE_Y_ONE = 175;
    public static final int UPPER_LINE_Y_TWO = 295;
    public static final int DOWN_LINE_Y_ONE = 20;
    public static final int DOWN_LINE_Y_TWO = 140;
    public static final int UPPER_DOWN_X = 407;

    /**
     * Sets the fields of the background.
     * @param rectangle - rectangle of the background.
     * @param color     - color of the background.
     */
    public TargetBackground(Rectangle rectangle, Color color) {
        super(rectangle, color);
    }

    @Override
    public void drawOn(DrawSurface surface) {
        super.drawOn(surface);
        this.drawTarget(surface);
    }

    /**
     * Draws a target on the DrawSurface.
     * @param surface - the surface to be drawn on.
     */
    private void drawTarget(DrawSurface surface) {
        surface.setColor(Color.blue);

        //Draws the target's circles//
        for (int i = 0, radius = SMALL_RADIUS; i < NUM_OF_CIRCLES; i++, radius = radius + RADIUS_GAP) {
            surface.drawCircle(CIRCLE_CENTER_X, CIRCLE_CENTER_Y, radius);
        }
        surface.drawLine(LEFT_LINE_X_ONE, LEFT_RIGHT_Y, LEFT_LINE_X_TWO, LEFT_RIGHT_Y);
        surface.drawLine(RIGHT_LINE_X_ONE, LEFT_RIGHT_Y, RIGHT_LINE_X_TWO, LEFT_RIGHT_Y);
        surface.drawLine(UPPER_DOWN_X, UPPER_LINE_Y_ONE, UPPER_DOWN_X, UPPER_LINE_Y_TWO);
        surface.drawLine(UPPER_DOWN_X, DOWN_LINE_Y_ONE, UPPER_DOWN_X, DOWN_LINE_Y_TWO);
    }
}
