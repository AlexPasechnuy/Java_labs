package Lab1.Ind_task;

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
        String str = "Surname: " + getSurn() + "; Specialization: " + getSpec() + ";\n"
                + "Receptions:\n";
        for(int i = 0; i < getLength(); i++){
            str += getRec(i);
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
        return getSurn() == h.getSurn() && getSpec() == h.getSpec();
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

    public AbsRecept[] searchByDay(int day) {
        int counter = 0;
        for (int i = 0; i < getLength(); i++) {
            if (getRec(i).getDay() == day)
                counter++;
        }
        AbsRecept[] arr = new AbsRecept[counter];
        for (int i = 0; i < getLength(); i++) {
            counter = 0;
            if (getRec(i).getDay() == day) {
                arr[counter] = getRec(i);
                counter++;
            }
        }
        return arr;
    }

}
