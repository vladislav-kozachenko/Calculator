package javaclasses.calculator.impl.operator;

public class ExponentiationOperator extends AbstractBinaryOperator {

    ExponentiationOperator(Priority priority) {
        super(priority);
    }

    /**
     * @return exponentiation of leftOperand on rightOperand.
     */
    @Override
    public double evaluate(double leftOperand, double rightOperand) {
        return Math.pow(leftOperand, rightOperand);
    }
}
