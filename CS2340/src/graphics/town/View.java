package graphics.town;

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
import javafx.scene.image.Image;
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
 * View for rendering a town.
 *
 * @author Joshua Songy
 * @version 1.2
 */
public class View implements graphics.IView {

    /**
     * The root pane of this view.
     */
    Pane root;

    /**
     * The scene this view is rendering against.
     */
    Scene scene;

    /**
     * Sets up the view for rendering.
     */
    public View() {
        root = new Pane();
        scene = new Scene(root);
        Image image = resources.Manager
                               .getResources()
                               .getTownImage("background");
        root.getChildren().add(new ImageView(image));
    }

    /**
     * Returns the title of the configuration window.
     *
     * @return The title of the configuration window.
     */
    public String getTitle() {
        return "M.U.L.E. - Town";
    }

    /**
     * Returns the root pane of this View.
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
        // Actually render against the scene.
        return scene;
    }
}
