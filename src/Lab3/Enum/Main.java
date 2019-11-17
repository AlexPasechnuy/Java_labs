package Lab3.Enum;

public class Main {
    public static void main(String[] args) {
        Month mth = Month.APRIL;
        System.out.println("Previous: " + mth.getPrev().toString());
        System.out.println("Next: " +mth.getNext().toString() + '\n' + "All months:");
        mth.printAll();
        System.out.println(mth.getSeason());
    }
}
