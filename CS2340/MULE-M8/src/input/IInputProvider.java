package input;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

/**
 * Provides an input source to the input subsystem.
 *
 * @author Joshua Songy
 * @version 1.0
 */
public interface IInputProvider {

    /**
     * Provides input to the input subsystem.
     *
     * @param callback The callback to provide input to.
     */
    public void provideInput(KeyHandler callback);

    /**
     * Interface for providing a key.
     */
    public interface KeyHandler {
        public void handle(Key key) ;
    }
}
