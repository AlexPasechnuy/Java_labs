package Lab1.Hierarchy;

class Student extends Citizen {
    private String univer;
    private String group;
    private int bookNumb;

    Student(String name, String surname, String gender, int iq, String country, String status,
            String univer, String group, int bookNumb){
        super(name, surname, gender, iq, country, status);
        this.univer = univer;
        this.group = group;
        this.bookNumb = bookNumb;
    }
    public String toString() {
        return super.toString() + "; University: " + univer + "; Group: " + group +
                "; Record book number: " + bookNumb;
    }
}
