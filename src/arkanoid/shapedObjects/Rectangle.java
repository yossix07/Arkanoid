package arkanoid.shapedObjects;
import arrays.ArrayAction;
import arkanoid.geometry.Line;
import arkanoid.geometry.Point;
import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;


/**
 * Shapes.Rectangle has upper left point, width and height.
 * @author Yossi Maatook.
 */
public class Rectangle {
    public static final int RECTANGLE_NUM_OF_SIDES = 4;

    private Point upperLeft;
    private double width;
    private double height;

    /**
     * Sets the rectangle fields - Create a new rectangle with location and width/height.
     * @param upperLeft - upper left point of the rectangle.
     * @param width - width of the rectangle.
     * @param height - height of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * Sets the rectangle fields - Create a new rectangle with location and width/height.
     * @param x - x axis upper left point of the rectangle.
     * @param y - y axis upper left point of the rectangle.
     * @param width - width of the rectangle.
     * @param height - height of the rectangle.
     */
    public Rectangle(double x, double y, double width, double height) {
        this(new Point(x, y), width, height);
    }

    /**
     *  Return a (possibly empty) List of intersection points of current rectangle with received line.
     * @param line - line which we want to know where intersect with current rectangle.
     * @return list of points.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        java.util.List<Point> pointsList = new ArrayList<Point>();

        Line topOfRectangle = this.getUpperSideLine();
        Line bottomOfRectangle = this.getLowerSideLine();
        Line leftOfRectangle = this.getLeftSideLine();
        Line rightOfRectangle = this.getRightSideLine();

        //In case the line and one of the sides of the rectangle are crossing lines there are no intersections//
        if (line.isCrossingLines(topOfRectangle) || line.isCrossingLines(bottomOfRectangle)
                || line.isCrossingLines(leftOfRectangle) || line.isCrossingLines(rightOfRectangle)) {
            return pointsList;
        }
        Line[] rectangleSides = {topOfRectangle, bottomOfRectangle, leftOfRectangle, rightOfRectangle};

        //Adds the intersection(if exist) to the list//
        for (int i = 0; i < RECTANGLE_NUM_OF_SIDES; i++) {
            if (line.isIntersecting(rectangleSides[i])) {
                pointsList.add(line.intersectionWith(rectangleSides[i]));
            }
        }
        ArrayAction a = new ArrayAction();

        //Return the list after removing duplicates//
        return a.removeDuplicates(pointsList);
    }

    /**
     * Draw the rectangle on received DrawSurface in the received color.
     * @param surface - the DrawSurface which we want to draw on.
     * @param rectangle - the rectangle which we want to draw.
     * @param color - the color of the rectangle.
     */
    public void drawOn(DrawSurface surface, Rectangle rectangle, Color color) {
        surface.setColor(Color.BLACK);
        surface.drawRectangle((int) rectangle.getUpperLeft().getX(),
                (int) (rectangle.getUpperLeft().getY() - rectangle.getHeight()),
                (int) rectangle.getWidth(), (int) rectangle.getHeight());
        surface.setColor(color);
        surface.fillRectangle((int) rectangle.getUpperLeft().getX(),
                (int) (rectangle.getUpperLeft().getY() - rectangle.getHeight()),
                (int) rectangle.getWidth(), (int) rectangle.getHeight());
    }

    /**
     * Return the rectangle width.
     * @return rectangle's width.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Return the rectangle height.
     * @return rectangle's height.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Return the rectangle upper left point.
     * @return rectangle's upper left point..
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * Return the rectangle upper side line.
     * @return the rectangle's upper side line.
     */
    public Line getUpperSideLine() {
        return new Line(this.upperLeft.getX(), this.upperLeft.getY(),
                this.upperLeft.getX() + this.width, this.upperLeft.getY());
    }

    /**
     * Return the rectangle bottom side line.
     * @return the rectangle's bottom side line.
     */
    public Line getLowerSideLine() {
        return new Line(this.upperLeft.getX(), this.upperLeft.getY() - this.height,
                this.upperLeft.getX() + this.width, this.upperLeft.getY() - this.height);
    }

    /**
     * Return the rectangle left side line.
     * @return the rectangle's left side line.
     */
    public Line getLeftSideLine() {
        return new Line(this.upperLeft.getX(), this.upperLeft.getY(),
                this.upperLeft.getX(), this.upperLeft.getY() - this.height);
    }

    /**
     * Return the rectangle right side line.
     * @return the rectangle's right side line.
     */
    public Line getRightSideLine() {
        return new Line(this.upperLeft.getX() + this.width, this.upperLeft.getY(),
                this.upperLeft.getX() + this.width, this.upperLeft.getY() - this.height);
    }
}