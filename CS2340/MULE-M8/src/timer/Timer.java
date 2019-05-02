package timer;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import java.util.List;
import java.util.ArrayList;

/**
 * A javafx implementation of timer that executes a callback when it has
 * finished its time.
 *
 * @author Joshua Songy
 * @version 1.0
 */
public class Timer implements ITimer {

    /**
     * The remaining time.
     */
    private int remaining;

    /**
     * The elapsed time.
     */
    private int elapsed = 0;

    /**
     * Holds the timeline we are using to implement our timer.
     */
    private Timeline timeline;

    /**
     * The timeout to execute when this timer is finished.
     */
    private final Runnable timeout;

    /**
     * List of sub-timers used for registered timeouts.
     */
    private final List<Timer> timeouts = new ArrayList<Timer>();

    /**
     * The constructor for this timer.
     *
     * @param duration How long this timer should run for.
     * @param timeout The timeout callback function to execute when this timer
     *                expires.
     */
    public Timer(int duration, Runnable timeout) {
        this.remaining = duration;
        this.timeout = timeout;

        // Javafx timeline is basically a timer.
        this.timeline = new Timeline(
            new KeyFrame(
                // We want 1 millisecond granularity.
                Duration.millis(1),
                event -> {
                    // Call cycle every event.
                    this.cycle();
                }));

        timeline.setCycleCount(timeline.INDEFINITE);
        timeline.play();
    }

    /**
     * The constructor for this timer.
     *
     * @param duration How long this timer should run for.
     */
    public Timer(int duration) {
        this(duration, null);
    }

    /**
     * The constructor for this timer.
     *
     * @param timeout The timeout callback function to execute when this timer
     *                expires.
     */
    public Timer(Runnable timeout) {
        this(ITimer.INDEFINITE, timeout);
    }

    /**
     * The constructor for this timer.
     */
    public Timer() {
        this(ITimer.INDEFINITE, null);
    }

    /**
     * Called on every tick of this timer.
     */
    private synchronized void cycle() {
        this.elapsed++;
        if (this.remaining != ITimer.INDEFINITE && --this.remaining <= 0) {
            this.timeline.stop();
            if (this.remaining == 0 && this.timeout != null) {
                this.timeout.run();
            }
        }
        if (this.remaining > 0 && this.remaining % ITimer.SECOND == 0) {
            System.out.println("Remaining: " + this.remaining / ITimer.SECOND);
        }
    }

    /**
     * Returns the remaining time, in milliseconds.
     *
     * @return the remaining time.
     */
    public synchronized int getRemainingTime() {
        return this.remaining;
    }

    /**
     * Manually set remaining time to an integer
     * @param n time to change to
     */
    public synchronized void setRemainingTime(int n) {
        this.remaining = n;
        this.timeline.play();
    }

    /**
     * Returns the elapsed time, in milliseconds.
     *
     * @return the elapsed time.
     */
    public synchronized int getElapsedTime() {
        return this.elapsed;
    }

    /*
     * Pause the timer.
     */
    public void pause() {
        timeline.pause();
    }

    /*
     * Resume paused timer.
     */
    public void play() {
        timeline.play();
    }

    /**
     * Returns whether this timer has finished.
     *
     * @return whether this timer has finished.
     */
    public synchronized boolean isFinished() {
        return this.remaining == 0;
    }

    /**
     * Registers a timeout to execute every given milliseconds until the timer
     * finishes, or a given number of times.
     *
     * @param duration The duration to execute the timeout after.
     * @param count The number of times to execute the timeout. INDEFINITE
     *              means unlimitted number of times.
     * @param timeout The timeout to execute.
     */
    public synchronized void registerTimeout(int duration, int count, Runnable timeout) {
        if (count == 0) {
            return;
        }

        final TimerWrapper timer = new TimerWrapper();
        timer.setTimer(new Timer(duration, () -> {
            this.timeouts.remove(timer.getTimer());

            if (this.isFinished()) {
                return;
            }

            this.registerTimeout(duration, count == ITimer.INDEFINITE ? count : count - 1, timeout);

            timeout.run();
        }));

        this.timeouts.add(timer.getTimer());
    }

    /**
     * Wrapper class used by registerTimeout to get around java closure issues.
     *
     * @author Joshua Songy
     * @version 1.0
     */
    private class TimerWrapper {

        /**
         * Holds a timer instance.
         */
        private Timer timer = null;

        /**
         * Sets the timer
         *
         * @param timer The new timer.
         */
        public void setTimer(Timer timer) {
            this.timer = timer;
        }

        /**
         * Gets the timer.
         *
         * @return The timer.
         */
        public Timer getTimer() {
            return this.timer;
        }
    }
}
