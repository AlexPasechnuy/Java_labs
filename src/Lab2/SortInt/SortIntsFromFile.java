package Lab2.SortInt;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SortIntsFromFile {
    ArrayList<Integer> arr = new ArrayList<Integer>();

    void readFromFile(String filename){
        try {
            FileReader fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr);
            String s = br.readLine();
            try{
                StringTokenizer st = new StringTokenizer(s);
                while (st.hasMoreTokens()){
                    arr.add(Integer.parseInt(st.nextToken()));
                }
            }finally{
                br.close();
            }
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    void writeToFile(String filename) {
        try {
            FileWriter fw = new FileWriter(filename);
            PrintWriter pw = new PrintWriter(fw);
            for(int i = 0; i < arr.size(); i++){
                pw.print(arr.get(i)+ " ");
            }
            pw.close();
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }

    void sortByDecr(){
        java.util.Collections.sort(arr, new DecrComp());
    }

    void sortByIncDigSum(){
        java.util.Collections.sort(arr, new IncrDigitsSumComp());
    }

    public static void main(String[] args) {
        SortIntsFromFile sort = new SortIntsFromFile();
        sort.readFromFile("src/Lab2/SortInt/Files/Input.txt");
        sort.sortByDecr();
        sort.writeToFile("src/Lab2/SortInt/Files/DecrOutput.txt");
        sort.sortByIncDigSum();
        sort.writeToFile("src/Lab2/SortInt/Files/IncrDigSumOutput.txt");
    }
}
