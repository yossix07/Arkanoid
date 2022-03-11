package arkanoid.shapedObjects;
import arkanoid.game.levels.GameLevel;
import arkanoid.geometry.Point;
import arkanoid.geometry.Velocity;
import arkanoid.sprites.Sprite;
import arkanoid.collidables.Collidable;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Color;
import java.util.ArrayList;

/**
 * Arkanoid.ShapedObjects.Paddle is a rectangle with color which able to move using the keyboard arrows.
 * @author Yossi Maatook.
 */
public class Paddle implements Sprite, Collidable {
    public static final int PADDLE_PARTS = 5;
    public static final int PADDLE_MIDDLE_PART = 2;
    public static final int FIRST_FIFTH_ANGLE = 300;
    public static final int CHANGE_DIRECTION = -1;
    public static final int ANGLES_DIFFERENCES = 30;
    public static final int BALL_FREE = -1;
    private Rectangle rectangle;
    private Color color;
    private biuoop.KeyboardSensor keyboard;
    private ArrayList<Ball> balls;
    private int speed;

    /**
     * Constructor.
     * @param rectangle - the rectangle of the paddle.
     * @param color - the color of the paddle.
     * @param keyboard - the sensor of the keyboard.
     * @param s - paddle speed.
     * @param b - the game ball's list.
     */
    public Paddle(Rectangle rectangle, Color color, biuoop.KeyboardSensor keyboard, int s, ArrayList<Ball> b) {
        this.rectangle = rectangle;
        this.color = color;
        this.keyboard = keyboard;
        this.balls = b;
        this.speed = s;
    }

    /**
     * Moves the paddle left using the left arrow on the keyboard.
     */
    public void moveLeft() {
        double newX = this.rectangle.getUpperLeft().getX() - speed;

        //Limit the paddle to frame left boarder//
        if (newX < GameLevel.FRAME_BLOCK_WIDTH_HEIGHT) {
            newX = GameLevel.FRAME_BLOCK_WIDTH_HEIGHT;
        }
        this.rectangle = new Rectangle(newX, this.rectangle.getUpperLeft().getY(),
                this.rectangle.getWidth(), this.rectangle.getHeight());;
    }

    /**
     * Moves the paddle right using the  right arrow on the keyboard.
     */
    public void moveRight() {
        double newX = this.rectangle.getUpperLeft().getX() + speed;

        //Limit the paddle to frame right boarder//
        if (newX + this.rectangle.getWidth() > GameLevel.FRAME_WIDTH - GameLevel.FRAME_BLOCK_WIDTH_HEIGHT) {
            newX = GameLevel.FRAME_WIDTH - GameLevel.FRAME_BLOCK_WIDTH_HEIGHT - this.rectangle.getWidth();
        }
        this.rectangle = new Rectangle(newX, this.rectangle.getUpperLeft().getY(),
                this.rectangle.getWidth(), this.rectangle.getHeight());
    }

    /**
     * Moves the paddle to the right or left according to which arrow the user click on the keyboard and clear
     * the paddle from balls(if there is balls in it).
     */
    public void timePassed() {
        boolean movedRight;
        if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
            movedRight = false;
            clearBallsFromPaddle(movedRight);
        }
        if (this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
            movedRight = true;
            clearBallsFromPaddle(movedRight);
        }
    }

    /**
     * Checks if any of the balls in the game are in the paddle, change them to be outside of it.
     * @param movedRight - is true if the paddle moved right and false if moved left.
     */
    private void clearBallsFromPaddle(boolean movedRight) {
        InsideObjects insideObjects = new InsideObjects();

        //If a ball is in the paddle, get it out//
        for (Ball ball:this.balls) {
            if (insideObjects.isBallInRectangle(ball, this.rectangle)) {
                moveBallOutside(ball, movedRight);
                ball.setVelocity(ball.getVelocity().getDx() * CHANGE_DIRECTION, ball.getVelocity().getDy());
            }
        }
    }

    /**
     * Receives a ball that is trapped inside the paddle and a boolean - movedRight.
     * Moves the ball outside of the paddle.
     * @param ball - the ball which we want to move from inside the paddle.
     * @param movedRight - true if the paddle moved right and false if moved left. Used in order to
     * know where to get the ball out.
     */
    private void moveBallOutside(Ball ball, boolean movedRight) {

        //In case the paddle moved left, move the ball to it's left//
        if (!movedRight) {
            ball.setCenter(this.rectangle.getUpperLeft().getX() - ball.getSize(), ball.getY());
        } else {
            ball.setCenter(this.rectangle.getUpperLeft().getX() + this.rectangle.getWidth() + ball.getSize(),
                    ball.getY());
        }

        InsideObjects insideObjects = new InsideObjects();
        int index = insideObjects.isPointInAnyRectangle(ball.getCenter(), ball.getGameEnvironment());
        Velocity origin = ball.getVelocity();

        //In case the ball is the paddle, move it out of it//
        if (index != BALL_FREE) {
            ball.setVelocity(CHANGE_DIRECTION * ball.getVelocity().getDx(),
                    CHANGE_DIRECTION * Math.abs(ball.getVelocity().getDy()));

            //Move the ball at the opposite direction until it is out//
            while (index != BALL_FREE) {
                ball.setCenter(ball.getVelocity().applyToPoint(ball.getCenter()));
                index = insideObjects.isPointInAnyRectangle(ball.getCenter(), ball.getGameEnvironment());
            }
        }
        //Change the velocity back to the original//
        ball.setVelocity(origin);
    }

    /**
     * Draw the paddle on the received DrawSurface.
     * @param surface - the DrawSurface on which we want to draw the paddle.
     */
    public void drawOn(DrawSurface surface) {
        this.rectangle.drawOn(surface, this.rectangle, this.color);
    }

    /**
     * Return the paddle's rectangle.
     * @return the paddle's rectangle.
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double fifthOfPaddleLength = this.rectangle.getWidth() / PADDLE_PARTS;

        for (int i = 0, angle = FIRST_FIFTH_ANGLE; i < PADDLE_PARTS; i++) {
            if (collisionPoint.getX() > this.rectangle.getUpperLeft().getX() + i * fifthOfPaddleLength
                    && collisionPoint.getX() < this.rectangle.getUpperLeft().getX() + (i + 1) * fifthOfPaddleLength) {
                if (i == PADDLE_MIDDLE_PART) {
                    return new Velocity(currentVelocity.getDx(), currentVelocity.getDy() * CHANGE_DIRECTION);
                }
                return Velocity.fromAngleAndSpeed(angle, currentVelocity.getSpeed());
            }
            angle = angle + ANGLES_DIFFERENCES;
        }
        return new Velocity(CHANGE_DIRECTION * currentVelocity.getDx(), currentVelocity.getDy());
    }

    /**
     * Add current paddle to the received game.
     * @param g - the game which we want to add the paddle to.
     */
    public void addToGame(GameLevel g) {
        if (g == null) {
            throw new NullPointerException("Must Set Arkanoid.Game.Game First!");
        }

        g.getEnvironment().addCollidable(this);
        g.getSprites().addSprite(this);
    }

    /**
     * Remove current paddle to the received game.
     * @param g - the game which we want to remove the paddle from.
     */
    public void removeFromGame(GameLevel g) {
        if (g == null) {
            throw new NullPointerException("Must Set Arkanoid.Game.Game First!");
        }

        g.getEnvironment().getCollidables().remove(this);
        g.getSprites().removeSprite(this);
    }
}
