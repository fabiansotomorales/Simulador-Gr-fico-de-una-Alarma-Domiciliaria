public class Door {

    public Door(MagneticSensor sensor, DoorView view) {
        magneticSensor = sensor;
        state = State.CLOSE;
        dView = view;
        dView.addMagneticSensorView(magneticSensor.getView());
        dView.setDoorModel(this);
    }
    public void changeState() {
        switch (state) {  // this is the enhanced version of switch statement. It does not require breaks.
            case OPEN -> {
                state = State.CLOSING;
                magneticSensor.setSensorClose();
                state = State.CLOSE;

            }
            case CLOSE-> {
                state = State.OPENING;
                magneticSensor.setSensorOpen();
                state = State.OPEN;
            }
        }
    }
    private final DoorView dView;
    private final MagneticSensor magneticSensor;
    private State state;
 }