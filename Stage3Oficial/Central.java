import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Shape;
import javafx.util.Duration;
import java.util.ArrayList;

public class Central {

    public Central(Siren siren){
        view = new CentralView(this);
        zones = new ArrayList<>();
        zone2 = new ArrayList<>();
        persons = new ArrayList<>();
        state = CentralState.DISARMED;
        this.siren = siren;
        periodicCheck = new Timeline(new KeyFrame(Duration.millis(200),
                e -> checkZones()));
        periodicCheck.setCycleCount(Animation.INDEFINITE);
        periodicCheckZone2 = new Timeline(new KeyFrame(Duration.millis(300),
                e -> checkZone2()));
        periodicCheckZone2.setCycleCount(Animation.INDEFINITE);
    }

    public VBox getView (){
        return view;
    }

    public void armAll() {
        boolean[] close = checkCloseZones();
        String msg="Open zone(s): ";
        msg+=(!close[0]?"0":"-") + (!close[1]?",1":"-") + (!close[2]?",2":"-");
        if(close[0] && close[1] && close[2]) {
            state = CentralState.ALL_ARMED;
            zone2.get(0).setSensorActive();
            zone2.get(1).setSensorActive();
            periodicCheck.play();
            periodicCheckZone2.play();
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
        zone2.get(0).setSensorDesactive();
        zone2.get(1).setSensorDesactive();
        periodicCheck.stop();
        periodicCheckZone2.stop();
        siren.stop();
        siren.getView().stopBlinking();
    }

    private boolean[] checkCloseZones() {
        boolean[] close = {true, true, true};
        for (Sensor sensor : zones)
            close[sensor.getZone()] &= sensor.isClose();
        return close;
    }


    private void checkZone2() {
        Shape inter = Shape.intersect(zone2.get(0).getView().getArc(), persons.get(0).getView().getRectangle());
        Shape inter2 = Shape.intersect(zone2.get(1).getView().getArc(), persons.get(0).getView().getRectangle());
        switch (state) {
            case ALL_ARMED -> {
                if (inter.getLayoutBounds().getHeight()>=0 || inter.getLayoutBounds().getWidth()>=0) {
                    siren.play();
                    siren.getView().startBlinking();
                } else if (inter2.getLayoutBounds().getHeight()>=0 || inter2.getLayoutBounds().getWidth()>=0){
                    siren.play();
                    siren.getView().startBlinking();
                }
            }
        }
    }

    public void addNewSensor(Sensor s){
        zones.add(s);
    }

    public void addNewSensorPir(PIR_Detector p){
        zone2.add(p);
    }

    public void addNewPerson(Person per){
        persons.add(per);
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

    private final ArrayList<PIR_Detector> zone2;

    private final ArrayList<Person> persons;
    private CentralState state;
    private final Siren siren;
    private final Timeline periodicCheck;

    private final Timeline periodicCheckZone2;
    private final CentralView view;
}
