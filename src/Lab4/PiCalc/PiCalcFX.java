package Lab4.PiCalc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;

public class PiCalcFX extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            BorderPane root = FXMLLoader.load(getClass().getResource("PiCalc.fxml"));
            Scene scene = new Scene(root, 700, 500);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Prime Factors");
            primaryStage.show();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
