package Lab1.Ind_task;

public class Main {
    public static void main(String[] args) {
        //using array
        System.out.println("\n\nUsing array \n");

        DoctorArr arrdoc = new DoctorArr("Johnson", "Therapist");
        arrdoc.checkAndAddRec(new Reception(3, 4, 5));
        arrdoc.checkAndAddRec(new Reception(7, 8, 4));
        arrdoc.checkAndAddRec(new Reception(1, 2, 3));
        arrdoc.checkAndAddRec(new Reception(3, 4, 5));
        System.out.println(arrdoc);

        System.out.println("Doctors are equal:" + arrdoc.equals(new DoctorArr("Johnson", "Surgeon")) + '\n');

        arrdoc.sortByDay();
        System.out.println("Sorted by day:\n" + arrdoc);

        arrdoc.sortByVisCount();
        System.out.println("Sorted by count of visitors:\n" + arrdoc);

        //using list
        System.out.println("\n\nUsing list \n");
        DoctorList listdoc = new DoctorList("Johnson", "Therapist");
        listdoc.checkAndAddRec(new Reception(3, 4, 5));
        listdoc.checkAndAddRec(new Reception(7, 8, 4));
        listdoc.checkAndAddRec(new Reception(1, 2, 3));
        listdoc.checkAndAddRec(new Reception(3, 4, 5));
        System.out.println(listdoc);

        System.out.println("Doctors are equal:" + listdoc.equals(new DoctorList("Johnson", "Surgeon")) + '\n');

        listdoc.sortByDay();
        System.out.println("Sorted by day:\n" + listdoc);

        listdoc.sortByVisCount();
        System.out.println("Sorted by count of visitors:\n" + listdoc);
    }
}