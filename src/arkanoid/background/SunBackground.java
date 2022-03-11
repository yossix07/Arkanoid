package arkanoid.background;
import arkanoid.shapedObjects.Rectangle;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * A background with a sun drawing.
 * @author Yossi Maatook.
 */
public class SunBackground extends Background {
    public static final int GOLD_COLOR_R = 255;
    public static final int GOLD_COLOR_G = 240;
    public static final int GOLD_COLOR_B = 153;
    public static final int YELLOW_COLOR_R = 228;
    public static final int YELLOW_COLOR_G = 238;
    public static final int YELLOW_COLOR_B = 37;
    public static final int SUN_CENTER = 135;
    public static final int BIG_RADIUS = 55;
    public static final int MEDIUM_RADIUS = 45;
    public static final int SMALL_RADIUS = 35;
    public static final int BLOCKS_HEIGHT = 230;
    public static final int LINES_X_START = 20;
    public static final int LINES_X_LIMIT = 680;
    public static final int LINES_GAP = 15;

    /**
     * Sets the fields of the background.
     *
     * @param rectangle - rectangle of the background.
     * @param color     - color of the background.
     */
    public SunBackground(Rectangle rectangle, Color color) {
        super(rectangle, color);
    }

    @Override
    public void drawOn(DrawSurface surface) {
        super.drawOn(surface);
        this.drawSun(surface);
    }

    /**
     * Draws sun on the DrawSurface.
     * @param surface - the surface to be drawn on.
     */
    private void drawSun(DrawSurface surface) {
        surface.setColor(new Color(GOLD_COLOR_R, GOLD_COLOR_G, GOLD_COLOR_B));
        surface.fillCircle(SUN_CENTER, SUN_CENTER, BIG_RADIUS);
        int x = LINES_X_START;
        //Draws the sun's beams//
        while (x <= LINES_X_LIMIT) {
            surface.drawLine(SUN_CENTER, SUN_CENTER, x, BLOCKS_HEIGHT);
            x = x + LINES_GAP;
        }

        surface.setColor(new Color(YELLOW_COLOR_R, YELLOW_COLOR_G, YELLOW_COLOR_B));
        surface.fillCircle(SUN_CENTER, SUN_CENTER, MEDIUM_RADIUS);
        surface.setColor(Color.yellow);
        surface.fillCircle(SUN_CENTER, SUN_CENTER, SMALL_RADIUS);
    }
}
