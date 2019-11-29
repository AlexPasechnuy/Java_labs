package Lab3.Dictionary;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Callback;

import java.io.*;
import java.net.URL;
import java.util.*;

public class DictConroller implements Initializable {
    private HashMap<String,String> dict;
    private String fileName = "src/Lab3/Dictionary/Source.txt";
    public TextField searchWord;
    public Button searchBtn;
    public TableView tableSearch;
    public TableColumn<Map.Entry<String, String>, String> rusSearchColumn;
    public TableColumn<Map.Entry<String, String>, String> engSearchColumn;
    public TableView dictTable;
    public TableColumn<Map.Entry<String, String>, String> rusColumn;
    public TableColumn<Map.Entry<String, String>, String> engColumn;
    public TextField rusWord;
    public TextField engWord;
    public Button addBtn;
    public Button saveBtn;

    @FXML
    private void search(){
        String word = searchWord.getText();
        if(!word.isEmpty()){
            HashMap<String, String> map = new HashMap<>();
            for(Map.Entry entry: dict.entrySet()){
                if((entry.getValue().toString().indexOf(word))==0 || (entry.getKey().toString().indexOf(word))==0){
                    map.put(entry.getKey().toString(), entry.getValue().toString());
                }
            }
            searchTableInit(map);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dict = new HashMap<>();

        readData();

        mainTableInit();
    }

    public void readData(){
        try {
            FileReader fr = new FileReader(fileName);
            Scanner sc = new Scanner(fr);
            StringTokenizer st;
            try {
                while(sc.hasNextLine()){
                    st = new StringTokenizer(sc.nextLine());
                    dict.put(st.nextToken(), st.nextToken());
                }
            }
            finally {
                sc.close();
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void addWord(){
        if(!(rusWord.getText().isEmpty() || engWord.getText().isEmpty())){
            dict.put(rusWord.getText(), engWord.getText());
            mainTableInit();
        }
    }

    public void save(){
        try{
            FileWriter fw = new FileWriter("src/Lab3/Dictionary/Source.txt");
            PrintWriter pw = new PrintWriter(fw);
            for(Map.Entry entry: dict.entrySet()){
                pw.println(entry.getKey().toString() + " " + entry.getValue().toString());
            }
            pw.close();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    private void searchTableInit(Map<String, String> map){
        tableSearch.setItems(FXCollections.observableArrayList(map.entrySet()));

        rusSearchColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<String, String>, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Map.Entry<String, String>, String> p) {

                return new SimpleStringProperty(p.getValue().getKey());
            }
        });

        engSearchColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<String, String>, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Map.Entry<String, String>, String> p) {
                return new SimpleStringProperty(p.getValue().getValue());
            }
        });
    }

    private void mainTableInit(){
        dictTable.setItems(FXCollections.observableArrayList(dict.entrySet()));

        rusColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<String, String>, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Map.Entry<String, String>, String> p) {

                return new SimpleStringProperty(p.getValue().getKey());
            }
        });

        engColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<String, String>, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Map.Entry<String, String>, String> p) {
                return new SimpleStringProperty(p.getValue().getValue());
            }
        });
    }
}
