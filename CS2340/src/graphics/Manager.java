package graphics;

import javafx.stage.Stage;
import input.IInputProvider;

/**
 * Class that reprents the manager for the graphics subsystem.
 *
 * @author Joshua Songy
 * @version 1.0
 */
public class Manager {

    /**
     * The main window of this application.
     */
    private static IWindow main = null;

    /**
     * Sets the main javafx stage.
     *
     * @param stage The main javafx stage.
     */
    public static void setMainStage(Stage stage) {
        Manager.main = new Window(stage);

        // If the stage is an input provider, provide input.
        // THIS IS KIND OF A HACK, FIXME.
        if (Manager.main.isInputProvider()) {
            IInputProvider provider = (IInputProvider) Manager.main;
            input.Manager.setInputProvider(provider);
        }
    }

    /**
     * Returns the main window object of the application.
     *
     * @return The main window object of the application.
     */
    public static IWindow getMainWindow() {
        return Manager.main;
    }

    /**
     * Creates a new javafx stage.
     *
     * @return A new javafx stage.
     */
    public static Stage newStage() {
        return new Stage();
    }
}
