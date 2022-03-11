package arkanoid.game.levels;
import arkanoid.background.BuildingBackground;
import arkanoid.geometry.Point;
import arkanoid.geometry.Velocity;
import arkanoid.shapedObjects.Block;
import arkanoid.shapedObjects.Rectangle;
import arkanoid.sprites.Sprite;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Third level of the game.
 * @author Yossi Maatook.
 */
public class LevelThree implements LevelInformation {
    public static final int NUM_OF_BALLS = 2;
    public static final int NUM_OF_BLOCKS = 45;
    public static final int FRAME_WIDTH = 800;
    public static final int FRAME_HEIGHT = 600;
    public static final int ORIGIN_AXIS = 0;
    public static final int PADDLE_X = 365;
    public static final int PADDLE_Y = 585;
    public static final int PADDLE_SPEED = 6;
    public static final int PADDLE_WIDTH = 75;
    public static final int MAX_BLOCKS_IN_ROW = 10;
    private static final int STARTING_Y_OF_ROW = 150;
    private static final int ROWS_NUM = 5;
    private static final int STARTING_X_OF_ROW = 730;
    private static final int FIELD_BLOCK_WIDTH = 50;
    private static final int FIELD_BLOCK_HEIGHT = 20;
    public static final int BALLS_SPEED = 6;
    public static final int BALL_ANGLE_ONE = 50;
    public static final int BALL_ANGLE_SECOND = 230;

    @Override
    public int numberOfBalls() {
        return NUM_OF_BALLS;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        ArrayList<Velocity> velocities = new ArrayList<>();
        velocities.add(Velocity.fromAngleAndSpeed(BALL_ANGLE_ONE, BALLS_SPEED));
        velocities.add(Velocity.fromAngleAndSpeed(BALL_ANGLE_SECOND, BALLS_SPEED));
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
        return new String("Green 3");
    }

    @Override
    public Sprite getBackground() {
        return new BuildingBackground(new Rectangle(new Point(ORIGIN_AXIS, FRAME_HEIGHT),
                FRAME_WIDTH, FRAME_HEIGHT), Color.green);
    }

    @Override
    public List<Block> blocks() {
        ArrayList<Block> list = new ArrayList<>();
        Color[] rowsColors = {Color.darkGray, Color.red, Color.yellow, Color.BLUE, Color.white};
        for (int i = 0, j = MAX_BLOCKS_IN_ROW, startingY = STARTING_Y_OF_ROW; i < ROWS_NUM; i++, j--) {
            list.addAll(Block.rowOfBlocksGenerator(STARTING_X_OF_ROW, startingY,
                    FIELD_BLOCK_WIDTH, FIELD_BLOCK_HEIGHT, j, rowsColors[i]));
            startingY = startingY + FIELD_BLOCK_HEIGHT;
        }
        return list;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return NUM_OF_BLOCKS;
    }
}
