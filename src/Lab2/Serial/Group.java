package Lab2.Serial;

import java.io.Serializable;
import java.util.Arrays;

public class Group implements Serializable {
    private static final long serialVersionUID = 2855527598653949834L;

    Student[] arr;

    public Group(){
        arr = new Student[0];
    }

    public void add(Student st) {
        arr = Arrays.copyOf(arr, arr.length + 1);
        arr[arr.length - 1] = st;
    }

    public int getLength() {
        return arr.length;
    }

    public Student getStud(int n) {
        return arr[n];
    }

    public Student[] getStuds() {
        return arr;
    }

    public void setStuds(Student[] studs) {
        arr = studs;
    }
}
