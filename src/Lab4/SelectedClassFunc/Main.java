package Lab4.SelectedClassFunc;

import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) {
        try {
                Method method = Class.forName("Lab4.SelectedClassFunc.C1").getMethod("print");
                method.invoke(null);
        }catch(Exception ex){
            System.out.println("Error");
        }
    }
}
