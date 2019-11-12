package Lab2.SortInt;

import java.util.Comparator;

public class DecrDigitsSumComp implements Comparator<Integer> {
    @Override
    public int compare(Integer a1, Integer a2) {
        int s1 = 0;
        while(a1 != 0){
            s1 += (a1 % 10);
            a1/=10;
        }
        int s2 = 0;
        while(a2 != 0){
            s2 += (a2 % 10);
            a2/=10;
        }
        return Integer.compare(s2,s1);
    }
}
