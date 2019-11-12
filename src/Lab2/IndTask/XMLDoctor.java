package Lab2.IndTask;

import Lab1.Ind_task.DoctorArr;
import Lab1.Ind_task.Reception;
import Lab2.IndTask.JAXBSchema.Doctor;

import javax.xml.bind.*;
import java.io.*;

public class XMLDoctor extends DoctorArr implements FileWorkInter {
    Doctor doc;
    JAXBContext jaxbContext;
    public void fileRead(String filename) {
        try {
            jaxbContext = JAXBContext.newInstance("Lab2.IndTask.JAXBSchema");
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            doc = (Doctor) unmarshaller.unmarshal(new FileInputStream
                    (filename));
            setSurn(doc.getSurname());
            setSpec(doc.getSpeciality());
            for(int i = 0; i < doc.getReception().size(); i++){
                Doctor.Reception jaxbRec = doc.getReception().get(i);
                Reception rec = new Reception
                        (jaxbRec.getDate(), jaxbRec.getShift(), jaxbRec.getCount());
                addRec(rec);
            }
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
    }

    public void fileWrite(String filename){
        try{
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(doc, new FileWriter
                    (filename));
        }catch(JAXBException | IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        XMLDoctor xmldoc = new XMLDoctor();
        xmldoc.fileRead("src\\Lab2\\IndTask\\Doctor.xml");
        System.out.println(xmldoc.toString());
        xmldoc.doc.setSurname("Pupkin");
        xmldoc.doc.setSpeciality("Dentist");
        xmldoc.doc.getReception().get(0).setDate("01.01.2000");
        xmldoc.doc.getReception().get(2).setShift(1);
        xmldoc.fileWrite("src\\Lab2\\IndTask\\DoctorJAXB.xml");
    }
}
