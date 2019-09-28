package Lab1.Minimum;

import Lab1.Minimum.Abstract.MyFunc_Abs;
import Lab1.Minimum.FunctInterfaces.FunctInterFunc;
import Lab1.Minimum.Interface.IntFunction;
import Lab1.Minimum.Interface.MyFunc_Int;

import java.util.Scanner;

public class Main {

    public static int f(int x) {return x*x+x;}

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter minimum, maximum and step: ");
        int min = sc.nextInt();
        int max = sc.nextInt();
        int step = sc.nextInt();

        //using abstract////////////////////////////////////////////////////////////////////////
        MyFunc_Abs abs = new MyFunc_Abs();
        try {
            System.out.println("Abstract: " + abs.findMin(min, max, step));
        }catch(WrongMinimum wr){
            System.out.println(wr.getMessage());
        }

        //using interface///////////////////////////////////////////////////////////////////////
        MyFunc_Int inter = new MyFunc_Int();
        try {
            System.out.println("Interface: " + inter.findMin(min, max, step));
        }catch(WrongMinimum wr){
            System.out.println(wr.getMessage());
        }

        //using anonymous class/////////////////////////////////////////////////////////////////
        IntFunction anonClass = new IntFunction() {

            public double func(int x){
                return x*x + x;
            }

            public double findMin(int from, int to, int step) throws WrongMinimum {
                if(from > to) throw new WrongMinimum();
                double min = func(from);
                for (int i = from + step; i <= to; i += step){
                    double temp = func(i);
                    if(temp < min)
                        min = temp;
                }
                return min;
            }
        };
        try {
            System.out.println("Anonymous function: " + anonClass.findMin(min, max, step));
        }catch(WrongMinimum wr) {
            System.out.println(wr.getMessage());
        }

        //using lambda-expression///////////////////////////////////////////////////////////////
        try {
            System.out.println("Lambda-expression: " + FunctInterFunc.findMin(min, max, step, x ->  x*x + x));
        }catch(WrongMinimum wr) {
            System.out.println(wr.getMessage());
        }

        //using references to methods
        try {
            System.out.println("Reference to function: " + FunctInterFunc.findMin(min, max, step, Main::f));
        }catch(WrongMinimum wr) {
            System.out.println(wr.getMessage());
        }
    }
}
