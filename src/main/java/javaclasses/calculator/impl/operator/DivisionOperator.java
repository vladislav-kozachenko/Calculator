package javaclasses.calculator.impl.operator;

public class DivisionOperator extends AbstractBinaryOperator {

    DivisionOperator(Priority priority) {
        super(priority);
    }

    /**
     *
     * @return division of leftOperand on rightOperand
     */
    @Override
    public double evaluate(double leftOperand, double rightOperand) {
        return leftOperand / rightOperand;
    }
}
