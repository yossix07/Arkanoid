package arkanoid.background;
import arkanoid.shapedObjects.Rectangle;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * Background with Clouds.
 * @author Yossi Maatook.
 */
public class CloudsBackground extends Background {
    public static final int LINES_GAP = 7;
    public static final int LINES_SLOPE = 25;
    public static final int LINES_NUM = 10;
    public static final int FIRST_CLOUD_CENTER_HEIGHT = 380;
    public static final int FIRST_CLOUD_CENTER_X = 110;
    public static final int SECOND_CLOUD_CENTER_HEIGHT = 500;
    public static final int SECOND_CLOUD_CENTER_X = 610;
    public static final int FRAME_HEIGHT = 600;
    public static final int SMALL_BALL = 21;
    public static final int MEDIUM_BALL = 24;
    public static final int BIG_BALL = 27;
    public static final int HUGE_BALL = 30;
    public static final int WHITE_BALL_R = 215;
    public static final int WHITE_BALL_G = 215;
    public static final int WHITE_BALL_B = 222;
    public static final int GREY_BALL_R = 191;
    public static final int GREY_BALL_G = 191;
    public static final int GREY_BALL_B = 198;
    public static final int DARK_GREY_BALL_R = 152;
    public static final int DARK_GREY_BALL_G = 152;
    public static final int DARK_GREY_BALL_B = 158;
    public static final int FIRST_BALL_CLOUD_ONE_X = 100;
    public static final int SECOND_BALL_CLOUD_ONE_X = 120;
    public static final int THIRD_BALL_CLOUD_ONE_X = 140;
    public static final int FORTH_BALL_CLOUD_ONE_X = 160;
    public static final int FIFTH_BALL_CLOUD_ONE_X = 180;
    public static final int FIRST_BALL_CLOUD_ONE_Y = 380;
    public static final int SECOND_BALL_CLOUD_ONE_Y = 405;
    public static final int THIRD_BALL_CLOUD_ONE_Y = 370;
    public static final int FORTH_BALL_CLOUD_ONE_Y = 400;
    public static final int FIFTH_BALL_CLOUD_ONE_Y = 385;
    public static final int FIRST_BALL_CLOUD_TWO_X = 600;
    public static final int SECOND_BALL_CLOUD_TWO_X = 620;
    public static final int THIRD_BALL_CLOUD_TWO_X = 630;
    public static final int FORTH_BALL_CLOUD_TWO_X = 655;
    public static final int FIFTH_BALL_CLOUD_TWO_X = 670;
    public static final int FIRST_BALL_CLOUD_TWO_Y = 500;
    public static final int SECOND_BALL_CLOUD_TWO_Y = 535;
    public static final int THIRD_BALL_CLOUD_TWO_Y = 507;
    public static final int FORTH_BALL_CLOUD_TWO_Y = 521;
    public static final int FIFTH_BALL_CLOUD_TWO_Y = 512;

    /**
     * Sets the fields of the background.
     *
     * @param rectangle - rectangle of the background.
     * @param color     - color of the background.
     */
    public CloudsBackground(Rectangle rectangle, Color color) {
        super(rectangle, color);
    }

    @Override
    public void drawOn(DrawSurface surface) {
        super.drawOn(surface);
        this.drawClouds(surface);
    }

    /**
     * Draws 2 clouds on the DrawSurface.
     * @param surface - the surface to be drawn on.
     */
    private void drawClouds(DrawSurface surface) {
        //Draws rain//
        surface.setColor(Color.white);
        for (int i = 0, x = FIRST_CLOUD_CENTER_X, j = SECOND_CLOUD_CENTER_X; i < LINES_NUM;
             i++, x = x + LINES_GAP, j = j + LINES_GAP) {
            surface.drawLine(x, FIRST_CLOUD_CENTER_HEIGHT, x - LINES_SLOPE, FRAME_HEIGHT);
            surface.drawLine(j, SECOND_CLOUD_CENTER_HEIGHT, j - LINES_SLOPE, FRAME_HEIGHT);
        }
        surface.setColor(new Color(WHITE_BALL_R, WHITE_BALL_G, WHITE_BALL_B));
        surface.fillCircle(FIRST_BALL_CLOUD_ONE_X, FIRST_BALL_CLOUD_ONE_Y, BIG_BALL);
        surface.fillCircle(SECOND_BALL_CLOUD_ONE_X, SECOND_BALL_CLOUD_ONE_Y, MEDIUM_BALL);
        surface.fillCircle(FIRST_BALL_CLOUD_TWO_X, FIRST_BALL_CLOUD_TWO_Y, MEDIUM_BALL);
        surface.fillCircle(SECOND_BALL_CLOUD_TWO_X, SECOND_BALL_CLOUD_TWO_Y, BIG_BALL);

        surface.setColor(new Color(GREY_BALL_R, GREY_BALL_G, GREY_BALL_B));
        surface.fillCircle(THIRD_BALL_CLOUD_ONE_X, THIRD_BALL_CLOUD_ONE_Y, HUGE_BALL);
        surface.fillCircle(THIRD_BALL_CLOUD_TWO_X, THIRD_BALL_CLOUD_TWO_Y, BIG_BALL);

        surface.setColor(new Color(DARK_GREY_BALL_R, DARK_GREY_BALL_G, DARK_GREY_BALL_B));
        surface.fillCircle(FORTH_BALL_CLOUD_ONE_X, FORTH_BALL_CLOUD_ONE_Y, SMALL_BALL);
        surface.fillCircle(FIFTH_BALL_CLOUD_ONE_X, FIFTH_BALL_CLOUD_ONE_Y, HUGE_BALL);
        surface.fillCircle(FORTH_BALL_CLOUD_TWO_X, FORTH_BALL_CLOUD_TWO_Y, MEDIUM_BALL);
        surface.fillCircle(FIFTH_BALL_CLOUD_TWO_X, FIFTH_BALL_CLOUD_TWO_Y, BIG_BALL);
    }
}
