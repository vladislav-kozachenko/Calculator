package javaclasses.calculator.impl.operator;

public class DivisionOperator extends AbstractBinaryOperator {

    public DivisionOperator(Priority priority) {
        super(priority);
    }

    /**
     *
     * @param leftOperand
     * @param rightOperand
     * @return division of leftOperand on rightOperand
     */
    @Override
    public double evaluate(double leftOperand, double rightOperand) {
        return leftOperand / rightOperand;
    }
}
