package input;

/**
 * Class representing an input system.
 */
public interface IInput {

    public static int BASE_FRAME = 0;

    /**
     * Set the input provider for this input.
     *
     * @param provider The input provider.
     */
    public void setInputProvider(IInputProvider provider);

    /**
     * Pushes a new input frame.
     *
     * @return Number of new frame.
     */
    public int pushFrame();

    /**
     * Pops an input frame. Popping frame 0 results in frame 0, but clears
     * every input handler.
     *
     * @return Number of removed frame.
     */
    public int popFrame();

    /**
     * Gets the current input frame number.
     *
     * @return current input frame number.
     */
    public int peekFrame();

    /**
     * Registers a key handler.
     *
     * @param key The input key
     * @param handler The method to run.
     */
    public void registerKeyHandler(Key key, Runnable handler);

    /**
     * Removes a key handler.
     *
     * @param key The input key.
     * @param handler The method to run.
     */
    public void deregisterKeyHandler(Key key, Runnable handler);

    /**
     * Clears the current input frame.
     */
    public default void clearFrame() {
        if (this.peekFrame() == BASE_FRAME) {
            this.popFrame();
        } else {
            this.popFrame();
            this.pushFrame();
        }
    }
}
