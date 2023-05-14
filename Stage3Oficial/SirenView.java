import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.util.Duration;

public class SirenView extends Polygon {

    public SirenView() {
        getPoints().addAll(0d,30d,
                0d,50d,
                40d, 80d,
                40d, 0d);
        setFill(Color.GREEN);
        setStroke(Color.BLACK);
        timeline = new Timeline(new KeyFrame(Duration.millis(250), e -> {
            if(getFill() == Color.GREEN){
                setFill(Color.RED);
            } else {
                setFill(Color.GREEN);
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
    }

    public void startBlinking(){
        timeline.play();
    }

    public void stopBlinking() {
        timeline.stop();
        setFill(Color.GREEN);
    }

    private final Timeline timeline;
}
