import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.transform.Rotate;

public class CentralView extends VBox {
    private final Label display;
    public CentralView (Central central) {
        super(20);
        display = new Label("Display");
        display.setStyle("-fx-border-color: black; -fx-background-color: white");
        display.setMinWidth(200);
        display.setAlignment(Pos.CENTER);
        Font controlFont = new Font("Arial", 24);
        display.setFont(controlFont);
        Button aBtn, pBtn, dBtn;
        aBtn = new Button("A");
        pBtn = new Button("P");
        dBtn = new Button("D");
        aBtn.setFont(controlFont);
        pBtn.setFont(controlFont);
        dBtn.setFont(controlFont);
        aBtn.setOnAction(e -> central.armAll());
        pBtn.setOnAction(e -> central.armPerimeter());
        dBtn.setOnAction(e -> central.disarm());
        HBox hBox = new HBox();
        hBox.getChildren().addAll(aBtn,pBtn,dBtn);
        hBox.setAlignment(Pos.CENTER);
        getChildren().addAll(display,hBox);

    }

    public void setDisplay (String msg) {
        display.setText(msg);
    }
}
