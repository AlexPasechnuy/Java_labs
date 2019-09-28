package Lab1.Hierarchy;

public class Human {
    private String name;
    private String surname;
    private String gender;
    private int iq;

    Human(String name, String surname, String gender, int iq){
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.iq = iq;
    }

    @Override
    public String toString(){
       return "Name: " + name + "; Surname: " + surname + "; Gender: " + gender + "; IQ: " + iq;
    }
}
