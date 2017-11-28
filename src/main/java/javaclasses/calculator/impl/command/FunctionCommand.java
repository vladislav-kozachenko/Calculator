package javaclasses.calculator.impl.command;

import javaclasses.calculator.CalculationException;
import javaclasses.calculator.impl.Command;
import javaclasses.calculator.impl.EvaluationContext;
import javaclasses.calculator.impl.Function;

public class FunctionCommand implements Command{

    private Function function;

    public FunctionCommand(Function function) {
        this.function = function;
    }

    @Override
    public void execute(EvaluationContext context) throws CalculationException {
        context.pushFunction(function);
    }

}
