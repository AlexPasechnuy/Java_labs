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

    public static <T> T[] insert(T[] from, int fromPos, T[] to, int toPos, int num) throws WrongUsage{
        if(fromPos + num > from.length) {
            throw new WrongUsage();
        }
        T[] temp = Arrays.copyOf(to, to.length + num);
        System.arraycopy(from, fromPos, temp, toPos, num);
        System.arraycopy(to, toPos, temp, toPos + num, to.length - toPos);
        return temp;
    }

    public static<T> void replOtherArr(){

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
        try {
            arr1 = insert(arr2, 3, arr1, 4, 3);
        }catch(WrongUsage wr) {
            System.out.println(wr.getMessage());
        }
        utils.print(arr1);
    }
}
