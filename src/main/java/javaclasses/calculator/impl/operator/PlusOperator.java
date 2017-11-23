package javaclasses.calculator.impl.operator;

public class PlusOperator extends AbstractBinaryOperator {

    PlusOperator(Priority priority) {
        super(priority);
    }

    @Override
    public double evaluate(double leftOperand, double rightOperand) {
        return leftOperand + rightOperand;
    }
}
