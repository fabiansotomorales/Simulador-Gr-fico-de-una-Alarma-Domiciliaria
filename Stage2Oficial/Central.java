import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import java.util.ArrayList;

public class Central {

    public Central(Siren siren){
        view = new CentralView(this);
        zones = new ArrayList<Sensor>();
        state = CentralState.DISARMED;
        this.siren = siren;
        periodicCheck = new Timeline(new KeyFrame(Duration.millis(200),
                e -> checkZones()));
        periodicCheck.setCycleCount(Animation.INDEFINITE);
    }

    public VBox getView (){
        return view;
    }

    public void armAll() {
        boolean[] close = checkCloseZones();
        String msg="Open zone(s): ";
        msg+=(!close[0]?"0":"-") + (!close[1]?",1":"-") + (!close[2]?",2":"");
        if(close[0] && close[1] && close[2]) {
            state = CentralState.ALL_ARMED;
            periodicCheck.play();
        }
        else
            view.setDisplay(msg);
    }

    public void armPerimeter() {
        String msg="No permitido";
        view.setDisplay(msg);
    }

    public void disarm() {
        state = CentralState.DISARMED;
        periodicCheck.stop();
        siren.stop();
        siren.getView().stopBlinking();
    }

    private boolean[] checkCloseZones() {
        boolean[] close = {true, true, true};
        for (Sensor sensor : zones)
            close[sensor.getZone()] &=sensor.isClose();
        return close;
    }

    public void addNewSensor(Sensor s){
        zones.add(s);
    }

    private void checkZones(){
        boolean[] close = checkCloseZones();
        switch (state) {
            case ALL_ARMED -> {
                if (!close[0] || !close[1] || !close[2]) {
                    siren.play();
                    siren.getView().startBlinking();
                }
            }
            case PERIMETER_ARMED -> {
                if (!close[0] || !close[1]) {
                    siren.play();
                    siren.getView().startBlinking();
                }
            }
        }
    }

    enum CentralState {
        ALL_ARMED, PERIMETER_ARMED, DISARMED
    }

    private final ArrayList<Sensor> zones;
    private CentralState state;
    private final Siren siren;
    private final Timeline periodicCheck;
    private final CentralView view;
}
