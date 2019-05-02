package input;

import javafx.stage.Stage;

/**
 * Manages the global input instance.
 *
 * @author Joshua Songy
 * @version 1.0
 */
public class Manager {

    /**
     * The main input of this application.
     */
    private static final IInput input = new Input();

    /**
     * Private constructor to prevent new managers from being built.
     */
    private Manager() {};

    /**
     * Provides an IInputProvider to the input subsystem.
     *
     * @param inputProvider The IInputProvider
     */
    public static void setInputProvider(IInputProvider inputProvider) {
        if (Manager.input != null) {
            Manager.input.setInputProvider(inputProvider);
        }
    }

    /**
     * Returns the input provider for the application.
     *
     * @return The input provider.
     */
    public static IInput getInput() {
        return Manager.input;
    }
}
