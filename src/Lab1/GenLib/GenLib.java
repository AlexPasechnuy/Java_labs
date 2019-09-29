package Lab1.GenLib;

import java.util.Arrays;

public class GenLib {
    public static <T> T[] swapGroups(int length, T... t) {
        Object[] temp = new Object[t.length];
        System.arraycopy(t, 0, temp, t.length - length, length);
        System.arraycopy(t, t.length - length, temp, 0, length);
        return (T[]) temp;
    }

    public static <T> T[] swapElements(T... t) {
        Object[] temp = new Object[t.length];
        for (int i = 0; i < t.length; i = i + 2) {
            if (i != t.length - 1) {
                temp[i] = t[i + 1];
                temp[i + 1] = t[i];
            } else {
                temp[i] = t[i];
            }
        }
        return (T[]) temp;
    }

    public static <T> T[] addGroup(int i, T[] in, T... t) {             ///[1,2,3,4,5] +(2)[6,7,8] = [1,2,6,7,8,4,5]
        Object[] temp = new Object[in.length + t.length];
        System.arraycopy(in, 0, temp, 0, i);
        System.arraycopy(t, 0, temp, i, t.length);
        System.arraycopy(in, i, temp, i + t.length, in.length - i);
        return (T[]) temp;
    }

    public static <T> T[] exchangeGroups(int start, T[] in, T... t) {
        Object[] temp = new Object[in.length];
        System.arraycopy(in, 0, temp, 0, start); // [1,2,3,4,5,6,7,8]+(2)[9.10,11]=[1,2,9,10,11,6,7,8]
        System.arraycopy(t, 0, temp, start, t.length);
        System.arraycopy(in, start + t.length, temp, start + t.length, in.length - (start + t.length));
        return (T[]) temp;
    }

    public static void main(String[] args) {
        GenLib utils = new GenLib();
        String[] arr = {"b", "c", "d", "e", "f"};

        System.out.println(Arrays.toString(exchangeGroups(2, arr, "f", "g" , "s")));
    }
}
