package Lab1.Ind_task;

import Lab1.GenLib.WrongUsage;

public class Main {
    public static void main(String[] args) {
        //using array
        System.out.println("\n\nUsing array \n");
        DoctorArr arrdoc = new DoctorArr("Johnson", "Therapist");
        try {
            arrdoc.checkAndAddRec(new Reception("22.11.2019", 4, 5));
            arrdoc.checkAndAddRec(new Reception("22.11.2019", 1, 29));
            arrdoc.checkAndAddRec(new Reception("25.11.2019", 8, 4));
            arrdoc.checkAndAddRec(new Reception("27.11.2019", 2, 3));
            arrdoc.checkAndAddRec(new Reception("28.11.2019", 4, 5));
        }catch(WrongUsage ex){
            System.out.println("Wrong values of shift or count");
        }
        System.out.println(arrdoc);

        System.out.println("Doctors are equal:" + arrdoc.equals(new DoctorArr("Johnson", "Therapist")) + '\n');

        arrdoc.sortByDay();
        System.out.println("Sorted by day:\n" + arrdoc);

        arrdoc.sortByVisCount();
        System.out.println("Sorted by count of visitors:\n" + arrdoc);


        AbsRecept[] rec = arrdoc.searchByDay("22.11.2019");
        System.out.println(rec.length);
        for(int i = 0; i < rec.length; i++){
            System.out.println(rec[i].toString());
        }

        //using list
        System.out.println("\n\nUsing list \n");
        DoctorList listdoc = new DoctorList("Johnson", "Therapist");
        try {
        listdoc.checkAndAddRec(new Reception("22.11.2019", 4, 5));
        listdoc.checkAndAddRec(new Reception("25.11.2019", 8, 4));
        listdoc.checkAndAddRec(new Reception("27.11.2019", 2, 3));
        listdoc.checkAndAddRec(new Reception("28.11.2019", 4, 5));
        }catch(WrongUsage ex){
            System.out.println("Wrong values of shift or count");
        }
        System.out.println(listdoc);

        System.out.println("Doctors are equal:" + listdoc.equals(new DoctorList("Johnson", "Therapist")) + '\n');

        listdoc.sortByDay();
        System.out.println("Sorted by day:\n" + listdoc);

        listdoc.sortByVisCount();
        System.out.println("Sorted by count of visitors:\n" + listdoc);
    }
}