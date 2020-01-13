package Lab3.IndTask;

import Lab1.GenLib.WrongUsage;
import Lab1.IndTask.*;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

public class DoctorConroller implements Initializable {
    private DoctorFacade facade = DoctorFacade.getInstance();

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

        public String getSurn(){
            if(surn == null){
                surn = new SimpleStringProperty();
            }
            return surn.get();
        }

        public String getSpec(){
            if(spec == null){
                spec = new SimpleStringProperty();
            }
            return spec.get();
        }

        public String getDate(){
            if(date == null){
                date = new SimpleStringProperty();
            }
            return date.get();
        }

        public int getShift(){
            if(shift == null){
                shift = new SimpleIntegerProperty();
            }
            return shift.get();
        }

        public int getCount(){
            if(count == null){
                count = new SimpleIntegerProperty();
            }
            return count.get();}
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        docTableInit();
    }

    private void docTableInit() {
        //initializing rows
        ObservableList<TableRow> rows = FXCollections.observableArrayList();
        for (DoctorArr dct : facade.getDoctors()) {
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

    @FXML
    private void doNew(javafx.event.ActionEvent event) {
        facade.doNew();
        docTableInit();
    }

    @FXML
    private void doOpen(javafx.event.ActionEvent event) {
        FileChooser fileChooser = getFileChooser("Open XML file");
        File file;
        if ((file = fileChooser.showOpenDialog(null)) != null) {
            try {
                facade.doOpen(file.getCanonicalPath());
                docTableInit();
            } catch (IOException e) {
                showError("No such file");
            }catch(WrongUsage ex){
                showError("Wrong shift or count");
            }
        }
    }

    @FXML
    private void doSave(javafx.event.ActionEvent event) {
        FileChooser fileChooser = getFileChooser("Save XML file");
        File file;
        if ((file = fileChooser.showSaveDialog(null)) != null) {
            try {
                facade.doSave(file.getCanonicalPath());
                showMessage("Results are saved");
            } catch (Exception e) {
                showError("Error write to file");
            }
        }
    }

    @FXML
    private void doAdd(javafx.event.ActionEvent event){
        try {
            facade.doAdd(surnAdd.getText(), specAdd.getText(), dateAdd.getText(), shiftAdd.getText(), countAdd.getText());
        }catch(WrongUsage ex){
            showError("Wrong shift or count");
        }
        docTableInit();
    }

    @FXML
    private void doSearch(javafx.event.ActionEvent event) {
        try {
            searchRes.setText(facade.doSearch(surnSearch.getText(), dateSearch.getText()));
        }catch (NullPointerException ex){
            showError("Some field is empty");
        }
    }

    @FXML
    private void doDateSort(javafx.event.ActionEvent event) {
        facade.doDateSort();
        docTableInit();
    }

    @FXML
    private void doCountSort(javafx.event.ActionEvent event) {
        facade.doCountSort();
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
    private void doAbout(javafx.event.ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About program...");
        alert.setHeaderText("Application shows information about doctors and their receptions");
        alert.setContentText("Version 1.0");
        alert.showAndWait();
    }
}
