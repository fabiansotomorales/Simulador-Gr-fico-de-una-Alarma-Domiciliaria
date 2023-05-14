///////    PIR_Detector.java    ///////

//import docPirDetector.*;

/**
 * Sub-class of sensor hierarchy.
 * @author Fabian Soto, Felipe Salas y Javiera Fuentes
 */
public class PIR_Detector extends Sensor{

    /**
     * Constructor PIR_Detector.  Call the constructor of superclass Sensor an inicializes the detector pir view.
     * @param pdView	   Inicializes the view of pir detector
     * @param z            Is passed to constructor of superclass Sensor
     */
    public PIR_Detector(PIR_DetectorView pdView, int z){
        super(z);
        pirView = pdView;
    }

    /**
     * activates the detector pir
     */
    public void setSensorActive(){
        isActive = true;
    }

    /**
     * Desactivates the detector pir
     */
    public void setSensorDesactive(){
        isActive = false;
    }

    /**
     * Delivers the pir detector view
     * @return the view of pir detector
     */
    public PIR_DetectorView getView(){
        return pirView;
    }

    /**
     * Recovers the current state of the pir detector
     * @return status of the pir detector
     */
    public boolean isActive(){
        return isActive;
    }

    /**
     * view of pir detector
     */
    private PIR_DetectorView pirView;

    /**
     * pir detector state
     */
    private boolean isActive;

}


