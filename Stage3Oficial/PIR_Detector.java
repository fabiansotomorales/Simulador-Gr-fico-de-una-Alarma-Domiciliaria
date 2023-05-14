public class PIR_Detector extends Sensor{

    public PIR_Detector(PIR_DetectorView pdView, int z){
        super(z);
        pirView = pdView;
    }

    public void setSensorActive(){
        isActive = true;
    }

    public void setSensorDesactive(){
        isActive = false;
    }

    public PIR_DetectorView getView(){
        return pirView;
    }

    public boolean isActive(){
        return isActive;
    }

    private PIR_DetectorView pirView;

    private boolean isActive;

}


