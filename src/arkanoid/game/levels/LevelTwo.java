package arkanoid.game.levels;
import arkanoid.background.SunBackground;
import arkanoid.geometry.Point;
import arkanoid.geometry.Velocity;
import arkanoid.shapedObjects.Block;
import arkanoid.shapedObjects.Rectangle;
import arkanoid.sprites.Sprite;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Second level of the game.
 * @author Yossi Maatook.
 */
public class LevelTwo implements LevelInformation {
    public static final int FRAME_WIDTH = 800;
    public static final int FRAME_HEIGHT = 600;
    public static final int ORIGIN_AXIS = 0;
    public static final int PADDLE_X = 100;
    public static final int PADDLE_Y = 585;
    public static final int NUM_OF_BLOCKS = 15;
    public static final int BLOCKS_WIDTH = 50;
    public static final int MIDDLE_BLOCK_WIDTH = 52;
    public static final int NUM_OF_BALLS = 10;
    public static final int PADDLE_SPEED = 2;
    public static final int PADDLE_WIDTH = 600;
    public static final int BALLS_SPEED = 5;
    public static final int BALLS_ANGLE_ONE = 180;
    public static final int BALLS_ANGLE_SECOND = 120;
    public static final int COLORS_NUM = 7;
    public static final int REGULAR_NUM_OF_BLOCKS = 2;
    public static final int MIDDLE_NUM_OF_BLOCKS = 3;
    public static final int BLOCKS_HEIGHT = 20;
    public static final int BLOCKS_Y = 250;
    public static final int ROW_X = 728;
    public static final int BALLS_ANGLE_GAP = 20;

    @Override
    public int numberOfBalls() {
        return NUM_OF_BALLS;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        ArrayList<Velocity> list = new ArrayList<>();
        for (int i = 0, angle = BALLS_ANGLE_ONE; i < NUM_OF_BALLS / 2; i++, angle = angle + BALLS_ANGLE_GAP) {
            list.add(Velocity.fromAngleAndSpeed(angle, BALLS_SPEED));
        }

        for (int i = 0, angle = BALLS_ANGLE_SECOND; i < NUM_OF_BALLS / 2; i++, angle = angle + BALLS_ANGLE_GAP) {
            list.add(Velocity.fromAngleAndSpeed(angle, BALLS_SPEED));
        }
        return list;
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
        return new String("Wide Easy");
    }

    @Override
    public Sprite getBackground() {
        return new SunBackground(new Rectangle(new Point(ORIGIN_AXIS, FRAME_HEIGHT),
                FRAME_WIDTH, FRAME_HEIGHT), Color.white);
    }

    @Override
    public List<Block> blocks() {
        ArrayList<Block> list = new ArrayList<>();
        Color[] rowsColors = {Color.CYAN, Color.PINK, Color.BLUE, Color.GREEN, Color.YELLOW,
                Color.ORANGE, Color.RED};
        int numOfBlocks;
        int blocksWidth;
        for (int i = 0, x = ROW_X; i < COLORS_NUM; i++, x = x - blocksWidth * numOfBlocks) {
            if (i == MIDDLE_NUM_OF_BLOCKS) {
                numOfBlocks = MIDDLE_NUM_OF_BLOCKS;
                blocksWidth = MIDDLE_BLOCK_WIDTH;
            } else {
                blocksWidth = BLOCKS_WIDTH;
                numOfBlocks = REGULAR_NUM_OF_BLOCKS;
            }
            list.addAll(Block.rowOfBlocksGenerator(x, BLOCKS_Y, blocksWidth, BLOCKS_HEIGHT,
                    numOfBlocks, rowsColors[i]));
        }
        return list;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return NUM_OF_BLOCKS;
    }
}
