package Lab4.PiCalc;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class PiCalcController {
    @FXML private TextField NumText;
    @FXML private TextField EpsText;
    @FXML private Button StartButton;
    @FXML private Button SuspendButton;
    @FXML private Button ResumeButton;
    @FXML private Button StopButton;
    @FXML private ProgressIndicator progressIndicator;
    @FXML private TextArea ResultsText;
    @FXML private TextField IterNumText;

    private PiCalcThread primeNumbers = new PiCalcThread(this::addToTextArea, this::setProgress, this::finish);

    @FXML private void startClick(ActionEvent actionEvent) {
        try {
            primeNumbers.setNum(Integer.parseInt(NumText.getText()));
            primeNumbers.setEps(Double.parseDouble(EpsText.getText()));
            ResultsText.setText("");
            progressIndicator.setProgress(0);
            StartButton.setDisable(true);
            SuspendButton.setDisable(false);
            ResumeButton.setDisable(true);
            StopButton.setDisable(false);
            primeNumbers.start();
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid input");
            alert.showAndWait();

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

    @FXML
    private void updateClick(ActionEvent actionEvent) {
        IterNumText.setText(""+primeNumbers.getCount());
    }

    private void addToTextArea() {

        ResultsText.setText(ResultsText.getText()+primeNumbers.getLastFound()+" | ");
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
}
