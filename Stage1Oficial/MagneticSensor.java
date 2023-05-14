public class MagneticSensor extends Sensor {

    public MagneticSensor(int z){
        super(z);
        view = new MagneticSensorView();
    }
    public void setSensorOpen() {
        view.setOpenView();
        setState(SwitchState.OPEN);
    }
    public void setSensorClose() {
        view.setCloseView();
        setState(SwitchState.CLOSE);
    }

    public MagneticSensorView getView(){ return view;}

    private final MagneticSensorView view;
}
