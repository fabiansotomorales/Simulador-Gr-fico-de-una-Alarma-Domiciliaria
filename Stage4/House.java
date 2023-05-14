import javafx.scene.Group;
import javafx.scene.layout.Pane;
import java.util.Scanner;
import javafx.scene.control.Button;

public class House extends Pane {

    public House(Scanner in, Central central, Button insert) {
        // reading <#_doors> <#_windows> <#_PIRs>
        int numDoors = in.nextInt();
        int numWindows = in.nextInt();
        int numPirs = in.nextInt();
        for (int i = 0; i < numDoors; i++){
            int x = in.nextInt();
            int y = in.nextInt();
            int angle = in.nextInt();
            int zone = in.nextInt();
            DoorView doorView = new DoorView(x, y, angle);
            MagneticSensor sensor = new MagneticSensor(zone);
            central.addNewSensor(sensor);
            new Door(sensor, doorView);
            this.getChildren().add(doorView);
            in.nextLine();
        }
        for (int i = 0; i < numWindows; i++){
            int x = in.nextInt();
            int y = in.nextInt();
            int angle = in.nextInt();
            int zone = in.nextInt();
            WindowView windowView= new WindowView(x, y, angle);
            MagneticSensor sensor = new MagneticSensor(zone);
            central.addNewSensor(sensor);
            new Window(sensor, windowView);
            this.getChildren().add(windowView);
            in.nextLine();
        }
        for (int i = 0; i < numPirs; i++){
            int x = in.nextInt();
            int y = in.nextInt();
            int dir_angle = in.nextInt();
            int sensing_angle = in.nextInt();
            int sensing_range = in.nextInt();
            int zone = in.nextInt();
            PIR_DetectorView pirDetectorView = new PIR_DetectorView(x, y, dir_angle, sensing_angle, sensing_range);
            PIR_Detector pirDetector = new PIR_Detector(pirDetectorView, zone);
            central.addNewSensor(pirDetector);
            central.addNewSensorPir(pirDetector);
            this.getChildren().add(pirDetectorView);
        }
        insert.setOnAction(event -> {
            PersonView newPersonView = new PersonView(300, 300);
            Person newPerson = new Person(newPersonView);
            central.addNewPerson(newPerson);
            this.getChildren().add(newPersonView);
        });
        setMinWidth(700);
        in.close();
    }
}
