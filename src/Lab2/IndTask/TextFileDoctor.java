package Lab2.IndTask;

import Lab1.GenLib.WrongUsage;
import Lab1.IndTask.DoctorArr;
import Lab1.IndTask.Reception;

import java.io.*;
import java.util.*;

public class TextFileDoctor extends DoctorArr implements DoctorFileInter{
    TextFileDoctor(String surn, String spec) {
        super(surn, spec);
    }

    TextFileDoctor() {
        super();
    }

    public void fileRead(String filename) {
            try {
                FileReader fr = new FileReader(filename);
                BufferedReader br = new BufferedReader(fr);
                String s = br.readLine();
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
                        try{addRec(new Reception(day, shift, count));}
                        catch(WrongUsage ex){
                            System.out.println("Wrong shift or count");
                        }
                    }
                } finally {
                    br.close();
                }
                } catch (IOException ex) {
                ex.printStackTrace();
            }
    }

    public void fileWrite(String filename) {
        try {
            FileWriter fw = new FileWriter(filename);
            PrintWriter pw = new PrintWriter(fw);
            pw.println(this.toString());
            pw.close();
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        TextFileDoctor doc = new TextFileDoctor();
        doc.fileRead("src\\Lab2\\IndTask\\TextFiles\\indTaskSrc.txt");
        System.out.println(doc.toString());
        doc.fileWrite("src\\Lab2\\IndTask\\TextFiles\\indTaskDest.txt");
    }
}