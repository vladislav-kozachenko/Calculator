package javaclasses.calculator.impl.operator;

public class MultiplicationOperator extends AbstractBinaryOperator {

    public MultiplicationOperator(Priority priority) {
        super(priority);
    }

    @Override
    public double evaluate(double leftOperand, double rightOperand) {
        return leftOperand * rightOperand;
    }
}
