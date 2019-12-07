package Lab4.PrimeFactors;

import Lab1.GenLib.WrongUsage;
import Lab1.Ind_task.AbsRecept;
import Lab1.Ind_task.DoctorArr;
import Lab1.Ind_task.Reception;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class FactorsConroller implements Initializable {
    @FXML private TextField FromText;
    @FXML private TextField ToText;
    @FXML private Button StartButton;
    @FXML private Button SuspendButton;
    @FXML private Button ResumeButton;
    @FXML private Button StopButton;
    @FXML private ProgressIndicator progressIndicator;
    @FXML private TextArea ResultsText;

    private FactorProcess primeNumbers = new FactorProcess(this::addToTextArea, this::setProgress, this::finish);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        StartButton.setDisable(false);
        SuspendButton.setDisable(true);
        ResumeButton.setDisable(true);
        StopButton.setDisable(true);
    }

    @FXML private void startClick(ActionEvent actionEvent) {
        try {
            primeNumbers.setFrom(Integer.parseInt(FromText.getText()));
            primeNumbers.setTo(Integer.parseInt(ToText.getText()));
            ResultsText.setText("");
            progressIndicator.setProgress(0);
            StartButton.setDisable(true);
            SuspendButton.setDisable(false);
            ResumeButton.setDisable(true);
            StopButton.setDisable(false);
            primeNumbers.start();
        } catch (NumberFormatException e) {
            showError("Incorrect input format");
        }
        catch (IllegalArgumentException ex){
            showError("Illegal FROM or TO");
        }
    }

    @FXML private void suspendClick(ActionEvent actionEvent) {
        primeNumbers.suspend();
        StartButton.setDisable(true);
        SuspendButton.setDisable(true);
        ResumeButton.setDisable(false);
        StopButton.setDisable(false);
    }

    @FXML private void resumeClick(ActionEvent actionEvent) {
        primeNumbers.resume();
        StartButton.setDisable(true);
        SuspendButton.setDisable(false);
        ResumeButton.setDisable(true);
        StopButton.setDisable(false);
    }

    @FXML
    private void stopClick(ActionEvent actionEvent) {
        primeNumbers.stop();
    }

    private void addToTextArea() {
        String res = primeNumbers.getLastFound().get(0) + ": ";
        for(int i=1;i<primeNumbers.getLastFound().size();i++) {
            res += primeNumbers.getLastFound().get(i) + " ";
        }
        ResultsText.setText(ResultsText.getText()+ res +"\n");
    }

    private void setProgress() {
        progressIndicator.setProgress(primeNumbers.getPercentage());
    }

    private void finish() {
        StartButton.setDisable(false);
        SuspendButton.setDisable(true);
        ResumeButton.setDisable(true);
        StopButton.setDisable(true);
    }

    private static void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(message);
        alert.showAndWait();
    }
}
