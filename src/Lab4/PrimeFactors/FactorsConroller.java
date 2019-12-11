package Lab4.PrimeFactors;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.net.URL;
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
