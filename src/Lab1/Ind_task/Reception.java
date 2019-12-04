package Lab1.Ind_task;
import Lab1.GenLib.WrongUsage;
import Lab2.SortInt.NonPositiveException;

import java.util.*;
import java.text.*;

public class Reception extends AbsRecept {
    private Date day;
    private int shift;
    private int count;
    SimpleDateFormat ft = new SimpleDateFormat ("dd.MM.yyyy");

    public Reception(String day, int shift, int count) throws WrongUsage {
        try {
            this.day = ft.parse(day);
        }catch (ParseException e) {
            return;
        }
        if(count < 0 || shift < 1 || shift > 4){throw new WrongUsage();}
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

    public void setShift(int shift) throws WrongUsage{
        if(shift < 1 || shift > 4){throw new WrongUsage();}
        this.shift = shift;
    }

    public void setCount(int count)throws WrongUsage{
        if(count < 0){throw new WrongUsage();}
        this.count = count;
    }

}
