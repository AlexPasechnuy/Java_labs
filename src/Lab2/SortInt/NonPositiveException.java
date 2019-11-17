package Lab2.SortInt;

public class NonPositiveException extends Exception {
    public NonPositiveException(){
        super("Number is not positive! It will be skipped");
    }
}