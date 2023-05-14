import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class PersonView extends Pane {
    private Person per;
    private Circle head;
    private Rectangle torso;

    private Rectangle rectangle;
    private Circle leftArm;
    private Circle rightArm;

    public PersonView(int x, int y) {

        // Cabeza
        head = new Circle(20, Color.BLACK);
        head.setCenterX(x);
        head.setCenterY(y);

        // Torso
        torso = new Rectangle(60, 40, Color.BROWN);
        torso.setArcWidth(20);
        torso.setArcHeight(20);
        torso.setX(x - 30);
        torso.setY(y - 20);
        torso.setStroke(Color.BROWN);
        torso.setStrokeWidth(3);

        rectangle = new Rectangle(x, y, 90, 40);
        rectangle.setFill(Color.WHITE);
        rectangle.setStroke(Color.BLACK);

        // Brazos
        leftArm = new Circle(15, Color.BROWN);
        leftArm.setCenterX(x - 40);
        leftArm.setCenterY(y);

        rightArm = new Circle(15, Color.BROWN);
        rightArm.setCenterX(x + 40);
        rightArm.setCenterY(y);

        getChildren().addAll(torso, head, leftArm, rightArm);

        setOnMouseDragged((MouseEvent event) -> {
            double mouseX = event.getX();
            double mouseY = event.getY();


            head.setCenterX(mouseX);
            head.setCenterY(mouseY);

            torso.setX(mouseX - 30);
            torso.setY(mouseY - 20);

            rectangle.setX(mouseX - 30);
            rectangle.setY(mouseY - 20);

            leftArm.setCenterX(mouseX - 40);
            leftArm.setCenterY(mouseY);

            rightArm.setCenterX(mouseX + 40);
            rightArm.setCenterY(mouseY);
        });

    }

    public void setPerson (Person person){
        per = person;
    }

    public Rectangle getRectangle(){
        return rectangle;
    }

}
