package javaclasses.calculator.impl.command;

import javaclasses.calculator.CalculationException;
import javaclasses.calculator.impl.Command;
import javaclasses.calculator.impl.EvaluationContext;

public class VariableGetterCommand implements Command{
    private String variableName;

    public VariableGetterCommand(String variableName) {
        this.variableName = variableName;
    }

    @Override
    public void execute(EvaluationContext context) throws CalculationException {
        context.pushVariable(variableName);
    }
}
