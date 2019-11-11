package Lab2.SAXandDOM;

import java.io.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class DOM {
    public static void main(String[] args) throws Exception{
        try {
            Document doc;
            DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            doc = db.parse(new File("src\\Lab2\\SAXandDOM\\Group.xml"));
            Node rootNode = doc.getDocumentElement();
            mainLoop:
            for (int i = 0; i < rootNode.getChildNodes().getLength(); i++) {
                Node studentsNode = rootNode.getChildNodes().item(i);
                if (studentsNode.getNodeName().equals("Students")) {
                    for (int j = 0; j < studentsNode.getChildNodes().getLength(); j++) {
                        Node studentNode = studentsNode.getChildNodes().item(j);
                        if (studentNode.getNodeName().equals("Student")) {
                            // Знаходимо атрибут за іменем:
                            if (studentNode.getAttributes().getNamedItem("Name").getNodeValue().equals("Vasya Pupkin")) {
                                studentNode.getAttributes().getNamedItem("Name").setNodeValue("Vapa Puskin");
                                studentNode.getAttributes().getNamedItem("Age").setNodeValue("228");
                                studentNode.getAttributes().getNamedItem("BookNumb").setNodeValue("expelled");
                            }
                        }
                    }
                }
            }
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(new DOMSource(doc),
                    new StreamResult(new FileOutputStream(new File("src\\Lab2\\SAXandDOM\\NewGroup.xml"))));
        }
        catch (Exception  e) {
            e.printStackTrace();
        }
    }
}
