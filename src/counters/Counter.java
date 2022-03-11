package counters;

/**
 * Counters.Counter has a integer which can increase and decrease.
 * @author Yossi Maatook.
 */
public class Counter {
    private int count;

    /**
     * Contractor.
     * @param startingFrom - the starting value of the counter.
     */
    public Counter(int startingFrom) {
        this.count = startingFrom;
    }

    /**
     * Add number to current count.
     * @param number - the number which we want to add to the count.
     */
    public void increase(int number) {
        this.count = this.count + number;
    }

    /**
     * Subtract number to current count.
     * @param number - the number which we want to subtract from the count.
     */
    public void decrease(int number) {
        this.count = this.count - number;
    }

    /**
     * Get current count.
     * @return current count.
     */
    public int getValue() {
        return this.count;
    }
}