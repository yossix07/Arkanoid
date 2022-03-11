package arkanoid.game.levels;
import arkanoid.game.LivesIndicator;
import arkanoid.game.NameIndicator;
import arkanoid.game.ScoreIndicator;
import arkanoid.animation.Animation;
import arkanoid.animation.AnimationRunner;
import arkanoid.animation.CountdownAnimation;
import arkanoid.animation.KeyPressStoppableAnimation;
import arkanoid.animation.PauseScreen;
import biuoop.KeyboardSensor;
import counters.Counter;
import arkanoid.sprites.SpriteCollection;
import arkanoid.collidables.Collidable;
import arkanoid.collidables.GameEnvironment;
import arkanoid.geometry.Point;
import arkanoid.listeners.BallRemover;
import arkanoid.listeners.BlockRemover;
import arkanoid.listeners.ScoreTrackingListener;
import arkanoid.shapedObjects.Ball;
import arkanoid.shapedObjects.Block;
import arkanoid.shapedObjects.Paddle;
import arkanoid.shapedObjects.Rectangle;
import arkanoid.sprites.Sprite;
import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;

/**
 * Arkanoid.Game.Game has a Arkanoid.Sprites.SpriteCollection,
 * Arkanoid.Collidables.GameEnvironment and a gui as it holds the information about the game,
 * creates it and runs it.
 * @author Yossi Maatook.
 */
public class GameLevel implements Animation {
    public static final int FRAME_WIDTH = 800;
    public static final int FRAME_HEIGHT = 600;
    public static final int SCORE_BLOCK_HEIGHT = 15;
    public static final int FRAME_BLOCK_WIDTH_HEIGHT = 20;
    public static final int DEATH_BLOCK_X = -200;
    public static final int PADDLE_HEIGHT = 20;
    public static final int ORIGIN_AXIS = 0;
    public static final int BALL_RADIUS = 4;
    public static final int BALL_X = 405;
    public static final int BALL_Y = 555;
    public static final int LEVEL_UP_SCORE = 100;

    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Counter remainingBlocks;
    private Counter remainingBalls;
    private Counter score;
    private AnimationRunner runner;
    private boolean running;
    private KeyboardSensor keyboard;
    private LevelInformation currentLevel;
    private ArrayList<Ball> ballsList;
    private Paddle paddle;
    private Counter lives;

    /**
     * Constructor.
     * @param r - the AnimationRunner of the level.
     * @param k - the KeyboardSensor of the level.
     * @param levelInformation - the current level information.
     * @param s - the score Counter of the level.
     * @param l - the player lives.
     */
    public GameLevel(LevelInformation levelInformation, KeyboardSensor k, AnimationRunner r, Counter s, Counter l) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();

        //Set amount of remainingBlocks according to the number of blocks in the current level//
        this.remainingBlocks = new Counter(levelInformation.numberOfBlocksToRemove());
        //Set amount of remainingBalls according to the number of balls in the current level//
        this.remainingBalls = new Counter(levelInformation.numberOfBalls());
        this.score = s;
        this.runner = r;
        this.keyboard = k;
        this.currentLevel = levelInformation;
        this.ballsList = new ArrayList<>();
        this.lives = l;
    }

    /**
     * Add the received ball to the balls list.
     * @param ball - the ball to be add.
     */
    public void addToBallsList(Ball ball) {
        this.ballsList.add(ball);
    }

    /**
     * Adds the received Arkanoid.Collidables.Collidable to the environment of current game.
     * @param c - the Arkanoid.Collidables.Collidable which we want to add the environment of the game.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Removes the received Arkanoid.Collidables.Collidable from the environment of current game.
     * @param c - the Arkanoid.Collidables.Collidable which we want to remove from the environment of the game.
     */
    public void removeCollidable(Collidable c) {
        this.environment.getCollidables().remove(c);
    }

    /**
     * Adds the received Arkanoid.Sprites.Sprite to the sprites of current game.
     * @param s - the Arkanoid.Sprites.Sprite which we want to add the sprites of the game.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Removes the received Arkanoid.Sprites.Sprite from the sprites of current game.
     * @param s - the Arkanoid.Sprites.Sprite which we want to remove from the sprites of the game.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * Returns the Arkanoid.Collidables.GameEnvironment of current game.
     * @return the Arkanoid.Collidables.GameEnvironment of current game.
     */
    public GameEnvironment getEnvironment() {
        return this.environment;
    }

    /**
     * Returns the Arkanoid.Sprites.SpriteCollection of current game.
     * @return the Arkanoid.Sprites.SpriteCollection of current game.
     */
    public SpriteCollection getSprites() {
        return this.sprites;
    }

    /**
     * Returns the remaining balls counter of the game.
     * @return remaining balls counter of the game.
     */
    public Counter getRemainingBalls() {
        return this.remainingBalls;
    }

    /**
     * Returns the remaining blocks counter of the game.
     * @return remaining blocks counter of the game.
     */
    public Counter getRemainingBlocks() {
        return this.remainingBlocks;
    }

    /**
     * Initialize a new game - creates the Blocks, Balls and Arkanoid.ShapedObjects.Paddle, and adds them to the game.
     */
    public void initialize() {
        this.currentLevel.getBackground().addToGame(this);
        initializeBlocks();
        initializeFrame();
        ScoreIndicator scoreIndicator = new ScoreIndicator(this.score);
        scoreIndicator.addToGame(this);
        NameIndicator nameIndicator = new NameIndicator(this.currentLevel.levelName());
        nameIndicator.addToGame(this);
        LivesIndicator livesIndicator = new LivesIndicator(this.lives);
        livesIndicator.addToGame(this);
    }

    /**
     * Initialize the frame blocks and adds them to the game.
     */
    private void initializeFrame() {
        Block scoreBlock = new Block(new Rectangle(new Point(ORIGIN_AXIS, SCORE_BLOCK_HEIGHT),
                FRAME_WIDTH, SCORE_BLOCK_HEIGHT), Color.white);
        Block top = new Block(new Rectangle(new Point(ORIGIN_AXIS, FRAME_BLOCK_WIDTH_HEIGHT + SCORE_BLOCK_HEIGHT),
                FRAME_WIDTH, FRAME_BLOCK_WIDTH_HEIGHT), Color.LIGHT_GRAY);
        Block bot = new Block(new Rectangle(new Point(DEATH_BLOCK_X, FRAME_HEIGHT + 2 * FRAME_BLOCK_WIDTH_HEIGHT),
                2 * FRAME_WIDTH, ORIGIN_AXIS + FRAME_BLOCK_WIDTH_HEIGHT), Color.LIGHT_GRAY);
        bot.addHitListener(new BallRemover(this, this.remainingBalls));
        Block left = new Block(new Rectangle(new Point(ORIGIN_AXIS, FRAME_HEIGHT),
                FRAME_BLOCK_WIDTH_HEIGHT, FRAME_HEIGHT - SCORE_BLOCK_HEIGHT - FRAME_BLOCK_WIDTH_HEIGHT),
                Color.LIGHT_GRAY);
        Block right = new Block(new Rectangle(new Point(FRAME_WIDTH - FRAME_BLOCK_WIDTH_HEIGHT,
                FRAME_HEIGHT),
                FRAME_BLOCK_WIDTH_HEIGHT, FRAME_HEIGHT - SCORE_BLOCK_HEIGHT), Color.LIGHT_GRAY);
        Block[] frame = {scoreBlock, top, bot, left, right};

        //Add all 4 blocks of frame and background to the game//
        Block.addArrayToGame(frame, this);
    }

    /**
     * Initialize the blocks and adds them to the game.
     */
    private void initializeBlocks() {
        BlockRemover remover = new BlockRemover(this, this.remainingBlocks);
        ScoreTrackingListener trackingListener = new ScoreTrackingListener(this.score);

        for (Block b:this.currentLevel.blocks()) {
            b.addHitListener(remover);
            b.addHitListener(trackingListener);
            b.addToGame(this);
        }
    }

    /**
     * Initialize the paddle and balls, and adds them to the game.
     */
    private void initializePaddleAndBalls() {
        Paddle p = new Paddle(new Rectangle(new Point(this.currentLevel.paddleAxisX(),
                this.currentLevel.paddleAxisY()), this.currentLevel.paddleWidth(),
                PADDLE_HEIGHT), Color.YELLOW, this.keyboard, this.currentLevel.paddleSpeed(), this.ballsList);
        p.addToGame(this);
        this.paddle = p;

        for (int i = 0; i < this.currentLevel.numberOfBalls(); i++) {
            Ball ball = new Ball(new Point(BALL_X, BALL_Y),
                    BALL_RADIUS, Color.white, this.environment);
            ball.setVelocity(this.currentLevel.initialBallVelocities().get(i));
            ball.addToGame(this);
        }
    }

    /**
     * This method run the game(start the animation loop).
     */
    public void run() {
        initializePaddleAndBalls();
        this.runner.run(new CountdownAnimation(this.sprites));
        this.running = true;
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.runner.run(this);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if ((this.remainingBlocks.getValue() == 0)) {
            this.score.increase(LEVEL_UP_SCORE);
            this.running = false;
        }
        if (this.remainingBalls.getValue() == 0) {
            this.running = false;
        }
        if (this.keyboard.isPressed("p") || this.keyboard.isPressed("P")) {
            this.runner.run(new KeyPressStoppableAnimation(new PauseScreen(), this.keyboard, "space") {
            });
        }
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * Reset the remaining ball's counter to the received integer.
     * @param balls - the new number of balls.
     */
    public void resetRemainingBalls(int balls) {
        this.remainingBalls.increase(balls);
    }

    /**
     * Getter of the game paddle.
     * @return the game paddle.
     */
    public Paddle getPaddle() {
        return this.paddle;
    }
}