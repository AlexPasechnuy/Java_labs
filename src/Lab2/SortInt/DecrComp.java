package Lab2.SortInt;

import java.util.Comparator;

public class DecrComp implements Comparator<Integer> {
    @Override
    public int compare(Integer a1, Integer a2) {
        return Integer.compare(a2,a1);
    }
}
