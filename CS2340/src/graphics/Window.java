package graphics;

import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import input.IInputProvider;

/**
 * Represents an application window.
 *
 * @author Joshua Songy
 * @version 1.0
 */
public class Window implements IWindow, IInputProvider {

    /**
     * The stage that is the window.
     */
    private Stage stage = null;

    /**
     * The scene we render to the scene,
     */
    private Scene scene = null;

    /**
     * The callback to use when the user types input.
     */
    private input.IInputProvider.KeyHandler callback = null;

    /**
     * Creates a new window.
     *
     * @param stage The stage that represents this window.
     */
    public Window(Stage stage) {
        this.stage = stage;
    }

    /**
     * Renders a view to this window.
     *
     * @param view The view to display to this window.
     */
    public void render(IView view) {
        this.scene = view.render();
        this.stage.setScene(this.scene);
        this.provide();
    }

    /**
     * Displays this window to the user.
     */
    public void display() {
        this.stage.show();
    }

    /**
     * Fetches the stage.
     *
     * @return The application stage.
     */
    public Stage getStage() {
        return stage;
    }

    /**
     * Provides input to the input subsystem.
     *
     * @param callback The callback to provide input to.
     */
    public void provideInput(input.IInputProvider.KeyHandler callback) {
        this.callback = callback;

        this.provide();
    }

    /**
     * Hooks up the input callback to the input source.
     */
    private void provide() {
        if (this.scene != null && this.callback != null) {

            this.scene.setOnKeyPressed(k -> {
                switch(k.getCode()) {
                    case ENTER:
                        this.callback.handle(input.Key.PRIMARY);
                        break;

                    case SPACE:
                        this.callback.handle(input.Key.SECONDARY);
                        break;

                    case UP:
                        this.callback.handle(input.Key.UP);
                        break;

                    case DOWN:
                        this.callback.handle(input.Key.DOWN);
                        break;

                    case LEFT:
                        this.callback.handle(input.Key.LEFT);
                        break;

                    case RIGHT:
                        this.callback.handle(input.Key.RIGHT);
                        break;
                    case S:
                        this.callback.handle(input.Key.SAVE);
                        break;
                }
            });

        } else if (this.scene != null) {
            this.scene.setOnKeyPressed(k -> {});
        }
    }

    /**
     * Whether this window is an input provider.
     *
     * @return Whether this class is an input provider.
     */
    public boolean isInputProvider() {
        return true;
    }
}
