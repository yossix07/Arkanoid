package arkanoid.shapedObjects;
import arkanoid.game.levels.GameLevel;
import arkanoid.geometry.Line;
import arkanoid.geometry.Point;
import arkanoid.geometry.Velocity;
import arkanoid.listeners.HitListener;
import arkanoid.listeners.HitNotifier;
import arkanoid.sprites.Sprite;
import arkanoid.collidables.Collidable;
import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;

/**
 * Arkanoid.ShapedObjects.Block is a rectangle with a color.
 * @author Yossi Maatook.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    public static final int NOT_MOVING = 0;
    public static final int CHANGE_DIRECTION = -1;
    private Rectangle rectangle;
    private Color color;
    private ArrayList<HitListener> hitListeners;

    /**
     * Sets the fields of the block.
     *
     * @param rectangle - the rectangle of the block.
     * @param color     - the color of the block.
     */
    public Block(Rectangle rectangle, Color color) {
        this.rectangle = rectangle;
        this.color = color;
        this.hitListeners = new ArrayList<>();
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();

        //In case the collision point is on the upper or lower side of the block, change the y axis direction//
        if ((Line.isOnSegment(collisionPoint, this.rectangle.getUpperSideLine()) && dy < NOT_MOVING)
                || (Line.isOnSegment(collisionPoint, this.rectangle.getLowerSideLine()) && dy > NOT_MOVING)) {
            dy = dy * CHANGE_DIRECTION;
        }

        //In case the collision point is on the right or left side of the block, change the x axis direction//
        if ((Line.isOnSegment(collisionPoint, this.rectangle.getRightSideLine()) && dx < NOT_MOVING)
                || (Line.isOnSegment(collisionPoint, this.rectangle.getLeftSideLine()) && dx > NOT_MOVING)) {
            dx = dx * CHANGE_DIRECTION;
        }
        this.notifyHit(hitter);
        return new Velocity(dx, dy);
    }

    @Override
    public void drawOn(DrawSurface surface) {
        this.rectangle.drawOn(surface, this.rectangle, this.color);
    }

    @Override
    public void timePassed() {
        return;
    }

    /**
     * Adds current block to the received Arkanoid.Game.Game.
     *
     * @param g - the Arkanoid.Game.Game which we want to add the block to.
     */
    public void addToGame(GameLevel g) {
        if (g == null) {
            throw new NullPointerException("Must Set Arkanoid.Game.Game First!");
        }

        //Add the block as a drawable object//
        g.getSprites().addSprite(this);

        //Add the block as a collidable object//
        g.getEnvironment().addCollidable(this);
    }

    /**
     * Removes current block from the received Arkanoid.Game.Game.
     *
     * @param gameLevel - the Arkanoid.Game.Game which we want to remove the block from.
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
        gameLevel.removeCollidable(this);
    }

    /**
     * This method adds a received array of blocks to the received game.
     *
     * @param array - array of blocks which we want to add.
     * @param g     - game which we want to add to.
     */
    public static void addArrayToGame(Block[] array, GameLevel g) {
        for (Block b : array) {
            b.addToGame(g);
        }
    }

    /**
     * The method receives a x and y locations, width, height, number of blocks and a color.
     * It returns a list of blocks, starting from the x and y received.
     * The number of blocks received, represents the number of blocks to be created in row.
     * Each block has the received width, height and color.
     *
     * @param x           - the starting x axis of the row of blocks.
     * @param y           - the y axis of the blocks row.
     * @param width       - width of every block.
     * @param numOfBlocks - number of blocks to be created.
     * @param color       - color of the blocks.
     * @param height      - height of every block.
     * @return a list of blocks as described.
     */
    public static ArrayList<Block> rowOfBlocksGenerator(int x, int y, int width, int height,
                                                        int numOfBlocks, Color color) {
        ArrayList<Block> list = new ArrayList<>();
        for (int i = 0; i < numOfBlocks; i++) {
            list.add(new Block(new Rectangle(new Point(x, y), width, height), color));
            x = x - width;
        }
        return list;
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * Add a received listener to each block in the received blocks array.
     * @param hl - the listener which we want to add.
     * @param blocks - array of block which we want to add the listener to.
     */
    public static void addHitListerToArray(HitListener hl, Block[] blocks) {

        //Adds the received listener to all blocks in the array//
        for (Block b:blocks) {
            b.addHitListener(hl);
        }
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * Notify to all the listeners that a hit has been occurred.
     * @param hitter - the hitter ball.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        ArrayList<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}
