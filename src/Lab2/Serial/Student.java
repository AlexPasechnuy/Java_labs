package Lab2.Serial;

import java.io.Serializable;

public class Student implements Serializable {
    private static final long serialVersionUID = 3286988671813276970L;

    private String name;
    private String gender;
    private int age;
    private long bookNumb;

    Student(String name, String gender, int age, long bookNumb){
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.bookNumb = bookNumb;
    }
    public String toString() {
        return name + ", " + gender + ", " + age + " years old, #" + bookNumb;
    }
}
