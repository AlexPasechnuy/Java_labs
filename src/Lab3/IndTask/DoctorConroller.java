package Lab3.IndTask;

import Lab1.Ind_task.AbsDoctor;
import Lab1.Ind_task.AbsRecept;
import Lab1.Ind_task.DoctorArr;
import Lab1.Ind_task.Reception;
import Lab2.IndTask.JAXBSchema.Doctor;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

public class DoctorConroller implements Initializable {
    List<DoctorArr> doctors = new ArrayList<>();

    public TextField surnSearch;
    public TextField dateSearch;
    public Button searchBtn;
    public TextArea searchRes;
    public TableView<TableRow> docTable;
    public TableColumn<TableRow, String> surnCol;
    public TableColumn<TableRow, String> specCol;
    public TableColumn<TableRow, String> dateCol;
    public TableColumn<TableRow, Integer> shiftCol;
    public TableColumn<TableRow, Integer> countCol;
    public TextField surnAdd;
    public TextField specAdd;
    public TextField dateAdd;
    public TextField shiftAdd;
    public TextField countAdd;
    public Button addBtn;

    public class TableRow{

        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        SimpleStringProperty surn,spec, date;
        SimpleIntegerProperty shift,count;
        public TableRow(String surn,String spec,Date date,int shift,int count){
            this.surn = new SimpleStringProperty(surn);
            this.spec = new SimpleStringProperty(spec);
            this.date = new SimpleStringProperty(format.format(date));
            this.shift = new SimpleIntegerProperty(shift);
            this.count = new SimpleIntegerProperty(count);
        }

        public String getSurn(){return surn.get();}
        public String getSpec(){return spec.get();}
        public String getDate(){return date.get();}
        public int getShift(){return shift.get();}
        public int getCount(){return count.get();}
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        docTableInit();
    }

    private void docTableInit() {
        //initializing rows
        ObservableList<TableRow> rows = FXCollections.observableArrayList();
        for (DoctorArr dct : doctors) {
            rows.add(new TableRow(dct.getSurn(), dct.getSpec(), dct.getRec(0).getDay(),
                    dct.getRec(0).getShift(), dct.getRec(0).getCount()));
            for (int i = 1; i < dct.getLength(); i++) {
                rows.add(new TableRow("", "", dct.getRec(i).getDay(),
                        dct.getRec(i).getShift(), dct.getRec(i).getCount()));
            }
        }
        docTable.setItems(rows);
        surnCol.setCellValueFactory(new PropertyValueFactory<>("surn"));
        specCol.setCellValueFactory(new PropertyValueFactory<>("spec"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        shiftCol.setCellValueFactory(new PropertyValueFactory<>("shift"));
        countCol.setCellValueFactory(new PropertyValueFactory<>("count"));
    }

    private void readFromFile(String path){
        doctors = new ArrayList<>();
        try {
            Document doc;
            DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            doc = db.parse(new File(path));
            Node rootNode = doc.getDocumentElement();
            mainLoop:
            for (int i = 0; i < rootNode.getChildNodes().getLength(); i++) {
                Node doctorNode = rootNode.getChildNodes().item(i);
                if (doctorNode.getNodeName().equals("Doctor")) {
                    DoctorArr doctor = new DoctorArr(doctorNode.getAttributes().getNamedItem("Surname").getNodeValue(),
                            doctorNode.getAttributes().getNamedItem("Speciality").getNodeValue());
                    for (int j = 0; j < doctorNode.getChildNodes().getLength(); j++) {
                        Node reception = doctorNode.getChildNodes().item(j);
                        if (reception.getNodeName().equals("Reception")) {
                            doctor.addRec(new Reception(reception.getAttributes().getNamedItem("Date").getNodeValue(),
                                    Integer.parseInt(reception.getAttributes().getNamedItem("Shift").getNodeValue()),
                                    Integer.parseInt(reception.getAttributes().getNamedItem("Count").getNodeValue())));
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
    private void doNew(javafx.event.ActionEvent event) {
        doctors = new ArrayList<>();
        docTableInit();
    }

    @FXML
    private void doOpen(javafx.event.ActionEvent event) {
        FileChooser fileChooser = getFileChooser("Open XML file");
        File file;
        if ((file = fileChooser.showOpenDialog(null)) != null) {
            try {
                readFromFile(file.getCanonicalPath());
                docTableInit();
                docTable.setItems(null);
                docTableInit();
            } catch (IOException e) {
                showError("No such file");
            }
        }
    }

    @FXML
    private void doAdd(javafx.event.ActionEvent event){
        if(surnAdd.getText().isEmpty() || specAdd.getText().isEmpty() || dateAdd.getText().isEmpty()
                || shiftAdd.getText().isEmpty()|| countAdd.getText().isEmpty()){
            throw new NullPointerException();
        }
        DoctorArr doc = new DoctorArr(surnAdd.getText(), specAdd.getText());
        for(DoctorArr dct : doctors){
            if(((doc.getSurn().indexOf(dct.getSurn()))==0 && doc.getSurn().length() == dct.getSurn().length())
                    &&( (doc.getSpec().indexOf(dct.getSpec()))==0&& doc.getSpec().length() == dct.getSpec().length())){
                dct.addRec(new Reception(dateAdd.getText(), Integer.parseInt(shiftAdd.getText())
                        , Integer.parseInt(countAdd.getText())));
                docTableInit();
                return;
            }
        }
        doc.addRec(new Reception(dateAdd.getText(), Integer.parseInt(shiftAdd.getText())
                , Integer.parseInt(countAdd.getText())));
        doctors.add(doc);
        docTableInit();
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

    public static FileChooser getFileChooser(String title) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("."));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("All files (*.*)", "*.*"));
        fileChooser.setTitle(title);
        return fileChooser;
    }

    @FXML
    private void about(javafx.event.ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About program...");
        alert.setHeaderText("Application shows information about doctors and their receptions");
        alert.setContentText("Version 1.0");
        alert.showAndWait();
    }
}
