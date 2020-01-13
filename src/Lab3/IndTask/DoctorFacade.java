package Lab3.IndTask;

import Lab1.GenLib.WrongUsage;
import Lab1.IndTask.AbsRecept;
import Lab1.IndTask.DoctorArr;
import Lab1.IndTask.Reception;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class DoctorFacade {
    private static DoctorFacade instance = null;
    List<DoctorArr> doctors = new ArrayList<>();

    private DoctorFacade(){

    }

    public static DoctorFacade getInstance() {
        if (instance == null) {
            instance = new DoctorFacade();
        }
        return instance;
    }

    public List<DoctorArr> getDoctors(){
        if(doctors == null){
            doctors = new ArrayList<>();
        }
        return doctors;
    }


    public void doNew(){
        doctors = new ArrayList<>();
    }

    public void doOpen(String path){
        try {
            doNew();
            Document doc;
            DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            doc = db.parse(new File(path));
            Node rootNode = doc.getDocumentElement();
            DoctorArr doctor;
            mainLoop:
            for (int i = 0; i < rootNode.getChildNodes().getLength(); i++) {
                Node doctorNode = rootNode.getChildNodes().item(i);
                if (doctorNode.getNodeName().equals("Doctor")) {
                    doctor = new DoctorArr(doctorNode.getAttributes().getNamedItem("Surname").getNodeValue(),
                            doctorNode.getAttributes().getNamedItem("Speciality").getNodeValue());
                    for (int j = 0; j < doctorNode.getChildNodes().getLength(); j++) {
                        Node reception = doctorNode.getChildNodes().item(j);
                        if (reception.getNodeName().equals("Reception")) {
                            doctor.addRec(new Reception(reception.getAttributes().getNamedItem("Date").getNodeValue(),
                                    Integer.parseInt(reception.getAttributes().getNamedItem("Shift").getNodeValue()),
                                    Integer.parseInt(reception.getAttributes().getNamedItem("Count").getNodeValue())));
                        }
                    }
                    getDoctors().add(doctor);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void doSave(String path){
        try {
            SimpleDateFormat ft = new SimpleDateFormat ("dd.MM.yyyy");
            DocumentBuilderFactory dbf;
            DocumentBuilder db;
            Document doc;

            dbf = DocumentBuilderFactory.newInstance();
            db = dbf.newDocumentBuilder();
            doc = db.newDocument();

            Element dcts = doc.createElement("Doctors");
            for(DoctorArr dct: getDoctors()){
                Element xmlDoc = doc.createElement("Doctor");
                xmlDoc.setAttribute("Surname", dct.getSurn());
                xmlDoc.setAttribute("Speciality", dct.getSpec());
                for(int i = 0; i < dct.getLength(); i++){
                    Element rec = doc.createElement("Reception");
                    rec.setAttribute("Date",ft.format(dct.getRec(i).getDay()));
                    rec.setAttribute("Shift",Integer.toString(dct.getRec(i).getShift()));
                    rec.setAttribute("Count",Integer.toString(dct.getRec(i).getCount()));
                    xmlDoc.appendChild(rec);
                }
                dcts.appendChild(xmlDoc);
            }
            doc.appendChild(dcts);

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(new DOMSource(doc),
                    new StreamResult(new FileOutputStream(new File(path))));
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void doAdd(String surn, String spec, String date, String shift, String count) throws WrongUsage{
        if(surn.isEmpty() || spec.isEmpty() || date.isEmpty()
                || shift.isEmpty()|| count.isEmpty()){
            throw new NullPointerException();
        }
        DoctorArr doc = new DoctorArr(surn, spec);
        for(DoctorArr dct : getDoctors()){
            if(((doc.getSurn().indexOf(dct.getSurn()))==0 && doc.getSurn().length() == dct.getSurn().length())
                    &&( (doc.getSpec().indexOf(dct.getSpec()))==0&& doc.getSpec().length() == dct.getSpec().length())){
                    dct.addRec(new Reception(date, Integer.parseInt(shift)
                            , Integer.parseInt(count)));
                return;
            }
        }
            doc.addRec(new Reception(date, Integer.parseInt(shift)
                    , Integer.parseInt(count)));
        getDoctors().add(doc);
    }

    public void doCountSort(){
        for(DoctorArr doc : getDoctors()){
            doc.sortByVisCount();
        }
    }

    public void doDateSort(){
        for(DoctorArr doc : getDoctors()){
            doc.sortByDay();
        }
    }

    public String doSearch(String surn, String date){
        String result = "";
        if(surn.isEmpty() || date.isEmpty()){
            throw new NullPointerException();
        }
        for(DoctorArr doc : getDoctors()){
            if(doc.getSurn().indexOf(surn.toString()) == 0 && doc.getSurn().length() == surn.length()){
                result +=doc.getSurn() + ", " + doc.getSpec() + ":\n";
                AbsRecept[] arr =  doc.searchByDay(date);
                for(AbsRecept rec : arr){
                    System.out.println(rec.toString());
                    result += "     " + rec.toString() + '\n';
                }
            }
        }
        return result;
    }
}
