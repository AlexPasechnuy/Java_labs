package Lab2.Serial;

import java.io.Serializable;

public class Student implements Serializable {
    private static final long serialVersionUID = 3286988671813276970L;

    private String name;
    private int age;
    private int bookNumb;

    public Student(String name, int age, int bookNumb){
        this.name = name;
        this.age = age;
        this.bookNumb = bookNumb;
    }

    public Student(){
        this.name = "";
        this.age = 0;
        this.bookNumb = 0;
    }

    public String toString() {
        return name + ", " + age + " years old, #" + bookNumb;
    }

    public String getName(){return name;}
    public int getAge(){return age;}
    public int getBookNumb(){return bookNumb;}

    public void setName(String name){this.name = name;}
    public void setAge(int age){this.age = age;}
    public void setBookNumb(int booknumb){this.bookNumb = booknumb;}
}
