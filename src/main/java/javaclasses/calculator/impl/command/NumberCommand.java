package javaclasses.calculator.impl.command;

import javaclasses.calculator.CalculationException;
import javaclasses.calculator.impl.Command;
import javaclasses.calculator.impl.EvaluationContext;

public class NumberCommand implements Command{

    private double number;

    public NumberCommand(double number){
        this.number = number;
    }

    @Override
    public void execute(EvaluationContext context) throws CalculationException {
        context.pushNumber(number);
    }
}
