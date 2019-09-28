package Lab1.Hierarchy;

public class Employee extends Citizen {
    private String company;
    private String position;

    Employee(String name, String surname, String gender, int iq, String country, String status,
    String company, String position){
        super(name, surname, gender, iq, country, status);
        this.company = company;
        this.position = position;
    }

    public String toString() {
        return super.toString() + "; Company: " + company + "; Position: " + position;
    }
}
