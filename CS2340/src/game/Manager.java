package game;

/**
 * Class that reprents the manager for the game.
 *
 * @author Joshua Songy
 * @version 1.0
 */
public class Manager {

    /**
     * The main window of this application.
     */
    private static IGame game = new Game();

    /**
     * This is a singleton manager, cannot be constructed.
     */
    private Manager() {};

    /**
     * Starts a new game.
     */
    public static void newGame() {
        Manager.game = new Game();
    }

    /**
     * Gets the current game object.
     *
     * @return The current game object.
     */
    public static IGame getGame() {
        return Manager.game;
    }
}
