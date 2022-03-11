package arkanoid.listeners;

/**
 * Arkanoid.Listeners.HitNotifier can and add and remove Arkanoid.Listeners.HitListener to the list of listeners.
 */
public interface HitNotifier {
    /**
     * Add hl as a listener to hit events.
      * @param hl - the listener which we want to add.
     */
    void addHitListener(HitListener hl);

    /**
     * Remove hl from the list of listeners to hit events.
     * @param hl - the listener we want to remove.
     */
    void removeHitListener(HitListener hl);
}