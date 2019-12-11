package Lab1.IndTask;
import Lab1.GenLib.WrongUsage;
import Lab2.SortInt.NonPositiveException;

import java.text.SimpleDateFormat;
import java.util.*;

public abstract class AbsRecept implements Comparable<AbsRecept> {

    public abstract Date getDay();

    public abstract int getShift();

    public abstract int getCount();

    public abstract void setDay(String day);

    public abstract void setShift(int shift)throws WrongUsage;

    public abstract void setCount(int count)throws WrongUsage;

    @Override
    public String toString(){
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        return format.format(getDay()) + " " + getShift() + " " + getCount();
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof AbsRecept)) {
            return false;
        }
        AbsRecept h = (AbsRecept) obj;
        return getDay()==h.getDay() && getShift() == h.getShift() && getCount() == h.getCount();
    }

    public int compareTo(AbsRecept p){
        return (int)(getDay().getTime() - p.getDay().getTime());
    }
}
