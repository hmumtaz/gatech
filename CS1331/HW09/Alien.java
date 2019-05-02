import java.util.Random;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.*;
import javafx.animation.PathTransition;
import javafx.util.Duration;
import javafx.scene.shape.Path;
import javafx.animation.PathTransition.OrientationType;
import javafx.scene.transform.Translate;
import javafx.scene.layout.Pane;

public class Alien {
    private Random rand = new Random();
    private int alNum;
    private String al;
    private Image img;
    private double health = 1.00;
    private double attack = .1;
    private ProgressBar healthBar = new ProgressBar(health);
    private ImageView alView = new ImageView();
    private HBox healthBox = new HBox();
    private VBox alBox = new VBox();
    private Path path;
    private PathTransition pathTransition = new PathTransition();
    private Translate tran = new Translate();

    public Alien() {

        alNum = rand.nextInt((11) + 1) + 1;
        al = "/res/Pixel-Alien-" + alNum + ".png";
        img = new Image(al);
        alView.setImage(img);
        healthBox.getChildren().add(healthBar);
        alBox.getChildren().addAll(healthBox, alView);
    }

    public void hurt(double amount) {
        health = health - amount;
        healthBar.setProgress(health);
    }

    public Pane getAlBox() {
        Pane show = new Pane(alBox);
        return show;
    }

    public PathTransition move() {
        path = new Path();
        path.getElements().add(new MoveTo(0f, 432f));
        path.getElements().add(new HLineTo(72f));
        path.getElements().add(new VLineTo(85f));
        path.getElements().add(new HLineTo(252f));
        path.getElements().add(new VLineTo(672f));
        path.getElements().add(new HLineTo(361f));
        path.getElements().add(new VLineTo(588f));
        path.getElements().add(new HLineTo(809f));
        path.getElements().add(new VLineTo(414f));
        path.getElements().add(new HLineTo(1110f));
        path.getElements().add(new VLineTo(279f));
        pathTransition.setDuration(Duration.millis(10000));
        pathTransition.setNode(alBox);
        pathTransition.setPath(path);
        pathTransition.setOrientation(OrientationType.NONE);
        pathTransition.setCycleCount(1);
        pathTransition.setAutoReverse(false);
        return pathTransition;
    }

    public double getHealth() {
        return health;
    }

    public boolean isDead() {
        return getHealth() < 0.1;
    }

    public Image getImage() {
        return img;
    }

    public void attack(Castle castle) {
        castle.hurt(.05);
    }
}