package Lab2.Serial;

import java.beans.XMLDecoder;
import java.io.*;

public class XMLDeser {
    public static void main(String[] args) {
        try (XMLDecoder xmlDecoder = new XMLDecoder(new FileInputStream("src\\Lab2\\Serial\\XMLSer.xml"))) {
            Group gr = (Group)xmlDecoder.readObject();
            for (int i = 0; i < gr.getLength(); i++) {
                System.out.println(gr.getStud(i));
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
