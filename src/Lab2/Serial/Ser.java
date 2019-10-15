package Lab2.Serial;

import java.io.*;

public class Ser {
    public static void main(String[] args) {
        Group gr = new Group();
        gr.add(new Student("Johnny Sins",18,1342567));
        gr.add(new Student("Danika Mori",18,4618465));
        gr.add(new Student("Rocco Siffredi",18,5649189));
        for(int i = 0; i < gr.getLength(); i++){
            System.out.println(gr.getStud(i));
        }
        try (ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream("temp.dat"))) {
            out.writeObject(gr);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
