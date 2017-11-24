package javaclasses.calculator.impl.function;

import javaclasses.calculator.impl.Function;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class FunctionFactory {

    private final Map<String, Function> functions = new HashMap<String, Function>() {{
        put("max", new MaxFunction());
        put("sum", new SumFunction());
        put("avg", new AverageFunction());
        put("log10", new Logarithm10Function());
        put("pi", new PiFunction());
    }};

    public Function getFunction(String name){
        return functions.get(name);
    }

    public Set<String> getFunctionNames(){
        return functions.keySet();
    }

}
