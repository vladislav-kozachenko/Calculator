package javaclasses.calculator;

/**
 * Public API.
 * Represents a calculator that accepts String argument and produces a double result.
 */
public interface Calculator {

    /**
     *
     * @param expression is a math expression may be solved.
     * @return the result of expression.
     */
    double calculate(String expression) throws Exception;

}
