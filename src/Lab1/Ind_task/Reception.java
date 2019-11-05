package Lab1.Ind_task;
import java.util.*;
import java.text.*;

public class Reception extends AbsRecept {
    private Date day;
    private int shift;
    private int count;
    SimpleDateFormat ft = new SimpleDateFormat ("dd.MM.yyyy");

    public Reception(String day, int shift, int count) {
        try {
            this.day = ft.parse(day);
        }catch (ParseException e) {
            return;
        }
        this.shift = shift;
        this.count = count;
    }

    public Date getDay(){return day;}

    public int getShift(){return shift;}

    public int getCount(){return count;}

    public void setDay(String day){
        try {
            this.day = ft.parse(day);
        }catch (ParseException e) {
            return;
        }}

    public void setShift(int shift){this.shift = shift;}

    public void setCount(int count){this.count = count;}

}
