package graphics;

import javafx.scene.Scene;

/**
 * Represents something that can be rendered to a scene using the graphics subsystem.
 *
 * @author Joshua Songy
 * @version 1.0
 */
public interface IView {

    /**
     * Asks the view to render a scene root.
     *
     * @return The rendered scene.
     */
    public Scene render();

    /**
     * Represents a title that will be set on the window. If this returns null,
     * then the title is not changed.
     *
     * @return The title of the window.
     */
    public String getTitle();

    /**
     * Represents whether this view can be resized.
     *
     * @return Whether this view can be resized.
     */
    public default boolean resizeable() {
        return false;
    }
}
