package arkanoid.game.levels;
import arkanoid.background.TargetBackground;
import arkanoid.geometry.Point;
import arkanoid.geometry.Velocity;
import arkanoid.shapedObjects.Block;
import arkanoid.shapedObjects.Rectangle;
import arkanoid.sprites.Sprite;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * First level of the game.
 * @author Yossi Maatook.
 */
public class LevelOne implements LevelInformation {
    public static final int NUM_OF_BALLS = 1;
    public static final int NUM_OF_BLOCKS = 1;
    public static final int FRAME_WIDTH = 800;
    public static final int FRAME_HEIGHT = 600;
    public static final int ORIGIN_AXIS = 0;
    public static final int PADDLE_X = 365;
    public static final int PADDLE_Y = 585;
    public static final int PADDLE_SPEED = 3;
    public static final int PADDLE_WIDTH = 75;
    public static final int VELOCITY_ANGLE = 0;
    public static final int VELOCITY_SPEED = 5;
    public static final int BLOCK_WIDTH_HEIGHT = 25;
    public static final int BLOCK_X = 395;
    public static final int BLOCK_Y = 170;
    public static final int BALL_X = 407;
    public static final int BALL_Y = 500;


    @Override
    public int numberOfBalls() {
        return NUM_OF_BALLS;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        ArrayList<Velocity> list = new ArrayList<>();
        list.add(Velocity.fromAngleAndSpeed(VELOCITY_ANGLE, VELOCITY_SPEED));
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
        return new String("Direct Hit");
    }

    @Override
    public Sprite getBackground() {
        return new TargetBackground(new Rectangle(new Point(ORIGIN_AXIS, FRAME_HEIGHT),
                FRAME_WIDTH, FRAME_HEIGHT), Color.black);
    }

    @Override
    public List<Block> blocks() {
        ArrayList<Block> list = new ArrayList<>();
        list.add(new Block(new Rectangle(new Point(BLOCK_X, BLOCK_Y), BLOCK_WIDTH_HEIGHT,
                BLOCK_WIDTH_HEIGHT), Color.red));
        return list;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return NUM_OF_BLOCKS;
    }
}
