package Lab2.Serial;

import java.io.Serializable;
import java.util.Arrays;

public class Group implements Serializable {
    private static final long serialVersionUID = 2855527598653949834L;

    Student[] arr = new Student[0];

    void add(Student st){
        arr = Arrays.copyOf(arr, arr.length + 1);
        arr[arr.length-1] = st;
    }

    int getLength(){return arr.length;}

    Student getStud(int n){return arr[n];}
}
