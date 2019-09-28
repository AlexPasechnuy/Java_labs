package Lab1.Minimum.FunctInterfaces;

import Lab1.Minimum.WrongMinimum;

public interface FunctInterFunc {
    int func(int x);

    static int findMin(int from, int to, int step, FunctInterFunc f) throws WrongMinimum {
        if(from > to) throw new WrongMinimum();
        int min = f.func(from);
        for (int i = from + step; i <= to; i += step){
            int temp = f.func(i);
            if(temp < min)
                min = temp;
        }
        return min;
    }
}
