package Lab1.Ind_task;

public class Main {
    public static void main(String[] args) {
        DoctorArr doc = new DoctorArr("Johnson", "Therapist");
        doc.checkAndAddRec(new Reception(3, 4, 5));
        doc.checkAndAddRec(new Reception(7, 8, 4));
        doc.checkAndAddRec(new Reception(1, 2, 3));
        doc.checkAndAddRec(new Reception(3, 4, 5));
        System.out.println(doc);
        DoctorArr doct = new DoctorArr("Johnson", "Therapist");
        System.out.println(doc.equals(doct));
        doc.sortByDay();
        System.out.println("Sorted by day:\n" + doc);
        doc.sortByVisCount();
        System.out.println("Sorted by count of visitors:\n" + doc);
    }
}