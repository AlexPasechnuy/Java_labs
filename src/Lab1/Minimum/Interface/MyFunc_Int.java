package Lab1.Minimum.Interface;

import Lab1.Minimum.WrongMinimum;

public class MyFunc_Int implements IntFunction {
    public double func(int x){
        return x*x + x;
    }

    public double findMin(int from, int to, int step) throws WrongMinimum {
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
