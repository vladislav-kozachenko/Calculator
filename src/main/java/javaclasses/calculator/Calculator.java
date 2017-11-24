package javaclasses.calculator;

/**
 * Represents a calculator that have method calculate.
 * Accepts mathematical expression in a String argument and produces a double result.
 *
 * @author Kozachenko
 */
public interface Calculator {

    /**
     * Evaluates the received mathematical expression.
     * @param expression is a math expression may be solved.
     * @return the result of expression.
     */
    double calculate(String expression) throws Exception;

}
