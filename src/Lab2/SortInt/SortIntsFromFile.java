package Lab2.SortInt;

import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.StringTokenizer;

public class SortIntsFromFile {
    Integer[] arr = new Integer[0];

    void readFromFile(String filename) throws IOException, InputMismatchException{
        try {
            FileReader fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr);
            String s = br.readLine();
            try{
                StringTokenizer st = new StringTokenizer(s);
                while (st.hasMoreTokens()){
                    try {
                        int temp = Integer.parseInt(st.nextToken());
                        if (temp <= 0){throw new NonPositiveException();}
                        arr = Arrays.copyOf(arr, arr.length + 1);
                        arr[arr.length - 1] = temp;
                    }catch(NumberFormatException ex){
                        System.out.println("Incorrect format of some element. It will be skipped!");
                    }
                    catch(NonPositiveException ex){
                        System.out.println(ex.getMessage());
                    }
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
            for(int i = 0; i < arr.length; i++){
                pw.print(arr[i]+ " ");
            }
            pw.close();
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }

    void sortByDecr(){
        java.util.Arrays.sort(arr, new DecrDigitsSumComp());
    }

    void sortByIncDigSum(){
        java.util.Arrays.sort(arr, new IncrDigitsSumComp());
    }

    public static void main(String[] args) {
        try {
            SortIntsFromFile sort = new SortIntsFromFile();
            sort.readFromFile("src/Lab2/SortInt/Files/Input.txt");
            sort.sortByDecr();
            sort.writeToFile("src/Lab2/SortInt/Files/DecrDigSumOutput.txt");
            sort.sortByIncDigSum();
            sort.writeToFile("src/Lab2/SortInt/Files/IncrDigSumOutput.txt");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (InputMismatchException e) {
            e.printStackTrace();
        }
    }
}
