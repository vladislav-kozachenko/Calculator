package javaclasses.calculator.impl.function;

import javaclasses.calculator.CalculationException;
import javaclasses.calculator.impl.ErrorHandler;
import javaclasses.calculator.impl.Function;

import java.util.Collections;
import java.util.List;

public class MaxFunction extends Function {

    public MaxFunction(int minArguments, int maxArguments) {
        super(minArguments, maxArguments);
    }

    @Override
    public double execute(List<Double> args, ErrorHandler errorHandler) throws CalculationException {
        validateArgumentsNumber(args.size(), errorHandler);
        return Collections.max(args);
    }
}
