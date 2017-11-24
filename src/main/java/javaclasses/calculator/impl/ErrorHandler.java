package javaclasses.calculator.impl;

import javaclasses.calculator.CalculationException;

public interface ErrorHandler {
    void raiseError(String message) throws CalculationException;
}
