package javaclasses.calculator.impl.function;

import javaclasses.calculator.impl.Function;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class FunctionFactory {

    private final Map<String, Function> functions = new HashMap<String, Function>() {{
        put("max", new MaxFunction(2, Integer.MAX_VALUE));
        put("min", new MinFunction(2, Integer.MAX_VALUE));
        put("sum", new SumFunction(2, Integer.MAX_VALUE));
        put("avg", new AverageFunction(2, Integer.MAX_VALUE));
        put("log10", new Logarithm10Function(1, 1));
        put("pi", new PiFunction(0, 0));
        put("print", new PrintFunction(1, 1));
    }};

    public Function getFunction(String name){
        return functions.get(name);
    }

    public Set<String> getFunctionNames(){
        return functions.keySet();
    }

}
