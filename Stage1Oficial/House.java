import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import java.util.ArrayList;
import java.util.Scanner;

public class House extends Pane {

    public House(Scanner in) {
        // reading <#_doors> <#_windows> <#_PIRs>
        in.nextInt();
        int numWindows = in.nextInt();
        in.nextInt();
        for (int i = 0; i < numWindows; i++){
            int x = in.nextInt();
            int y = in.nextInt();
            int angle = in.nextInt();
            int zone = in.nextInt();
            WindowView windowView = new WindowView(x, y, angle);
            Window window = new Window(zone, windowView);
            this.getChildren().add(window.getView());
        }
        in.close();
    }
}
