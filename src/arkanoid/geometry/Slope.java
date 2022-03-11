package arkanoid.geometry;

/**
 * Arkanoid.Gui.Slope has it's val(as the line slope).
 *
 * @author Yossi Maatook.
 */
public class Slope {
    private double val;

    /**
     * Sets the value of current slope.
     * @param x - the value of current slope.
     */
    public Slope(double x) {
        this.val = x;
    }

    /**
     * Returns the value of the current slope.
     * @return the value of the current slope.
     */
    public double getVal() {
        return this.val;
    }
}
