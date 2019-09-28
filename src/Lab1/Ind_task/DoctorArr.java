package Lab1.Ind_task;

public class DoctorArr extends AbsDoctor {
    private AbsRecept[] arr;

    private String surn;
    private String spec;

    DoctorArr(String surn, String spec){
        arr = new AbsRecept[0];
        this.surn = surn;
        this.spec = spec;
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
