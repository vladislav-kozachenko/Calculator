package javaclasses.calculator.impl.operator;

public class DivisionOperator extends AbstractBinaryOperator {

    DivisionOperator(Priority priority) {
        super(priority);
    }

    @Override
    public double evaluate(double leftOperand, double rightOperand) {
        return leftOperand / rightOperand;
    }
}
