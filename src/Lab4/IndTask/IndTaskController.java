package Lab4.IndTask;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;

import static java.lang.Math.abs;

public class IndTaskController {

    @FXML private Button Run;
    @FXML private Button Clear;
    @FXML private TextField fFuncText;
    @FXML private TextField gFuncText;
    @FXML private TextField XFromText;
    @FXML private TextField XToText;
    @FXML private TextField XStepText;
    @FXML private TextField YFromText;
    @FXML private TextField YToText;
    @FXML private TextField YStepText;
    @FXML private TextField AText;
    @FXML private TextField BText;
    @FXML private LineChart<Number, Number> graph;

    public void setGraph(LineChart<Number,Number> graphChart){
        this.graph=graphChart;
    }

    @FXML
    private void constructClick(Event event){
        try {
            NumberAxis xAxis = new NumberAxis(Double.parseDouble(XFromText.getText()),Double.parseDouble(XToText.getText()),Double.parseDouble(XStepText.getText()));
            NumberAxis yAxis = new NumberAxis(Double.parseDouble(YFromText.getText()),Double.parseDouble(YToText.getText()),Double.parseDouble(YStepText.getText()));
            if(Double.parseDouble(XFromText.getText())>=Double.parseDouble(XToText.getText())||Double.parseDouble(YFromText.getText())>=Double.parseDouble(YToText.getText())){
                throw new Exception();
            }
            LineChart<Number, Number> newChart = new LineChart<>(xAxis, yAxis);
            newChart.setCreateSymbols(false);
            double h = abs((Double.parseDouble(XToText.getText()) - Double.parseDouble(XFromText.getText())) / 100);
            double a = Double.parseDouble(AText.getText());
            if(a==0) {
                throw new Exception();
            }
            double b = Double.parseDouble(BText.getText());
            XYChart.Series<Number, Number> gSeries = new XYChart.Series<>();
            for (double x = Double.parseDouble(XFromText.getText()); x <= Double.parseDouble(XToText.getText()); x += h) {

                gSeries.getData().add(new XYChart.Data<>(x, (MetaProgr.f(fFuncText.getText(),x/a))*(MetaProgr.f(gFuncText.getText(),x+b))));
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

    @FXML
    private void clearClick(Event event){
        NumberAxis xAxis = new NumberAxis(-5, 5, 1);
        NumberAxis yAxis = new NumberAxis(-5, 5, 1);
        graph=new LineChart<Number,Number>(xAxis,yAxis);
        XFromText.setText("");
        XToText.setText("");
        XStepText.setText("");
        YFromText.setText("");
        YToText.setText("");
        YStepText.setText(" ");
        fFuncText.setText("");
        gFuncText.setText("");
        AText.setText("");
        BText.setText("");
    }
}
