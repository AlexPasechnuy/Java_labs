package Lab1.GenLib;

import java.lang.reflect.Array;
import java.util.Arrays;

public class GenLib<T> {

    public static<T> void swapGrp(T[] arr, int firstStart, int firstEnd, int secStart, int secEnd ) throws WrongUsage{
        if(firstStart < 0 || secEnd > arr.length || firstStart > firstEnd || secStart > secEnd || secStart < firstEnd){
            throw new WrongUsage();
        }
        int firstSize = firstEnd - firstStart + 1;
        int midSize = secStart - firstEnd - 1;
        int secSize = secEnd - secStart + 1;

        Object[] first = new Object[firstSize];
        Object[] mid = new Object[midSize];
        Object[] second = new Object[secSize];

        System.arraycopy(arr, firstStart, first, 0 ,  firstSize);
        System.arraycopy(arr, firstEnd + 1, mid, 0 ,  midSize);
        System.arraycopy(arr, secStart, second, 0 ,  secSize);

        System.arraycopy(second, 0, arr, firstStart,  secSize);
        System.arraycopy(mid, 0, arr, firstStart + secSize,  midSize);
        System.arraycopy(first, 0, arr, firstStart + secSize + midSize,  firstSize);
    }

    public static<T> void swapNeighb(T[] arr){
        T temp;
        for(int i = 0; i < ((arr.length/2)*2); i+=2){
            temp = arr[i];
            arr[i] = arr[i+1];
            arr[i+1] = temp;
        }
    }

    public static<T> void insertIn(){

    }

    public static<T> void replOtherArr(){

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

    public void print(T[] arr){
        String str = "";
        for (int i = 0; i < arr.length; i++){
            System.out.print(arr[i]);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        GenLib utils = new GenLib();
        String[] arr1 = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k"};
        String[] arr2 = {"l", "m" , "n", "o", "p", "q", "r", "s", "t", "u", "v"};
        try {
            swapGrp(arr1, 2, 5, 7, 9);
        }catch(WrongUsage wr) {
            System.out.println(wr.getMessage());
        }
        utils.print(arr1);
        swapNeighb(arr1);
        utils.print(arr1);
    }
}
