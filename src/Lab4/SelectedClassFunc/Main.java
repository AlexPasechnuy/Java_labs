package Lab4.SelectedClassFunc;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try
        {
            Object o = Class.forName(new Scanner(System.in).next()).newInstance();
            if(o instanceof C1)
                ((C1) o).print();
            else if(o instanceof C2)
                ((C2) o).print();
            else if(o instanceof C3)
                ((C3) o).print();
        }catch(Exception ex){
            System.out.println("Error");
        }
    }
}
