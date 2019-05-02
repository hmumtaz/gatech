import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.Pane;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.*;
import javafx.scene.paint.Paint;
import javafx.event.EventHandler;
import javafx.animation.PathTransition;
import javafx.util.Duration;
import java.util.ArrayList;
import javafx.animation.AnimationTimer;

public class TowerDefense extends Application {
    private Image background = new Image("/res/MapCropped.png");
    private Image grass = new Image("/res/grass.png");
    private Image foreground = new Image("/res/MapCroppedForeground.png");
    private Image bomb = new Image("/res/bomb.gif");
    private ImageView back;
    private ImageView fore;
    private int score = 50;
    private int level = 1;
    private ArrayList<Alien> aliens = new ArrayList<>();
    private ArrayList<Turret> turrets = new ArrayList<>();
    private double locx;
    private double locy;


    public void start(Stage stage) throws InterruptedException {

        back = new ImageView();
        back.setImage(background);
        StackPane stackPane = new StackPane();
        fore = new ImageView();
        fore.setImage(foreground);
        stackPane.getChildren().add(back);

        stackPane.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                if (background.getPixelReader().getArgb((int) (e.getX()),
                    (int) (e.getY())) == grass.getPixelReader().getArgb(
                    (int) (grass.getWidth() / 2), (int)
                    (grass.getHeight() / 2))) {
                    Rectangle rect = new Rectangle((int) (e.getX()), e.getY(),
                        grass.getWidth(), grass.getHeight());
                    rect.setFill(Paint.valueOf("green"));
                    stackPane.getChildren().add(rect);
                } else {
                    Rectangle rect = new Rectangle(e.getX(), e.getY(), grass
                        .getWidth(), grass.getHeight());
                    rect.setFill(Paint.valueOf("red"));
                }
            }
        });

        stackPane.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (score >= 10 && background.getPixelReader()
                    .getArgb((int) (event.getX()), (int) (event.getY()))
                    == grass.getPixelReader().getArgb(
                    (int) (grass.getWidth() / 2),
                    (int) (grass.getHeight() / 2))) {
                    Turret turr = new Turret();
                    turrets.add(turr);
                    ImageView tur = turr.createTurret();
                    locx = event.getX();
                    tur.setX(locx);
                    locy = event.getY();
                    tur.setY(locy);
                    score = score - 10;
                    stackPane.getChildren().add(new Pane(tur));
                }
            }
        });
//Need to implement eventhandler & timer
        while (aliens.size() < 10) {
            for (int x = 0; x < 3; x = x + 1) {
                Alien al = new Alien();
                aliens.add(al);
                stackPane.getChildren().add(al.getAlBox());
                al.move().play();
                Thread.sleep(1000);
            }
        }
//Need to implement eventhandler & timer
        new AnimationTimer() {
            public void handle(long now) {
                while (aliens.size() > 0 && turrets.size() > 0) {
                    for (int x = 0; x < aliens.size(); x++) {
                        Alien alie = aliens.get(x);
                        if (alie.isDead() == false) {
                            for (int y = 0; y < turrets.size(); y++) {
                                ImageView bomber = new ImageView();
                                bomber.setImage(bomb);
                                bomber.setX(locx);
                                bomber.setY(locy);
                                Line pth = new Line(locx,
                                    locy,
                                    (alie.getImage().getWidth() / 2),
                                    (alie.getImage()
                                    .getHeight() / 2));
                                PathTransition path = new PathTransition();
                                path.setDuration(Duration.millis(10000));
                                path.setNode(bomber);
                                path.setPath(pth);
                                stackPane.getChildren().add(new Pane(bomber));
                                path.play();
                                if (turrets.get(y).isHit()) {
                                    turrets.get(y).shoot(alie);
                                }
                            }
                        }
                        if (alie.isDead() == true) {
                            stackPane.getChildren().remove(alie
                                .getAlBox());
                            aliens.remove(alie);
                        }
                    }
                }
            } }.start();

        stackPane.getChildren().add(fore);

        Scene scene = new Scene(stackPane);
        stage.setScene(scene);
        stage.setTitle("Tower Defense");
        stage.show();
    }
}