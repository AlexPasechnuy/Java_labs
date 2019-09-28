package Lab1.Minimum;

public class Function {
    public static double func(int x){
        return x*x + x;
    }

    public static double findMin(int from, int to, int step) throws WrongMinimum {
        if(from > to) throw new WrongMinimum();
        double min = func(from);
        for (int i = from + step; i <= to; i += step){
            double temp = func(i);
            if(temp < min)
                min = temp;
        }
        return min;
    }
}
