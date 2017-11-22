package javaclasses.calculator.impl;

import java.util.List;

/**
 * Represents a function that accepts several arguments and produces a double result.
 */
public interface Function {

    double execute(List<Double> args);

}
