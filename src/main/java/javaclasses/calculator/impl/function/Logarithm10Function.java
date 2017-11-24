package javaclasses.calculator.impl.function;

import javaclasses.calculator.CalculationException;
import javaclasses.calculator.impl.ErrorHandler;
import javaclasses.calculator.impl.Function;

import java.util.List;

public class Logarithm10Function implements Function {

    @Override
    public double execute(List<Double> args, ErrorHandler errorHandler) throws CalculationException {
        if (args.size() != 1){
            errorHandler.raiseError("Logarithm function needs 1 argument. Used " + args.size() + " arguments.");
        }
        return Math.log10(args.get(0));
    }
}
