package Lab3.MiniCalc;

import javafx.application.Application;
import javafx.event.Event;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class Calculator extends Application {
    private RadioButton plus, minus, divide, multiply;
    private TextField field1, field2, field3;

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Simple calculator");
        FlowPane rootNode = new FlowPane(10, 10);// визначаємо розміри горизонтального і
        // вертикального зазорів між елементами
        rootNode.setAlignment(Pos.CENTER);
        Scene scene = new Scene(rootNode, 200, 200); // розміри вікна
        stage.setScene(scene);
        plus = new RadioButton("+");
        minus = new RadioButton("-");
        divide = new RadioButton("/");
        multiply = new RadioButton("*");
        plus.setOnAction(this::plusClick);
        minus.setOnAction(this::minusClick);
        divide.setOnAction(this::divideClick);
        multiply.setOnAction(this::multiplyClick);
        ToggleGroup btnGrp = new ToggleGroup();
        plus.setToggleGroup(btnGrp);
        minus.setToggleGroup(btnGrp);
        divide.setToggleGroup(btnGrp);
        multiply.setToggleGroup(btnGrp);
        field1 = new TextField();
        field2 = new TextField();
        field3 = new TextField();
        rootNode.getChildren().addAll(field1, field2, plus, minus, divide, multiply, field3);
        stage.show();
    }

    private void plusClick(Event event) {
        try {
            double i = Double.parseDouble(field1.getText());
            double j = Double.parseDouble(field2.getText());
            double k = i + j;
            field3.setText(k + "");
        }
        catch (NumberFormatException e1) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Wromg input!");
            alert.showAndWait();
        }
    }

    private void minusClick(Event event) {
        try {
            double i = Double.parseDouble(field1.getText());
            double j = Double.parseDouble(field2.getText());
            double k = i - j;
            field3.setText(k + "");
        }
        catch (NumberFormatException e1) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Wromg input!");
            alert.showAndWait();
        }
    }

    private void multiplyClick(Event event) {
        try {
            double i = Double.parseDouble(field1.getText());
            double j = Double.parseDouble(field2.getText());
            double k = i * j;
            field3.setText(k + "");
        } catch (NumberFormatException e1) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Wromg input!");
            alert.showAndWait();
        }
    }

        private void divideClick(Event event) {
            try {
                double i = Double.parseDouble(field1.getText());
                double j = Double.parseDouble(field2.getText());
                double k = i / j;
                field3.setText(k + "");
            }
            catch (NumberFormatException | ArithmeticException e1) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Wromg input!");
                alert.showAndWait();
            }
        }

    public static void main(String[] args) {
        launch(args);
    }

}