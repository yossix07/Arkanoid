package arkanoid.background;
import arkanoid.shapedObjects.Rectangle;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * A background with building drawing.
 * @author Yossi Maatook.
 */
public class BuildingBackground extends Background {
    public static final int BUILDING_X = 70;
    public static final int BUILDING_Y = 430;
    public static final int BUILDING_WIDTH = 100;
    public static final int BUILDING_HEIGHT = 170;
    public static final int BLOCKS_X = 80;
    public static final int BLOCKS_Y = 440;
    public static final int BLOCKS_WIDTH = 10;
    public static final int BLOCKS_HEIGHT = 30;
    public static final int BLOCKS_IN_ROW = 5;
    public static final int BLOCKS_ROWS = 5;
    public static final int ROWS_GAP = 5;
    public static final int BLOCKS_GAP = 8;
    public static final int ROOF_X = BUILDING_X + BUILDING_WIDTH / 2 - 14;
    public static final int ROOF_HEIGHT = 50;
    public static final int ROOF_Y = BUILDING_Y - ROOF_HEIGHT;
    public static final int ROOF_WIDTH = 30;
    public static final int STICK_HEIGHT = 200;
    public static final int STICK_WIDTH = 8;
    public static final int STICK_X = ROOF_X + ROOF_WIDTH / 2 - 4;
    public static final int BIG_RADIUS = 14;
    public static final int MEDIUM_RADIUS = 8;
    public static final int SMALL_RADIUS = 4;
    public static final int BULB_G = 16;
    public static final int BULB_R = 179;
    public static final int BULB_B = 16;

    /**
     * Sets the fields of the background.
     *
     * @param rectangle - rectangle of the background.
     * @param color     - color of the background.
     */
    public BuildingBackground(Rectangle rectangle, Color color) {
        super(rectangle, color);
    }

    @Override
    public void drawOn(DrawSurface surface) {
        //Draws the background//
        super.drawOn(surface);

        //Draws the building//
        this.drawBuilding(surface);
    }

    /**
     * Draws the building drawing on the surface.
     * @param surface - the surface to be drawn on.
     */
    private void drawBuilding(DrawSurface surface) {
        surface.setColor(Color.black);
        //Draws the building main rectangle//
        surface.fillRectangle(BUILDING_X, BUILDING_Y, BUILDING_WIDTH, BUILDING_HEIGHT);

        surface.setColor(Color.white);
        int y = BLOCKS_Y;
        //Draws the windows of the building//
        for (int i = 0; i < BLOCKS_ROWS; i++) {
            int x = BLOCKS_X;
            for (int j = 0; j < BLOCKS_IN_ROW; j++) {
                surface.fillRectangle(x, y, BLOCKS_WIDTH, BLOCKS_HEIGHT);
                x = x + BLOCKS_WIDTH + BLOCKS_GAP;
            }
            y = y + BLOCKS_HEIGHT + ROWS_GAP;
        }

        surface.setColor(Color.darkGray);
        //Draws the building roof//
        surface.fillRectangle(ROOF_X, ROOF_Y, ROOF_WIDTH, ROOF_HEIGHT);
        //Draws the building stick//
        surface.fillRectangle(STICK_X, ROOF_Y - STICK_HEIGHT, STICK_WIDTH, STICK_HEIGHT);
        surface.setColor(Color.orange);
        surface.fillCircle(BUILDING_X + BUILDING_WIDTH / 2, ROOF_Y - STICK_HEIGHT - BIG_RADIUS, BIG_RADIUS);
        surface.setColor(new Color(BULB_R, BULB_G, BULB_B));
        surface.fillCircle(BUILDING_X + BUILDING_WIDTH / 2, ROOF_Y - STICK_HEIGHT - BIG_RADIUS, MEDIUM_RADIUS);
        surface.setColor(Color.white);
        surface.fillCircle(BUILDING_X + BUILDING_WIDTH / 2, ROOF_Y - STICK_HEIGHT - BIG_RADIUS, SMALL_RADIUS);
    }
}
