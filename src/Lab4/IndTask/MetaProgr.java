package Lab4.IndTask;

import Lab1.GenLib.WrongUsage;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class MetaProgr {
    public static double f(String function, double x) throws WrongUsage{
        ScriptEngineManager factory = new ScriptEngineManager();
        ScriptEngine engine=factory.getEngineByName("JavaScript");
        engine.put("x",x);
        Object result=null;
        try {
            result = engine.eval(function+";");

        }
        catch (Exception e){
            throw new WrongUsage();
        }
        return (double) result;
    }
}
