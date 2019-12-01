package Lab3.IndTask;

import Lab1.Ind_task.AbsDoctor;
import Lab1.Ind_task.AbsRecept;
import Lab1.Ind_task.DoctorArr;
import Lab1.Ind_task.Reception;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.net.URL;
import java.util.*;

public class DoctorConroller implements Initializable {
    List<DoctorArr> doctors = new ArrayList<>();

    public TextField surnSearch;
    public TextField dateSearch;
    public Button searchBtn;
    public TextArea searchRes;
    public TableView docTable;
    public TableColumn<AbsDoctor, String> surnCol;
    public TableColumn<AbsDoctor, String> specCol;
    public TableColumn<AbsRecept, String> dateCol;
    public TableColumn<AbsRecept, Integer> shiftCol;
    public TableColumn<AbsRecept, Integer> countCol;
    public Button sortByDate;
    public Button sortByCount;
    public TextField surnAdd;
    public TextField specAdd;
    public TextField dateAdd;
    public TextField shiftAdd;
    public TextField countAdd;
    public Button addBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //docTable.setPlaceholder(new Label(""));
    }

    private void docTableInit(){

    }

    private void readFromFile(String path){
        try {
            Document doc;
            DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            doc = db.parse(new File(path));
            Node rootNode = doc.getDocumentElement();
            mainLoop:
            for (int i = 0; i < rootNode.getChildNodes().getLength(); i++) {
                Node doctorNode = rootNode.getChildNodes().item(i);
                if (doctorNode.getNodeName().equals("Doctor")) {
                    DoctorArr doctor = new DoctorArr(doctorNode.getAttributes().getNamedItem("Surname").toString(),
                            doctorNode.getAttributes().getNamedItem("Speciality").toString());
                    for (int j = 0; j < doctorNode.getChildNodes().getLength(); j++) {
                        Node reception = doctorNode.getChildNodes().item(j);
                        if (reception.getNodeName().equals("Reception")) {
                            // Знаходимо атрибут за іменем:
                            System.out.println("Date " + reception.getAttributes().getNamedItem("Date"));
                            System.out.println("Shift " + reception.getAttributes().getNamedItem("Shift"));
                            System.out.println("Count " + reception.getAttributes().getNamedItem("Count"));
                            doctor.addRec(new Reception(reception.getAttributes().getNamedItem("Date").toString(),
                                    Integer.parseInt(reception.getAttributes().getNamedItem("Shift").toString()),
                                    Integer.parseInt(reception.getAttributes().getNamedItem("Count").toString())));
                        }
                    }
                    doctors.add(doctor);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void open(javafx.event.ActionEvent event) {
        FileChooser fileChooser = getFileChooser("Open XML file");
        File file;
        if ((file = fileChooser.showOpenDialog(null)) != null) {
            try {
                readFromFile(file.getCanonicalPath());
                docTableInit();
            } catch (IOException e) {
                showError("No such file");
            }
        }
    }

    public static FileChooser getFileChooser(String title) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("."));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("All files (*.*)", "*.*"));
        fileChooser.setTitle(title);
        return fileChooser;
    }

    private static void showMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("");
        alert.setHeaderText(message);
        alert.showAndWait();
    }

    private static void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(message);
        alert.showAndWait();
    }

    private void add(javafx.event.ActionEvent event){

    }
}
