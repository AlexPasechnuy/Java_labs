package Lab1.Ind_task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DoctorList extends AbsDoctor {
    List<AbsRecept> arr = new ArrayList<AbsRecept>();

    private String surn;
    private String spec;

    DoctorList(String surn, String spec){
        this.surn = surn;
        this.spec = spec;
    }

    public String getSurn(){return surn;}

    public String getSpec(){return spec;}

    public void setSurn(String surn){this.surn = surn;}

    public void setSpec(String spec){this.spec = spec;}

    public AbsRecept getRec(int num){return arr.get(num);}

    public void sortByDay(){
        Collections.sort(arr);
    }

    public void sortByVisCount(){
        Collections.sort(arr, new VisCountComp());
    }

    public void addRec(AbsRecept rec){
        arr.add(rec);
    }

    public int getLength(){
        return arr.size();
    }
}
