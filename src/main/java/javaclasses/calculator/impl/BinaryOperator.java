package javaclasses.calculator.impl;

/**
 * Abstract mathematical operator.
 */
public interface BinaryOperator extends Comparable<BinaryOperator> {

    /**
     * Evaluates atomic mathematical expression.
     * @param leftOperand
     * @param rightOperand
     * @return the result of mathematical operation proceeded on operands.
     */
    double evaluate(double leftOperand, double rightOperand);
}
