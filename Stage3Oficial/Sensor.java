public class Sensor {
    public Sensor(int z){
        this(z, true);
    }
    public Sensor(int z, boolean close){
        zone = z;
        isClose = close;
    }
    public boolean isClose(){
        return isClose;
    }
    public int getZone() {
        return zone;
    }
    protected void setClose(boolean close) {
        isClose = close;
    }

    private boolean isClose;
    private int zone;
}
