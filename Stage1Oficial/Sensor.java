public class Sensor {

    public Sensor(int z){
        this(z, SwitchState.CLOSE);
    }

    public Sensor(int z, SwitchState s){
        zone = z;
        state = s;
    }

    public SwitchState getState(){
        return state;
    }

    protected void setState(SwitchState s) {
        state = s;
    }

    private SwitchState state;

    private int zone;
}
