package Lab1.Ind_task;

public class Reception extends AbsRecept {
    private int day;
    private int shift;
    private int count;

    Reception(int day, int shift, int count) {
        this.day = day;
        this.shift = shift;
        this.count = count;
    }

    public int getDay(){return day;}

    public int getShift(){return shift;}

    public int getCount(){return count;}

    public void setDay(int day){this.day = day;}

    public void setShift(int shift){this.shift = shift;}

    public void setCount(int count){this.count = count;}

}
