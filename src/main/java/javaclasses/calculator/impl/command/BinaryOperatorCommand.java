package javaclasses.calculator.impl.command;

import javaclasses.calculator.CalculationException;
import javaclasses.calculator.impl.BinaryOperator;
import javaclasses.calculator.impl.Command;
import javaclasses.calculator.impl.EvaluationContext;

public class BinaryOperatorCommand implements Command{

    private BinaryOperator operator;

    public BinaryOperatorCommand(BinaryOperator operator) {
        this.operator = operator;
    }

    @Override
    public void execute(EvaluationContext context) throws CalculationException {
        context.pushBinaryOperator(operator);
    }
}
