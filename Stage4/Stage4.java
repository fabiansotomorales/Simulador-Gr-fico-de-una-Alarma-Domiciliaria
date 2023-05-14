import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class Stage4 extends Application {
    @Override
    public void start(Stage primaryStage) {
        List<String> args = getParameters().getRaw();
        if (args.size() != 1) {
            System.out.println("Usage: java Stage1 <configurationFile.txt>");
            System.exit(-1);
        }
        Scanner in = null;
        try {
            in = new Scanner(new File(args.get(0)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        Siren siren = new Siren("http://profesores.elo.utfsm.cl/~agv/elo329/JavaProg/JavaFX/AudioMediaDemo/siren.wav");
        Central central = new Central(siren);
        Button insertButton = new Button("Insert");
        House house = new House(in, central, insertButton);
        in.close();
        VBox vBox = new VBox(20);
        Separator hSeparator = new Separator(Orientation.HORIZONTAL);
        vBox.getChildren().addAll(siren.getView(),hSeparator, central.getView());
        vBox.setPadding(new Insets(10, 10,10,10));
        vBox.setAlignment(Pos.CENTER);
        BorderPane borderPane = new BorderPane();
        Separator insertSeparator = new Separator(Orientation.HORIZONTAL);
        borderPane.setTop(insertButton);
        borderPane.setLeft(house);
        borderPane.setCenter(new Separator(Orientation.VERTICAL));
        borderPane.setRight(vBox);
        borderPane.setPadding(new Insets(10,10,10,10));
        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("ELO329: T2 Stage 4");
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}

