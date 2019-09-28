package Lab1.Hierarchy;

public class main {
    public static void main(String[] args) {
        Human[] arr = new Human[4];
        arr[0] = new Human("Alex", "Nikonov", "Male", 115);
        arr[1] = new Citizen("John", "Cunningram", "Male", 118, "USA", "Businessman");
        arr[2] = new Student("Rachel", "Anderson", "Female", 116, "Britain", "Student"
        , "Cambridge", "LF046", 12456);
        arr[3] = new Employee("Sponge", "Bob", "Male", 0, "Sea", "dumb", "Crusty Crabs",
        "cook");
        for(int i = 0; i < 4; i++)
        {
            System.out.println(arr[i].toString());
        }
    }
}
