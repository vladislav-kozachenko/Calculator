package javaclasses.calculator.impl.operator;

public class ExponentiationOperator extends AbstractBinaryOperator {

    ExponentiationOperator(Priority priority) {
        super(priority);
    }

    @Override
    public double evaluate(double leftOperand, double rightOperand) {
        return Math.pow(leftOperand, rightOperand);
    }
}
