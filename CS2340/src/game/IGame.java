package game;

import javafx.stage.Stage;
import timer.Turn;

public interface IGame {

    /**
     * Provides the stage to be used by the game.
     *
     * @param stage The stage to use.
     */
    public void provideStage(Stage stage);

    /**
     * Begins the game.
     */
    public void start();

    /**
     * Starts the rounds after land selection.
     */
    public void beginTurns();

    /**
     * Switches to the town screen.
     */
    public void enterTown();

    /**
     * Leaves the town, switching back to the map screen.
     */
    public void leaveTown();

    /**
     * Checks if the game is currently in the town screen.
     *
     * @return Whether in town.
     */
    public boolean inTown();

    /**
     * Retrieves the instance of Turn associated with this IGame.
     *
     * @return the Turn
     */
    public Turn getTurn();

    /**
     * Saves the game, creating a save.json file in the base directory.
     */
    public void saveGame();
}
