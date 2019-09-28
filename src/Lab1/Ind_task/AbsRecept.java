package Lab1.Ind_task;

public abstract class AbsRecept implements Comparable<AbsRecept> {

    public abstract int getDay();

    public abstract int getShift();

    public abstract int getCount();

    public abstract void setDay(int day);

    public abstract void setShift(int shift);

    public abstract void setCount(int count);

    @Override
    public String toString(){
        return "Day: " + getDay() + "; Shift: " + getShift() + "; Count of visitors: " + getCount() + ";\n";
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
        return getDay() - p.getDay();
    }
}
