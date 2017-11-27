package javaclasses.calculator.impl.function;

import javaclasses.calculator.CalculationException;
import javaclasses.calculator.impl.ErrorHandler;
import javaclasses.calculator.impl.Function;

import java.util.List;

public class Logarithm10Function extends Function {

    public Logarithm10Function(int minArguments, int maxArguments) {
        super(minArguments, maxArguments);
    }

    @Override
    public double execute(List<Double> args, ErrorHandler errorHandler) throws CalculationException {
        validateArgumentsNumber(args.size(), errorHandler);
        return Math.log10(args.get(0));
    }
}
