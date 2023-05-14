///////    Sensor.java    ///////

/**
 * Superclass of sensor hierarchy.
 * @author Fabian Soto, Felipe Salas y Javiera Fuentes
 */

public class Sensor { // class name

    /**
     * Constructor Sensor.  Call another sensor constructor.
     * @param z	    Is passed when calling the other sensor constructor
     */
    public Sensor(int z){
        this(z, true);
    }

    /**
     * Other Constructor Sensor.
     * @param z	        initialize the zone
     * @param close     if window or door is close
     */
    public Sensor(int z, boolean close){
        zone = z;
        isClose = close;
    }

    /**
     * Recovers the current sensor status
     * @return status of the sensor
     */
    public boolean isClose(){
        return isClose;
    }

    /**
     * Recovers the zone where the sensor is located
     * @return the zone
     */
    public int getZone() {
        return zone;
    }

    /**
     * Changes the status of the sensor
     */
    protected void setClose(boolean close) {
        isClose = close;
    }

    /**
     * status of sensor
     */
    private boolean isClose;

    /**
     * zone where sensor is located
     */
    private int zone;
}
