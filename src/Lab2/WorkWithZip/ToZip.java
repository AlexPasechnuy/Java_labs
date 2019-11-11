package Lab2.WorkWithZip;

import java.io.*;
import java.util.zip.*;

import Lab2.Serial.*;

public class ToZip {
    public static void main(String[] args) {
        Group gr = new Group();
        gr.add(new Student("Johnny Sins",18,1342567));
        gr.add(new Student("Danika Mori",18,4618465));
        gr.add(new Student("Rocco Siffredi",18,5649189));
        for(int i = 0; i < gr.getLength(); i++){
            System.out.println(gr.getStud(i));
        }
        try (ZipOutputStream zOut = new ZipOutputStream(new FileOutputStream("src\\Lab2\\WorkWithZip\\Group.zip"));
             DataOutputStream out = new DataOutputStream(zOut)) {
            for (Student stud : gr.getStuds()) {
                ZipEntry zipEntry = new ZipEntry(stud.getName());
                zOut.putNextEntry(zipEntry);
                out.writeInt(stud.getAge());
                out.writeInt(stud.getBookNumb());
                zOut.closeEntry();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
