package javaclasses.calculator;

/**
 * Basic interface for calculator implementations.
 */
public interface Calculator {

    /**
     *
     * @param expression is a math expression may be solved.
     * @return the result of expression.
     */
    double calculate(String expression);

}
