import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;


public class WindowView extends Group {

    public WindowView(int x, int y, int angle){
        makeWindowViewWithoutSensor();
        setRotate(angle);  // to rotate at the geometric center.
        getTransforms().add(new Rotate(angle,40,50));  // to rotate at anchor pivot (40,50)
        relocate(x, y);
        prepareOpen_CloseTransition();
    }

    private void makeWindowViewWithoutSensor(){
        Rectangle origenPillar = new Rectangle(0, 0, 20, 20);
        origenPillar.setFill(Color.BLUE);
        origenPillar.setStroke(Color.BLUE);
        switchPillar = new Rectangle(180, 0, 20, 20);
        switchPillar.setFill(Color.BLUE);
        switchPillar.setStroke(Color.BLUE);
        Rectangle fixedGlas = new Rectangle(21, 4, 82, 6);
        fixedGlas.setFill(Color.LIGHTBLUE);
        slidingGlas = new Rectangle(97,11,82,6);
        slidingGlas.setFill(Color.LIGHTBLUE);
        Rectangle border = new Rectangle(0, 0, 200, 20);
        border.setFill(Color.WHITE);
        border.setStroke(Color.BLACK);
        border.setStrokeWidth(1);
        border.getStrokeDashArray().addAll(4d, 4d);
        getChildren().add(border);
        getChildren().addAll(origenPillar, switchPillar, fixedGlas,slidingGlas);
    }

    public void setWindowModel(Window model) {
        winModel = model;
        winModel.getView().setOnMouseClicked(e -> {
            if (e.getButton() == MouseButton.PRIMARY){
                winModel.changeState();
            }
        });
    }

    public void addMagneticSensorView(MagneticSensorView msView){
        placeMagneticSensor(msView);
        getChildren().add(msView);
    }

    private void placeMagneticSensor(MagneticSensorView mv){
        mv.getMagnetView().setX(slidingGlas.getX()+slidingGlas.getWidth()-mv.getMagnetView().getWidth());
        mv.getMagnetView().setY(slidingGlas.getY()+slidingGlas.getHeight()-mv.getMagnetView().getHeight());
        mv.getMagnetView().translateXProperty().bind(slidingGlas.translateXProperty()); // so it moves along with window
    }

    void prepareOpen_CloseTransition(){
        transition = new TranslateTransition(Duration.millis(2000), slidingGlas);
        transition.setCycleCount(1);
        transition.setOnFinished(e -> winModel.finishMovement());
    }

    public void startOpening(){
        transition.stop();
        transition.setFromX(slidingGlas.getTranslateX());// in case the user decides to close before it opens.
        transition.setToX(slidingGlas.getX());
        transition.play();
    }

    public void startClosing() {
        transition.stop();
        transition.setFromX(slidingGlas.getX());  // in case the user decides to open before it closes.
        transition.setToX(slidingGlas.getX()-slidingGlas.getTranslateX()); // original position
        transition.play();
    }

    private TranslateTransition transition;

    private Window winModel;

    private Rectangle switchPillar;

    private Rectangle slidingGlas;
}
