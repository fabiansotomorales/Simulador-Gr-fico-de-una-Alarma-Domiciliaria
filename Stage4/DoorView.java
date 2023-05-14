import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class DoorView extends Group {

    public DoorView(int x, int y, int angle) {
        makeDoorViewWithoutSensor();
        setRotate(angle);
        relocate(x, y);
    }

    private void makeDoorViewWithoutSensor() {
        Polygon origenPillar = new Polygon();
        origenPillar.getPoints().addAll(0d, 0d,
                0d, 20d,
                10d, 20d,
                10d, 10d,
                20d, 10d,
                20d, 0d);
        origenPillar.setFill(Color.BLUE);
        origenPillar.setStroke(Color.BLUE);

        switchPillar = new Polygon();
        switchPillar.getPoints().addAll(160d, 0d,
                160d, 10d,
                170d, 10d,
                170d, 20d,
                180d, 20d,
                180d, 0d);
        switchPillar.setFill(Color.BLUE);
        switchPillar.setStroke(Color.BLUE);

        slidingSheet = new Rectangle(0, 10, 160, 10);
        slidingSheet.setTranslateX(10);
        slidingSheet.setFill(Color.BURLYWOOD);

        Rectangle border = new Rectangle(0, 0, 180, 180);
        border.setFill(Color.TRANSPARENT);
        border.setStroke(Color.GRAY);
        border.getStrokeDashArray().addAll(4d, 4d);

        getChildren().addAll(border, origenPillar, switchPillar, slidingSheet);

    }

    private void toggleDoor(MagneticSensorView msView) {
        double targetAngle = isOpen ? closedAngle : openAngle;
        double currentAngle = slidingSheet.getRotate();
        Rotate rotate = new Rotate(currentAngle, 0, slidingSheet.getHeight() / 2);
        slidingSheet.getTransforms().clear();
        slidingSheet.getTransforms().add(rotate);

        double targetAngle1 = isOpen ? closedAngle : openAngle;
        double currentAngle1 = msView.getMagnetView().getRotate();
        Rotate rotate1 = new Rotate(currentAngle1, 6, (slidingSheet.getHeight() / 2) + 4);
        msView.getMagnetView().getTransforms().clear();
        msView.getMagnetView().getTransforms().add(rotate1);

        Timeline timeline = new Timeline();
        KeyValue keyValue = new KeyValue(rotate.angleProperty(), targetAngle);
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), keyValue);
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();

        Timeline timeline1 = new Timeline();
        KeyValue keyValue1 = new KeyValue(rotate1.angleProperty(), targetAngle1);
        KeyFrame keyFrame1 = new KeyFrame(Duration.seconds(1), keyValue1);
        timeline1.getKeyFrames().add(keyFrame1);
        timeline1.play();

        isOpen = !isOpen;
    }

    public void setDoorModel(Door model) {
        doorModel = model;
        setOnMouseClicked(e -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                toggleDoor(magView);
                doorModel.changeState();
            }
        });
    }

    public void addMagneticSensorView(MagneticSensorView msView) {
        placeMagneticSensor(msView);
        magView = msView;
        getChildren().add(msView);
    }

    private void placeMagneticSensor(MagneticSensorView mv) {
        mv.getMagnetView().xProperty().bind(slidingSheet.xProperty().add(slidingSheet.getWidth()).subtract(mv.getMagnetView().getWidth()));
        mv.getMagnetView().yProperty().bind(slidingSheet.yProperty().add(slidingSheet.getHeight()).subtract(mv.getMagnetView().getHeight()));
        mv.getSwitchView().setY(switchPillar.getBoundsInLocal().getHeight());
    }

    private Polygon switchPillar;
    private Door doorModel;
    private Rectangle slidingSheet;
    private double closedAngle = 0;
    private double openAngle = 90;
    private boolean isOpen = false;
    private MagneticSensorView magView;

}


