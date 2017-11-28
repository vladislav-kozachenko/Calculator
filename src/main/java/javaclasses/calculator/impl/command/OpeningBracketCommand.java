package javaclasses.calculator.impl.command;

import javaclasses.calculator.CalculationException;
import javaclasses.calculator.impl.Command;
import javaclasses.calculator.impl.EvaluationContext;

public class OpeningBracketCommand implements Command{
    @Override
    public void execute(EvaluationContext context) throws CalculationException {
        context.pushOpeningBracket();
    }
}
