package Lab3.Enum;

enum Month {
    JANUARY(31), FEBRUARY(28), MARCH(31), APRIL(30), MAY(31), JUNE(30),JULY(31),
    AUGUST(31),SEPTEMBER(30),OCTOBER(31),NOVEMBER(30),DECEMBER(31);

    int days;

    private Month(int days){
        this.days = days;
    }

    public Month getPrev() {
        Month prev = DECEMBER;
        for(Month m : Month.values()){
            if(m.name() == name()){break;}
            prev = m;
        }
        return prev;
    }

    public Month getNext(){
        Month prev = DECEMBER;
        Month next = DECEMBER;
        for(Month m : Month.values()){
            if(prev.name() == name()){
                next = m;
                break;
            }
            prev = m;
        }
        return next;
    }

    public String getSeason(){
        switch (this){
            case DECEMBER:
            case JANUARY:
            case FEBRUARY:
                return "Winter";
            case MARCH:
            case APRIL:
            case MAY:
                return "Spring";
            case JUNE:
            case JULY:
            case AUGUST:
                return "Summer";
            case SEPTEMBER:
            case OCTOBER:
            case NOVEMBER:
                return "Autumn";
            default:
                return "Error";
        }
    }

    public static void printAll(){
        for(Month m : Month.values()){
            System.out.println( m.toString());
        }
    }

    @Override
    public String toString() {
        return "In " + name() + ' ' + days + " days.";
    }
}
