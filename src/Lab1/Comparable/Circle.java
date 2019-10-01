package Lab1.Comparable;

import java.util.Scanner;

public class Circle implements Comparable<Circle> {
    int radius;

    Circle(int radius){    this.radius = radius;    }

    public int getRadius(){ return radius;  }

    public void setRadius(int radius){  this.radius = radius;   }

    public int compareTo(Circle p){
        return getRadius() - p.getRadius();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Circle[] arr = new Circle[5];
        for(int i = 0; i < 5; i++){
            System.out.print("Enter radius of circle #" + (i+1) + ": ");
            int radius = sc.nextInt();
            arr[i] = new Circle(radius);
        }
        java.util.Arrays.sort(arr);
        System.out.println("Sorted by radius: ");
        for(int i = 0; i < arr.length; i++){
            System.out.print(arr[i].getRadius() + " ");
        }
    }
}
