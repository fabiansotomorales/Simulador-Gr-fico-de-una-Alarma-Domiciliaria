import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Polygon;

import docPirDetector.*;

public class PIR_DetectorView extends Group {

    public PIR_DetectorView(int x, int y, int dir_angle, int sensing_angle, int sensing_range) {
        Polygon pir = new Polygon();
        pir.getPoints().addAll(
                0d, 15d,
                10d, 25d,
                40d, 25d,
                50d, 15d,
                50d, 0d,
                0d, 0d
        );

        pir.setFill(Color.GREEN);
        pir.setStroke(Color.BLACK);
        Arc arc = new Arc();
        if (dir_angle <= 180){
            arc.setCenterX(x+18);
            arc.setCenterY(y+4);
            arc.setRadiusX(sensing_range);
            arc.setRadiusY(sensing_range);
            arc.setStartAngle(dir_angle);
            arc.setLength(sensing_angle);
        } else {
            arc.setCenterX(x+32);
            arc.setCenterY(y+2);
            arc.setRadiusX(sensing_range);
            arc.setRadiusY(sensing_range);
            arc.setStartAngle(0);
            arc.setLength(sensing_angle);
        }
        arc.setType(ArcType.ROUND);
        arc.setFill(Color.LIGHTCORAL);
        arc.setStroke(Color.LIGHTCORAL);
        a = arc;
        getChildren().addAll(pir, arc);
        pir.setRotate(dir_angle);
        pir.relocate(x, y);
    }

    public Arc getArc(){
        return a;
    }

    private Arc a;

}