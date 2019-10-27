package Lab1.Comparator;

import java.util.Comparator;
import java.util.Scanner;

public class Triangle{
    int a, b, c;

    Triangle(int a, int b, int c){
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public double getArea(){
        return 0.25 * Math.sqrt((a+b+c)*(b+c-a)*(a+c-b)*(a+b-c));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Triangle[] arr = new Triangle[5];
        for(int i = 0; i < 5; i++){
            System.out.print("Enter sides of triangle #" + (i+1) + ": ");
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();
            arr[i] = new Triangle(a,b,c);
        }
        java.util.Arrays.sort(arr,  new TriangComp());
        System.out.println("Sorted by area: ");
        for(int i = 0; i < arr.length; i++){
            System.out.print(arr[i].getArea() + " ");
        }
    }
}