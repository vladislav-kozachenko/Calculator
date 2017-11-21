package javaclasses.calculator.impl;

/**
 * Abstract mathematical operator.
 * @param <Op> is concrete binary operator.
 */
public interface BinaryOperator<Op extends BinaryOperator> extends Comparable<Op> {

    /**
     * Evaluates atomic mathematical expression.
     * @param leftOperand
     * @param rightOperand
     * @return the result of mathematical operation proceeded on operands.
     */
    double evaluate(double leftOperand, double rightOperand);
}
