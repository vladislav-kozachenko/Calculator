package javaclasses.calculator.impl;

import javaclasses.calculator.CalculationException;

import java.util.List;

/**
 * Represents a function that accepts several arguments and produces a double result.
 */
public abstract class Function {

    private int minArguments;
    private int maxArguments;

    public Function(int minArguments, int maxArguments) {
        this.minArguments = minArguments;
        this.maxArguments = maxArguments;
    }

    abstract public double execute(List<Double> args, ErrorHandler errorHandler) throws CalculationException;

    protected void validateArgumentsNumber(int argumentsNumber, ErrorHandler errorHandler) throws CalculationException {
        if (argumentsNumber < minArguments || argumentsNumber > maxArguments) {
            if (minArguments == maxArguments) {
                errorHandler.raiseError("Function requires " + minArguments + " arguments.");
            } else {
                errorHandler.raiseError(argumentsNumber
                        + " arguments is not allowed here. Use from "
                        + minArguments + " to "
                        + maxArguments + " arguments.");
            }
        }
    }

}
