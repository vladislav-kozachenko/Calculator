package javaclasses.calculator.impl;

import javaclasses.calculator.CalculationException;

public interface Command {
    void execute(EvaluationContext context) throws CalculationException;
}
