package Lab1.Minimum;

public class MethRefUse {
    double findMin(int from, int to, int step, FunctInterFunc f) throws WrongMinimum{
        if(from > to) throw new WrongMinimum();
        double min = f.func(from);
        for (int i = from + step; i <= to; i += step){
            double temp = f.func(i);
            if(temp < min)
                min = temp;
        }
        return min;
    };
}
