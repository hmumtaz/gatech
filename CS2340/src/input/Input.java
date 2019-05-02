package input;

import java.util.Stack;
import java.util.List;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;

/**
 * Input implementation.
 */
public class Input implements IInput {

    /**
     * The input provider.
     */
    private IInputProvider provider = null;

    /**
     * The frames.
     */
    private Stack<Map<Key, Set<Runnable>>> frames = new Stack<Map<Key, Set<Runnable>>>();

    /**
     * Constructor.
     */
    public Input() {
        this.frames.push(new HashMap<Key, Set<Runnable>>());
    }

    /**
     * Set the input provider for this input.
     *
     * @param provider The input provider.
     */
    public void setInputProvider(IInputProvider provider) {
        this.provider = provider;
        this.provider.provideInput(k -> {
            if (k == Key.SAVE) {
                game.Manager.getGame().saveGame();
                return;
            }

            if (this.frames.peek().get(k) != null) {
                for (Runnable handler: this.frames.peek().get(k)) {
                    handler.run();
                }
            }
        });
    }

    /**
     * Pushes a new input frame.
     *
     * @return The frame number of the new frame.
     */
    public int pushFrame() {
        this.frames.push(new HashMap<Key, Set<Runnable>>());

        return this.peekFrame();
    }

    /**
     * Pops an input frame. Popping frame 0 results in frame 0, but clears
     * every input handler.
     *
     * @return The frame number of the removed frame.
     */
    public int popFrame() {
        this.frames.pop();

        if (this.peekFrame() == -1) {
            this.frames.push(new HashMap<Key, Set<Runnable>>());
        }

        return this.peekFrame();
    }

    /**
     * Gets the current input frame number.
     *
     * @return The frame number of the current frame.
     */
    public int peekFrame() {
        return this.frames.size() - 1;
    }

    /**
     * Registers a key handler.
     *
     * @param key The input key being registered.
     * @param handler What runs after the input key is pressed.
     */
    public void registerKeyHandler(Key key, Runnable handler) {
        Map<Key, Set<Runnable>> frame = this.frames.peek();
        if (frame.get(key) == null) {
            frame.put(key, new HashSet<Runnable>());
        }

        frame.get(key).add(handler);
    }

    /**
     * Removes a key handler.
     *
     * @param key The keyboard key of the task to remove.
     * @param handler The Runnable being removed.
     */
    public void deregisterKeyHandler(Key key, Runnable handler) {
        for (Map<Key, Set<Runnable>> frame: this.frames) {
            if (frame.get(key) != null) {
                frame.get(key).remove(handler);
            }
        }
    }
}
