package arkanoid.geometry;
import arkanoid.shapedObjects.Rectangle;

import java.util.Random;

/**
 * Arkanoid.Gui.Line has start point, end point, slope, constant and a boolean isVertical.
 * The line is described as the following: y=slope*x + constant.
 *
 * @author Yossi Maatook.
 */
public class Line {
    public static final int FIRST_INDEX = 0;
    public static final int SECOND_INDEX = 1;
    public static final int ONE_MEMBER = 1;

    private Point start;
    private Point end;
    private Slope slope;
    private double constant;
    private boolean isVertical;

    /**
     * The method receives the start and ending point of the
     * line and sets all the fields of current line accordingly.
     *
     * @param start - starting point of the line
     * @param end   - ending point of the line
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;

        //In case line is vertical//
        if (this.end.getX() == this.start.getX()) {
            this.slope = null;
            this.constant = this.start.getX();
            this.isVertical = true;
            return;
        }

        this.slope = new Slope(((this.end.getY() - this.start.getY()) / (this.end.getX() - this.start.getX())));
        this.constant = this.start.getY() - this.slope.getVal() * this.start.getX();
        this.isVertical = false;
    }

    /**
     * The method receives 2 x positions and 2 y positions and sets them as the starting point
     * and ending point of current line, and sets all other fields accordingly.
     *
     * @param x1 - x position of the starting point.
     * @param y1 - y position of the starting point.
     * @param x2 - x position of the ending point.
     * @param y2 - y position of the ending point.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this(new Point(x1, y1), new Point(x2, y2));
    }

    /**
     * Returns the current line slope.
     *
     * @return this.slope - the current line's slope.
     */
    public Slope getSlope() {
        return this.slope;
    }

    /**
     * Returns the current line constant.
     *
     * @return this.constant - the current line's constant.
     */
    public double getConstant() {
        return this.constant;
    }

    /**
     * Returns the current line length(distance between starting point and ending point).
     *
     * @return this.start.distance(this.end) - the current line's length.
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * Returns the current line middle point.
     *
     * @return middlePoint - the current line's middle point.
     */
    public Point middle() {
        double middleX = (this.start.getX() + this.end.getX()) / 2;
        double middleY = (this.start.getY() + this.end.getY()) / 2;
        return new Point(middleX, middleY);
    }

    /**
     * Returns the current line starting point.
     *
     * @return this.start - the current line's starting point.
     */
    public Point start() {
        return this.start;
    }

    /**
     * Returns the current line ending point.
     *
     * @return this.end - the current line's ending point.
     */
    public Point end() {
        return this.end;
    }

    /**
     * Returns the true if the line is vertical and false otherwise.
     *
     * @return true if the line is vertical and false otherwise.
     */
    public boolean getIsVertical() {
        return this.isVertical;
    }

    /**
     * Returns true if the line is a horizontal line and false otherwise.
     * @return true if the line is a horizontal line and false otherwise.
     */
    public boolean isHorizontal() {
        if (this.start.getY() == this.end.getY()) {
            return true;
        }
        return false;
    }

    /**
     * The method returns true if the current line and the received line is intersecting and false otherwise.
     *
     * @param other - the line which we want to know if it's is intersecting with the current line.
     * @return true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        if (other == null) {
            return false;
        }

        //In case one line is a dot or both lines are dots//
        if ((this.start.equals(this.end) && ((this.start.equals(other.start()) && this.start.equals(other.end()))
                || (isOnLine(this.start, other)) || ((other.start().equals(other.end())
                && isOnLine(other.start(), this)))))
                || (other.start().equals(other.end()) && isOnLine(other.start(), this))) {
            return true;
        }

        /*
        In case the starting point of current line is equals to the starting point of the
        other line, checks if there is another common point between the lines.
         */
        if (this.start.equals(other.start()) && ((this.isVertical && other.getIsVertical())
                || (!this.isVertical && !other.getIsVertical() && this.slope.getVal() == other.getSlope().getVal()))) {

            if (this.isHorizontal() && other.isHorizontal()) {
                return isCommonPointHorizontal(this.end, this.start, other.end(), other.start());
            }
            return isCommonPoint(this.end, this.start, other.end(), other.start());
        }

        /*
        In case the starting point of current line is equals to the ending point of the
        other line, checks if there is another common point between the lines.
         */
        if (this.start.equals(other.end()) && ((this.isVertical && other.getIsVertical())
                || (!this.isVertical && !other.getIsVertical() && this.slope.getVal() == other.getSlope().getVal()))) {

            if (this.isHorizontal() && other.isHorizontal()) {
                return isCommonPointHorizontal(this.end, this.start, other.start(), other.end());
            }
            return isCommonPoint(this.end, this.start, other.start(), other.end());
        }

        /*
        In case the ending point of current line is equals to the starting point of the
        other line, checks if there is another common point between the lines.
         */
        if (this.end.equals(other.start()) && ((this.isVertical && other.getIsVertical())
                || (!this.isVertical && !other.getIsVertical() && this.slope.getVal() == other.getSlope().getVal()))) {

            if (this.isHorizontal() && other.isHorizontal()) {
                return isCommonPointHorizontal(this.start, this.end, other.end(), other.start());
            }
            return isCommonPoint(this.start, this.end, other.end(), other.start());
        }

        /*
        In case the ending point of current line is equals to the ending point of the
        other line, checks if there is another common point between the lines.
         */
        if (this.end.equals(other.end()) && ((this.isVertical && other.getIsVertical())
                || (!this.isVertical && !other.getIsVertical() && this.slope.getVal() == other.getSlope().getVal()))) {

            if (this.isHorizontal() && other.isHorizontal()) {
                return isCommonPointHorizontal(this.start, this.end, other.start(), other.end());
            }
            return isCommonPoint(this.start, this.end, other.start(), other.end());
        }

        /*
        In case the lines are parallel or on the same big line,
        but has more than one common point or zero common points.
        */
        if ((this.isVertical && other.getIsVertical())
                || (!this.isVertical && !other.getIsVertical() && this.slope.getVal() == other.getSlope().getVal())) {
            return false;
        }

        /*
        In case both lines aren't vertical, calculates the intersection point and check if it is on both
        lines segments.
         */
        if (!this.isVertical && !other.getIsVertical()) {

            //X position of the intersection point//
            double intersectX = (this.constant - other.getConstant())
                    / (other.getSlope().getVal() - this.slope.getVal());

            //Y position of the intersection point//
            double intersectY = this.slope.getVal() * intersectX + this.constant;
            Point intersectionPoint = new Point(intersectX, intersectY);

            //Returns true if the intersection point is on both lines segments and false otherwise//
            return (isOnSegment(intersectionPoint, this) && isOnSegment(intersectionPoint, other));
        }

        Point intersectionPoint = null;
        //Get the intersection point if only one line is vertical//
        if (this.isVertical) {
            intersectionPoint = whereVerticalIntersectLine(this, other);
        } else {
            intersectionPoint = whereVerticalIntersectLine(other, this);
        }

        //Returns true if the intersection point is on both lines segments and false otherwise//
        return (isOnSegment(intersectionPoint, this) && isOnSegment(intersectionPoint, other));
    }

    /**
     * The method receives 4 points that forms 2 lines.
     * It returns true if they have only 1 common point and false otherwise.
     *
     * @param pOne   - first point of first line.
     * @param pTwo   - second point of first line.
     * @param pThree - first point of second line.
     * @param pFour  - second point of second line.
     * @return true if they have only 1 common point and false otherwise.
     */
    private boolean isCommonPoint(Point pOne, Point pTwo, Point pThree, Point pFour) {

        if (pOne.getY() >= pTwo.getY() && pThree.getY() <= pFour.getY()) {
            return true;
        }
        if (pOne.getY() <= pTwo.getY() && pThree.getY() >= pFour.getY()) {
            return true;
        }
        return false;
    }

    /**
     * The method receives 4 points that forms 2 horizontal lines.
     * It returns true if they have only 1 common point and false otherwise.
     * @param pOne   - first point of first line.
     * @param pTwo   - second point of first line.
     * @param pThree - first point of second line.
     * @param pFour  - second point of second line.
     * @return true if they have only 1 common point and false otherwise.
     */
    private boolean isCommonPointHorizontal(Point pOne, Point pTwo, Point pThree, Point pFour) {
        if (pOne.getX() >= pTwo.getX() && pThree.getX() <= pFour.getX()) {
            return true;
        }
        if (pOne.getX() <= pTwo.getX() && pThree.getX() >= pFour.getX()) {
            return true;
        }
        return false;
    }

    /**
     * The method gets a line and a point which is on the line.
     * It returns true if the received point is on the received line segment and false otherwise.
     * @param point - the point which we want to know if is on the line segment.
     * @param line - the line which we want to know if the point is on.
     * @return true if the received point is on the current line segment and false otherwise.
     */
    public static boolean isOnSegment(Point point, Line line) {
        double epsilon = Math.pow(10, -12);

        /*
        In case the received intersection point's x location is between both lines segment's x range,
        and the received intersection point's y location is between both lines segment's y range, returns true.
         */
        if ((point.getX() >= Math.min(line.start().getX(), line.end().getX())
                || (Math.abs(Math.min(line.start().getX(), line.end().getX()) - point.getX()) <= epsilon))
                && (point.getX() <= Math.max(line.start().getX(), line.end().getX())
                || Math.abs(Math.max(line.start().getX(), line.end().getX()) - point.getX()) <= epsilon)
                && (point.getY() >= Math.min(line.start().getY(), line.end().getY())
                || Math.abs(Math.min(line.start().getY(), line.end().getY()) - point.getY()) <= epsilon)
                && (point.getY() <= Math.max(line.start().getY(), line.end().getY())
                || Math.abs(Math.max(line.start().getY(), line.end().getY()) - point.getY()) <= epsilon)) {
            return true;
        }
        return false;
    }

    /**
     * Returns true if the given point is on the given line.
     * Returns false otherwise.
     * @param point - the point which we want to know if on the line.
     * @param line - the line which we want to know if contains the point.
     * @return true if the point is on the line and false otherwise.
     */
    public static boolean isOnLine(Point point, Line line) {

        //In case the line is vertical, checks if the point is on the y axis range.
        if (line.getIsVertical()) {
            if (point.getY() > Math.min(line.start().getY(), line.end().getY())
                    && point.getY() < Math.max(line.start().getY(), line.end().getY())) {
                return true;
            }
            return false;
        }

        //In case the line is a non-vertical line//
        if (line.getSlope().getVal() * point.getX() + line.getConstant() == point.getY()) {
            return true;
        }
        return false;
    }

    /**
     * The method receives two lines: a vertical line and a liner(non-vertical) line.
     * It returns the intersection point between the lines.
     *
     * @param vertical - a vertical line with undefined slope.
     * @param linear   - a linear(non-vertical) line.
     * @return intersectionPoint - the intersection point between both lines.
     */
    private Point whereVerticalIntersectLine(Line vertical, Line linear) {
        double intersectY = linear.getSlope().getVal() * vertical.start().getX() + linear.getConstant();
        return new Point(vertical.start().getX(), intersectY);
    }

    /**
     * The method Returns the intersection point of the current line and the received line, if they intersect,
     * and null otherwise.
     *
     * @param other - the received line.
     * @return the intersection point if the lines intersect, and null otherwise.
     */
    public Point intersectionWith(Line other) {

        //In case the lines doesn't intersect - returns null//
        if (other == null || !isIntersecting(other)) {
            return null;
        }

        if (other.start().equals(other.end()) && isOnSegment(other.start(), this)) {
            return other.start();
        }

        /*
        In case the lines intersect and the starting point of the current line is the same as the starting or ending
        point of the other line, returns the starting point of current line.
         */
        if (this.start.equals(other.start()) || this.start.equals(other.end())
                || (this.start.equals(this.end) && isOnSegment(this.start, other))) {
            return this.start;
        }

        /*
        In case the lines intersect and the ending point of the current line is the same as the starting or ending
        point of the other line, returns the ending point of current line.
         */
        if (this.end.equals(other.start()) || this.end.equals(other.end())) {
            return this.end;
        }

        if (this.isVertical && other.getIsVertical()) {
            return null;
        }

        //In case both lines aren't vertical//
        if (!this.isVertical && !other.getIsVertical()) {
            double intersectX = (this.constant - other.getConstant())
                    / (other.getSlope().getVal() - this.slope.getVal());
            double intersectY = this.slope.getVal() * intersectX + this.constant;
            return new Point(intersectX, intersectY);
        }
        Point intersectionPoint = null;

        //In case one line is vertical//
        if (this.isVertical && !other.getIsVertical()) {
            intersectionPoint = whereVerticalIntersectLine(this, other);
        }
        if (!this.isVertical && other.getIsVertical()) {
            intersectionPoint = whereVerticalIntersectLine(other, this);
        }
        return intersectionPoint;
    }

    /**
     * Returns true if the current line and the received lines are equal and false otherwise.
     *
     * @param other - the received line.
     * @return true if the current line and the received lines are equal and false otherwise.
     */
    public boolean equals(Line other) {
        if ((this.start.equals(other.start()) && this.end.equals(other.end()))
                || this.start.equals(other.end()) && this.end.equals(other.start())) {
            return true;
        }
        return false;
    }

    /**
     * The method receives a width(x axis) and a height(y axis) and returns a random line within the given sizes.
     *
     * @param width  - the max width(x axis) of the wanted line.
     * @param height - the max height(y axis) of the wanted line.
     * @return a new line with random starting point and ending point within the given sizes.
     */
    public static Line generateRandomLine(int width, int height) {
        Random rand = new Random();
        int xOne = rand.nextInt(width);
        int yOne = rand.nextInt(height);
        int xTwo = rand.nextInt(width);
        int yTwo = rand.nextInt(height);

        return new Line(xOne, yOne, xTwo, yTwo);
    }

    /**
     * Return true if current line and the received one are crossing lines and false otherwise.
     * @param other - the line which we want to know if crossing with the current one.
     * @return true if the lines are crossing.
     */
    public boolean isCrossingLines(Line other) {

        //In case the lines Intersect or only one is vertical, they are not crossing lines//
        if (this.isIntersecting(other) || (this.isVertical && !other.getIsVertical())
                || (!this.isVertical && other.getIsVertical())) {
            return false;
        }

        double currentHigh = Math.max(this.start.getY(), this.end.getY());
        double currentLow = Math.min(this.start.getY(), this.end.getY());
        double otherHigh = Math.max(other.start().getY(), other.end().getY());
        double otherLow = Math.min(other.start().getY(), other.end().getY());

        //In case the lines has common parts, they are indeed crossing lines//
        if ((this.isVertical && other.getIsVertical() && this.start.getX() == other.start().getX())
                || (!this.isVertical && !other.getIsVertical()
                && this.slope.getVal() == other.getSlope().getVal() && this.constant == other.getConstant())) {
            if ((currentHigh >= otherHigh && otherHigh >= currentLow)
                    || (otherHigh >= currentHigh && currentHigh >= otherLow)) {
                return true;
            }
            return false;
        }
        return false;
    }


    /**
     * If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the start of the line.
     * @param rect - the rectangle which we look for closest intersection to start of current Arkanoid.Gui.Line.
     * @return If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the start of the line.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        java.util.List<Point> pointsList = rect.intersectionPoints(this);

        //In case list of intersection points is empty, there is no intersection//
        if (pointsList.isEmpty()) {
            return null;
        }

        //If the list is a one size list, return the only member in it//
        if (pointsList.size() == ONE_MEMBER) {
            return pointsList.get(FIRST_INDEX);
        }

        //In case there are two intersections, and the first one is the closest, return it//
        if (pointsList.get(FIRST_INDEX).distance(this.start) <= pointsList.get(SECOND_INDEX).distance(this.start)) {
            return pointsList.get(FIRST_INDEX);
        }
        return pointsList.get(SECOND_INDEX);
    }

    /**
     * The method returns the index of the point in the received points
     * array which is the closest to start of current line.
     * @param points - array of points.
     * @return the index of closest point to the start of crruent line.
     */
    public int whichPointIsTheClosestToStart(Point[] points) {
        double currentMinDistance = this.start.distance(points[FIRST_INDEX]);
        int currentMinIndex = FIRST_INDEX;

        for (int i = 1; i < points.length; i++) {
            if (this.start.distance(points[i]) < currentMinDistance) {
                currentMinDistance = this.start.distance(points[i]);
                currentMinIndex = i;
            }
        }
        return currentMinIndex;
    }
}