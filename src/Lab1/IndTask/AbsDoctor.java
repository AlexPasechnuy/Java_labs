package Lab1.IndTask;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class AbsDoctor {

    public abstract String getSurn();

    public abstract String getSpec();

    public abstract int getLength();

    public abstract void setSurn(String surn);

    public abstract void setSpec(String spec);

    public abstract AbsRecept getRec(int num);

    public abstract void addRec(AbsRecept rec);

    public abstract void sortByDay();

    public abstract void sortByVisCount();

    @Override
    public String toString() {
        String str = getSurn() + " " + getSpec() + "\n";
        for(int i = 0; i < getLength(); i++){
            str += "- " + getRec(i).toString() + "\n";
        }
        return str;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof AbsDoctor)) {
            return false;
        }
        AbsDoctor h = (AbsDoctor) obj;
        return (getSurn() == h.getSurn()) && (getSpec() == h.getSpec());
    }

    public void checkAndAddRec(AbsRecept rec) {
        for (int i = 0; i < getLength(); i++) {
            if (getRec(i).equals(rec)) {
                System.out.println("Records are equal!\n");
                return;
            }
        }
        addRec(rec);
    }

    public AbsRecept[] searchByDay(String strDay) {
        SimpleDateFormat ft = new SimpleDateFormat ("dd.MM.yyyy");
        int counter = 0;
        for (int i = 0; i < getLength(); i++) {
            String strArrDay = ft.format(getRec(i).getDay());
            if (strArrDay.indexOf(strDay) == 0 && strArrDay.length() == strDay.length())
                counter++;
        }
        AbsRecept[] arr = new AbsRecept[counter];
        counter = 0;
        for (int i = 0; i < getLength(); i++) {
            String strArrDay = ft.format(getRec(i).getDay());
            if (strArrDay.indexOf(strDay) == 0 && strArrDay.length() == strDay.length()) {
                arr[counter] = getRec(i);
                counter++;
            }
        }
        return arr;
    }

}
