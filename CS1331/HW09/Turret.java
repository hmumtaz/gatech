import javafx.scene.image.Image;
import java.util.Random;
import javafx.scene.image.ImageView;

public class Turret {
    private Image launcher = new Image("/res/launcher.png");
    private ImageView launch;
    private Random rand = new Random();

    public Turret() {
    }

    public boolean isHit() {
        int prob = rand.nextInt((100) + 1);
        return prob < 70;
    }

    public Image getImage() {
        return launcher;
    }

    public ImageView createTurret() {
        launch = new ImageView();
        launch.setImage(launcher);
        return launch;
    }

    public void shoot(Alien alien) {
        alien.hurt(.001);
    }
}