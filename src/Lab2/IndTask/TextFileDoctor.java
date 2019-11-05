package Lab2.IndTask;

import Lab1.Ind_task.DoctorArr;
import Lab1.Ind_task.Reception;

import java.io.*;
import java.util.*;
import java.text.*;

public class TextFileDoctor extends DoctorArr {
    TextFileDoctor(String surn, String spec) {
        super(surn, spec);
    }

    TextFileDoctor() {
        super();
    }

    void fileRead() {
            try {
                FileReader fr = new FileReader("indTaskSrc.txt");
                BufferedReader br = new BufferedReader(fr);
                String s = br.readLine();
                String name, spec;
                try {
                    StringTokenizer st = new StringTokenizer(s);
                    setSurn(st.nextToken());
                    setSpec(st.nextToken());
                    while((s = br.readLine()) != null){
                        st = new StringTokenizer(s);
                        st.nextToken();
                        String day = st.nextToken();
                        int shift = Integer.parseInt(st.nextToken());
                        int count = Integer.parseInt(st.nextToken());
                        addRec(new Reception(day, shift, count));
                    }
                } finally {
                    br.close();
                }
                } catch (IOException ex) {
                ex.printStackTrace();
            }
    }

    void fileWrite() {
        try {
            FileWriter fw = new FileWriter("indTaskDest.txt");
            PrintWriter pw = new PrintWriter(fw);
            pw.println(this.toString());
            pw.close();
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        TextFileDoctor doc = new TextFileDoctor();
        doc.fileRead();
        doc.fileWrite();
    }
}