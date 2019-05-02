package graphics;

/**
 * Represents an application window.
 *
 * @author Joshua Songy
 * @version 1.0
 */
public interface IWindow {

    /**
     * Renders a view to this window.
     *
     * @param view The view to display to this window.
     */
    public void render(IView view);

    /**
     * Displays this window to the user.
     */
    public void display();

    /**
     * Whether this window is an input provider.
     *
     * @return Whether this class is an input provider.
     */
    public default boolean isInputProvider() {
        return false;
    }
}
