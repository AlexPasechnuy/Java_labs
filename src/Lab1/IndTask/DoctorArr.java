package Lab1.IndTask;

import java.io.Serializable;

public class DoctorArr extends AbsDoctor implements Serializable {
    private static final long serialVersionUID = -8023307716074778326L;
    private AbsRecept[] arr;

    private String surn;
    private String spec;

    public DoctorArr(String surn, String spec){
        arr = new AbsRecept[0];
        this.surn = surn;
        this.spec = spec;
    }

    public DoctorArr(){
        arr = new AbsRecept[0];
        this.surn = null;
        this.spec = null;
    }

    public String getSurn(){return surn;}

    public String getSpec(){return spec;}

    public void setSurn(String surn){this.surn = surn;}

    public void setSpec(String spec){this.spec = spec;}

    public AbsRecept getRec(int num){return arr[num];}

    public void sortByDay(){
        java.util.Arrays.sort(arr);
    }

    public void sortByVisCount(){
        java.util.Arrays.sort(arr, new VisCountComp());
    }

    public void addRec(AbsRecept rec){
        AbsRecept[] temp = new AbsRecept[getLength()];
        for(int i = 0; i < getLength(); i++){
            temp[i] = arr[i];
        }
        arr = new AbsRecept[getLength() + 1];
        for(int i = 0; i < temp.length; i++){
            arr[i] = temp[i];
        }
        arr[getLength() - 1] = rec;
    }

    public int getLength(){
        return arr.length;
    }
}
