package javaclasses.calculator.impl.operator;

public class MinusOperator extends AbstractBinaryOperator {

    MinusOperator(Priority priority) {
        super(priority);
    }

    @Override
    public double evaluate(double leftOperand, double rightOperand) {
        return leftOperand - rightOperand;
    }
}
