package Lab1.Hierarchy;

class Citizen extends Human {
    private String country;
    private String status;

    Citizen(String name, String surname, String gender, int iq,
            String country, String status){
        super(name, surname, gender, iq);
        this.country = country;
        this.status = status;
    }
    public String toString() {
        return super.toString() + "; Country: " + country + "; Status: " + status;
    }
}
