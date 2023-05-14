
/**
 * A window with its magnetic sensor.
 */
public class Window {
    public Window(int zone, WindowView view) {
        magneticSensor = new MagneticSensor(zone);
        state = State.CLOSE;
        wView = view;
        wView.addMagneticSensorView(magneticSensor.getView());
        wView.setWindowModel(this);
    }
    public void changeState() {  // is called when this window's view is clicked
        switch (state) {
            case CLOSE:
                this.state = State.OPENING;
                wView.startOpening();
                this.finishMovement();
                break;
            case OPEN:
                this.state = State.CLOSING;
                wView.startClosing();
                this.finishMovement();
                break;
        }
    }
    public void finishMovement() { // is called when this window ends closing or opening
        if (this.state == State.OPENING){
            magneticSensor.setSensorOpen();
            state = State.OPEN;
        } else if (state == State.CLOSING){
            magneticSensor.setSensorClose();
            state = State.CLOSE;
        }
    }
    public WindowView getView(){
        return wView;
    }
    private final WindowView wView;
    private final MagneticSensor magneticSensor;
    private State state;
}
