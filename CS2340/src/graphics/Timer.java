package graphics;

import timer.ITimer;

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
import javafx.scene.image.ImageView;
import input.IInput;

/**
 * View for rendering a timer.
 *
 * @author Joshua Songy
 * @version 1.2
 */
public class Timer {

    /**
     * The timer we are rendering.
     */
    private ITimer timer = null;

    /**
     * The root pane of this timer rendering.
     */
    private Pane root = new Pane();

    /**
     * Constructs a renderer for a timer.
     *
     * @param timer The timer to render.
     */
    public Timer(ITimer timer) {
        this.timer = timer;
        this.timer.registerTimeout(ITimer.SECOND / 10, ITimer.INDEFINITE, ()->{
            double ratio = 1;
            if (timer.getRemainingTime() != ITimer.INDEFINITE) {
                this.update(
                    (double) (timer.getRemainingTime()) /
                    (double) (timer.getRemainingTime() + timer.getElapsedTime()));
            }
        });
        this.update(0.5);
    }

    /**
     * Returns the root pane of this rendered object.
     *
     * @return The root pane of this rendered object.
     */
    public Pane getPane() {
        return this.root;
    }

    /**
     * Updates this timer view.
     *
     * @param ratio The ratio to render.
     */
    private void update(double ratio) {
        root.getChildren().clear();
        Image image = resources.Manager.getResources().getImage("timer");
        ImageView view = new ImageView(image);
        view.setFitHeight((double) image.getHeight() * ratio);
        view.setY(image.getHeight() - view.getFitHeight());
        root.getChildren().add(view);
    }
}
