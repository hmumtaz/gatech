import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;

public class Castle {
    private double health = 1.00;
    private ProgressBar healthBar = new ProgressBar(health);
    private HBox cBox = new HBox();

    public Castle() {
        cBox.getChildren().add(healthBar);
    }

    public void hurt(double amount) {
        health = health - amount;
        healthBar.setProgress(health);
    }

    public void heal() {
        if (health < 100) {
            health = health + .5;
        }
    }
}