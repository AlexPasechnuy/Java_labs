package Lab2.WorkWithZip;

import Lab2.Serial.*;

import java.io.*;
import java.util.zip.*;

public class FromZip {
    public static void main(String[] args) {
        try (ZipInputStream zIn = new ZipInputStream(new FileInputStream("Group.zip"));
             DataInputStream in = new DataInputStream(zIn)) {
            ZipEntry entry;
            while ((entry = zIn.getNextEntry()) != null) {
                System.out.println(entry.getName() + ", " + in.readInt() + " years old, #"
                        + in.readInt());
                zIn.closeEntry();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
