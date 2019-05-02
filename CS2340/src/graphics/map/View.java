package graphics.map;

import java.util.List;
import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.input.KeyCode;
import javafx.scene.Node;
import input.IInput;

import map.Map;
import map.MapTile;
import map.LandGrant;
import player.Player;
import timer.Turn;
import town.Pub;
import javafx.scene.image.ImageView;
import resources.Manager;

/**
 * Graphical view for rendering a map.
 *
 * @author Joshua Songy
 * @version 1.2
 */
public class View implements graphics.IView {

    /**
     * The map to render.
     */
    private Map map;

    Pane root;

    /**
     * Sets up the view for rendering.
     *
     * @param map The map to render.
     */
    public View(Map map) {
        this.map = map;
        root = new StackPane();
    }

    /**
     * Returns the title of the configuration window.
     *
     * @return The title of the configuration window.
     */
    public String getTitle() {
        return "M.U.L.E.";
    }

    /**
     * Adds a tile to a pane at x, y.
     *
     * @param pane The pane.
     * @param tile The MapTile.
     * @param x The x coordinate.
     * @param y The y coordinate.
     */
    public void addTile(GridPane pane, MapTile tile, int x, int y) {
        pane.add(tile.render(), y, x);
    }

    /**
     * Returns the root pane of this view.
     *
     * @return The root pane of this view.
     */
    public Pane getRoot() {
        return root;
    }

    /**
     * Renders this view to a scene.
     *
     * @return The rendered scene
     */
    public Scene render() {
        GridPane pane = new GridPane();
        root.getChildren().add(pane);

        int height = this.map.height();
        int width = this.map.width();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                this.addTile(pane, this.map.getTile(x, y), x, y);
            }
        }

        HBox timerBox = new HBox();
        timerBox.getChildren().add(root);
        if (game.Manager.getGame().getTurn() != null) {
            timerBox.getChildren().add(new graphics.Timer(
                game.Manager.getGame().getTurn().getTimer()).getPane());
        } else {
            timerBox.getChildren().add(new graphics.Timer(
                new timer.Timer(timer.ITimer.SECOND * 5, () -> {})).getPane());
        }
        // Actually render against the scene.
        Scene scene = new Scene(timerBox);

        return scene;
    }
}
