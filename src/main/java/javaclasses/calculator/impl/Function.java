package javaclasses.calculator.impl;

import javaclasses.calculator.CalculationException;

import java.util.List;

/**
 * Represents a function that accepts several arguments and produces a double result.
 */
public interface Function {

    double execute(List<Double> args, ErrorHandler errorHandler) throws CalculationException;

}
