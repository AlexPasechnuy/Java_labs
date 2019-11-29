package Lab3.DiffWords;

import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class DiffWords {
    Set<String> words = new TreeSet<>();

    DiffWords(String str){
        stringToSet(str);
    }

    DiffWords(){
    }

    public void read(){
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        stringToSet(str);
    }

    public void stringToSet(String str){
        StringTokenizer st = new StringTokenizer(str);
        while(st.hasMoreTokens()){
            words.add(st.nextToken());
        }
    }

    public Set getSet(){
        return words;
    }

    public static void main(String[] args) {
        DiffWords wrds = new DiffWords();
        System.out.println(wrds.getSet());
    }
}
