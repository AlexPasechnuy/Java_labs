package MathExpr;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.util.Scanner;

public class Solver {
    public static Number f(String function){
        ScriptEngineManager factory = new ScriptEngineManager();
        ScriptEngine engine=factory.getEngineByName("JavaScript");

        Object result=null;
        try {
            result = engine.eval(function+";");
        }
        catch (Exception e){
            System.out.println("Not a function");
        }
        System.out.print(function);
        return (Number) result;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter expression to solve: ");
        String f1 = scanner.nextLine();
        System.out.println(" = " + f(f1));
    }
}
