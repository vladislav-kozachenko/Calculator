package javaclasses.calculator.impl;

import javaclasses.calculator.CalculationException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Context for a function that may be executed from calculator.
 */
public class FunctionContext {

    private Function function;
    private List<Double> arguments = new ArrayList<>();
    private int bracketPosition;

    public Function getFunction() {
        return function;
    }

    public FunctionContext(Function function, int bracketPosition) {
        this.function = function;
        this.bracketPosition = bracketPosition;
    }

    public void addArgument(double argument){
        arguments.add(argument);
    }

    public Optional<Double> execute(ErrorHandler errorHandler) throws CalculationException {
        return function.execute(arguments, errorHandler);
    }

    public int getBracketPosition() {
        return bracketPosition;
    }
}
