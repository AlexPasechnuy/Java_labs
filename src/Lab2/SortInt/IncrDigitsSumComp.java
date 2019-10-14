package Lab2.SortInt;

import java.util.Comparator;

public class IncrDigitsSumComp implements Comparator<Integer> {
    @Override
    public int compare(Integer a1, Integer a2) {
        int s1 = 0;
        while(a1 != 0){
            //Суммирование цифр числа
            s1 += (a1 % 10);
            a1/=10;
        }
        int s2 = 0;
        while(a2 != 0){
            //Суммирование цифр числа
            s2 += (a2 % 10);
            a2/=10;
        }
        return Integer.compare(s1,s2);
    }
}
