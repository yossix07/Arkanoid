package arkanoid.game.levels;
import arkanoid.background.CloudsBackground;
import arkanoid.geometry.Point;
import arkanoid.geometry.Velocity;
import arkanoid.shapedObjects.Block;
import arkanoid.shapedObjects.Rectangle;
import arkanoid.sprites.Sprite;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Fourth level of the game.
 * @author Yossi Maatook.
 */
public class LevelFour implements LevelInformation {
    public static final int NUM_OF_BALLS = 3;
    public static final int FRAME_WIDTH = 800;
    public static final int FRAME_HEIGHT = 600;
    public static final int ORIGIN_AXIS = 0;
    public static final int PADDLE_X = 365;
    public static final int PADDLE_Y = 585;
    public static final int PADDLE_SPEED = 7;
    public static final int PADDLE_WIDTH = 75;
    public static final int BLOCKS_IN_ROW = 15;
    private static final int STARTING_Y_OF_ROW = 150;
    private static final int ROWS_NUM = 7;
    private static final int STARTING_X_OF_ROW = 730;
    private static final int FIELD_BLOCK_WIDTH = 51;
    private static final int FIELD_BLOCK_HEIGHT = 20;
    public static final int BACKGROUND_R = 0;
    public static final int BACKGROUND_G = 163;
    public static final int BACKGROUND_B = 227;
    public static final int FIRST_BALL_ANGLE = -5;
    public static final int SECOND_BALL_ANGLE = 5;
    public static final int THIRD_BALL_ANGLE = 0;
    public static final int BALLS_SPEED = 6;

    @Override
    public int numberOfBalls() {
        return NUM_OF_BALLS;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        ArrayList<Velocity> velocities = new ArrayList<>();
        velocities.add(Velocity.fromAngleAndSpeed(FIRST_BALL_ANGLE, BALLS_SPEED));
        velocities.add(Velocity.fromAngleAndSpeed(SECOND_BALL_ANGLE, BALLS_SPEED));
        velocities.add(Velocity.fromAngleAndSpeed(THIRD_BALL_ANGLE, BALLS_SPEED));
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return PADDLE_SPEED;
    }

    @Override
    public int paddleWidth() {
        return PADDLE_WIDTH;
    }

    @Override
    public int paddleAxisX() {
        return PADDLE_X;
    }

    @Override
    public int paddleAxisY() {
        return PADDLE_Y;
    }

    @Override
    public String levelName() {
        return new String("Final Four");
    }

    @Override
    public Sprite getBackground() {
        return new CloudsBackground(new Rectangle(new Point(ORIGIN_AXIS, FRAME_HEIGHT),
                FRAME_WIDTH, FRAME_HEIGHT), new Color(BACKGROUND_R, BACKGROUND_G, BACKGROUND_B));
    }

    @Override
    public List<Block> blocks() {
        ArrayList<Block> list = new ArrayList<>();
        Color[] rowsColors = {Color.darkGray, Color.red, Color.yellow, Color.green,
                Color.white, Color.pink, Color.cyan};
        for (int i = 0, startingY = STARTING_Y_OF_ROW; i < ROWS_NUM; i++) {
            ArrayList<Block> blocks = Block.rowOfBlocksGenerator(STARTING_X_OF_ROW, startingY,
                    FIELD_BLOCK_WIDTH, FIELD_BLOCK_HEIGHT, BLOCKS_IN_ROW, rowsColors[i]);
            list.addAll(blocks);
            startingY = startingY + FIELD_BLOCK_HEIGHT;
        }
        return list;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return BLOCKS_IN_ROW * ROWS_NUM;
    }
}
