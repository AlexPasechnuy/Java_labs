package Lab2.Serial;

import java.io.*;

public class BinDeser {
    public static void main(String[] args) throws ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(
                new FileInputStream("src\\Lab2\\Serial\\binSer.dat"))) {
            Group gr = (Group) in.readObject();
            for (int i = 0; i < gr.getLength(); i++) {
                System.out.println(gr.getStud(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
