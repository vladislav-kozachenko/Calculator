package javaclasses.calculator.impl.function;

import javaclasses.calculator.CalculationException;
import javaclasses.calculator.impl.ErrorHandler;
import javaclasses.calculator.impl.Function;

import java.util.List;

public class BracketsFunction implements Function {

    @Override
    public double execute(List<Double> args, ErrorHandler errorHandler) throws CalculationException {
        if (args.size() != 1){
            errorHandler.raiseError("Argument separators are forbidden in a common brackets.");
        }
        return args.get(0);
    }
}
