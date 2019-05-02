package main;

import javafx.application.Application;
import javafx.stage.Stage;

import game.IGame;

/**
 * This class is the main entry point of the MULE program. It is responsible
 * for spawning all needed children threads, then entering the main javafx
 * graphics thread (for purposes of creating stage objects and the like).
 *
 * @author Joshua Songy
 * @version 1.0
 * @see javafx.application.Application
 */
public class Main extends Application {

    /**
     * The main entry point of the application, this gets called by javafx.
     *
     * @param stage The main application window.
     */
    @Override
    public void start(Stage stage) {
        IGame g = game.Manager.getGame();
        g.provideStage(stage);
        g.start();
    }

    /**
     * Entry point of java, this dispatches to the javafx entry point, and
     * should usually not be called.
     *
     * @param args The program arguments.
     */
    public static void main(String[] args) {
        Application.launch(args);
    }
}
