package javaclasses.calculator.impl.command;

import javaclasses.calculator.CalculationException;
import javaclasses.calculator.impl.BinaryOperator;
import javaclasses.calculator.impl.Command;
import javaclasses.calculator.impl.EvaluationContext;

public class ArgumentSeparatorCommand implements Command{

    @Override
    public void execute(EvaluationContext context) throws CalculationException {
        context.pushArgumentSeparator();
    }
}
