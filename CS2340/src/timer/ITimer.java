package timer;

/**
 * Represents a timer class that is given a set amount of time and a few methods
 * for polling its current state.
 *
 * @author Joshua Songy
 * @version 1.0
 */
public interface ITimer {

    /**
     * A static representing unlimitted time.
     */
    public static final int INDEFINITE = -1;

    /**
     * A static representing one second.
     */
    public static final int SECOND = 1000;

    /**
     * A static representing one minute.
     */
    public static final int MINUTE = 1000 * 60;

    /**
     * A static representing one hour.
     */
    public static final int HOUR = 1000 * 60 * 60;

    /**
     * A static representing one day.
     */
    public static final int DAY = 1000 * 60 * 60 * 24;

    /**
     * Returns the remaining time, in milliseconds.
     *
     * @return the remaining time.
     */
    int getRemainingTime();

    /**
     * Returns the elapsed time, in milliseconds.
     *
     * @return the elapsed time.
     */
    int getElapsedTime();

    /**
     * Returns whether this timer has finished.
     *
     * @return whether this timer has finished.
     */
    boolean isFinished();

    /**
     * Registers a timeout to execute every given milliseconds until the timer
     * finishes, or a given number of times.
     *
     * @param duration The duration to execute the timeout after.
     * @param count The number of times to execute the timeout. INDEFINITE
     *              means unlimitted number of times.
     * @param timeout The timeout to execute.
     */
    void registerTimeout(int duration, int count, Runnable timeout);
}
