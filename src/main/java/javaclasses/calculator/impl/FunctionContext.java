package javaclasses.calculator.impl;

import javaclasses.calculator.CalculationException;

import java.util.ArrayList;
import java.util.List;

/**
 * Context for a function that may be executed from calculator.
 */
public class FunctionContext {

    private Function function;
    private List<Double> arguments = new ArrayList<>();

    public Function getFunction() {
        return function;
    }

    public FunctionContext(Function function) {
        this.function = function;
    }

    public void addArgument(double argument){
        arguments.add(argument);
    }

    public double execute(ErrorHandler errorHandler) throws CalculationException {
        return function.execute(arguments, errorHandler);
    }
}
