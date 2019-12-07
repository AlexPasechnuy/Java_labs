package Lab4.IndTask;

import javafx.application.Application;
import javafx.event.Event;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import static java.lang.Math.abs;

public class TestClass extends Application {
    private Button build, reset;
    private TextField fieldFunction1,fieldFunction2,fieldFromX,fieldToX,fieldStepX,fieldStepY,fieldFromY,fieldToY,fieldA,fieldB;
    private LineChart<Number, Number> graphChart;
    FlowPane rootNode,graphNode;
    public static double f(String function, double x){
        ScriptEngineManager factory = new ScriptEngineManager();
        ScriptEngine engine=factory.getEngineByName("JavaScript");
        engine.put("x",x);
        Object result=null;

        try {
            result = engine.eval(function+";");

        }

        catch (Exception e){

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Not a function");
                alert.showAndWait();
            }

        return (double) result;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Тригонометричні функції");
        rootNode = new FlowPane(10,10);
        graphNode = new FlowPane(10,10);
        rootNode.setAlignment(Pos.CENTER);
        Scene scene = new Scene(rootNode,1200,850);

        primaryStage.setScene(scene);
        build=new Button("Construct");
        reset=new Button("Reset");
        build.setOnAction(this::constructGraph);
        reset.setOnAction(this::resetFunc);
        fieldFromX=new TextField();
        fieldFromX.setPromptText("From X");
        fieldToX=new TextField();
        fieldToX.setPromptText("To X");
        fieldStepX = new TextField();
        fieldStepX.setPromptText("Step X");
        fieldFromY=new TextField();
        fieldFromY.setPromptText("From Y");
        fieldToY=new TextField();
        fieldToY.setPromptText("To Y");
        fieldStepY = new TextField();
        fieldStepY.setPromptText("Step Y");
        fieldFunction1= new TextField();
        fieldFunction1.setPromptText("Function 1");
        fieldFunction2= new TextField();
        fieldFunction2.setPromptText("Function 2");
        fieldA= new TextField();
        fieldA.setPromptText("a");
        fieldB= new TextField();
        fieldB.setPromptText("b");
        NumberAxis xAxis = new NumberAxis(-5, 5, 1);
        NumberAxis yAxis = new NumberAxis(-5, 5, 1);
        graphChart=new LineChart<Number,Number>(xAxis,yAxis);
        graphChart.setCreateSymbols(false);
        rootNode.getChildren().addAll(graphNode,build,reset,fieldFromX,fieldToX,fieldStepX,fieldFromY,fieldToY,fieldStepY,fieldFunction1,fieldFunction2,fieldA,fieldB);
        graphNode.getChildren().add(graphChart);
        primaryStage.show();
    }
    public void setGraph(LineChart<Number,Number> graphChart){
        this.graphChart=graphChart;
    }
    private void constructGraph(Event event){
        try {
            NumberAxis xAxis = new NumberAxis(Double.parseDouble(fieldFromX.getText()),Double.parseDouble(fieldToX.getText()),Double.parseDouble(fieldStepX.getText()));
            NumberAxis yAxis = new NumberAxis(Double.parseDouble(fieldFromY.getText()),Double.parseDouble(fieldToY.getText()),Double.parseDouble(fieldStepY.getText()));
            if(Double.parseDouble(fieldFromX.getText())>=Double.parseDouble(fieldToX.getText())||Double.parseDouble(fieldFromY.getText())>=Double.parseDouble(fieldToY.getText())){
                throw new Exception();
            }
            LineChart<Number, Number> newChart = new LineChart<>(xAxis, yAxis);
            newChart.setCreateSymbols(false);
            graphNode.getChildren().clear();
            graphNode.getChildren().add(newChart);
            double h = abs((Double.parseDouble(fieldToX.getText()) - Double.parseDouble(fieldFromX.getText())) / 100);
            double a = Double.parseDouble(fieldA.getText());
            double b = Double.parseDouble(fieldB.getText());
            XYChart.Series<Number, Number> gSeries = new XYChart.Series<>();
            for (double x = Double.parseDouble(fieldFromX.getText()); x <= Double.parseDouble(fieldToX.getText()); x += h) {

                gSeries.getData().add(new XYChart.Data<>(x, (f(fieldFunction1.getText(),x/a))*(f(fieldFunction2.getText(),x+b))));
            }
            newChart.getData().add(gSeries);


        }
        catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid input");
            alert.showAndWait();
        }
    }
    private void resetFunc(Event event){
        NumberAxis xAxis = new NumberAxis(-5, 5, 1);
        NumberAxis yAxis = new NumberAxis(-5, 5, 1);
        graphChart=new LineChart<Number,Number>(xAxis,yAxis);
        graphNode.getChildren().clear();
        graphNode.getChildren().add(graphChart);
        fieldFromX.setText("");
        fieldToX.setText("");
        fieldStepX.setText("");
        fieldFromY.setText("");
        fieldToY.setText("");
        fieldStepY.setText(" ");
        fieldFunction1.setText("");
        fieldFunction2.setText("");
        fieldA.setText("");
        fieldB.setText("");
    }


    public static void main(String[] args) {
        launch(args);
    }

}




