package javaclasses.calculator;

/**
 * Public API of a calculator.
 */
public interface Calculator {

    /**
     *
     * @param expression is a math expression may be solved.
     * @return the result of expression.
     */
    double calculate(String expression) throws CalculationException;

}
