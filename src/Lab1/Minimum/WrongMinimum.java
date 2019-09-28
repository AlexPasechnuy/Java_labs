package Lab1.Minimum;

public class WrongMinimum extends Exception {
    public WrongMinimum(){
        super("Minimum is greater than maximum");
    }
}
