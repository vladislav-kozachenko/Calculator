package javaclasses.calculator.impl.function;

import javaclasses.calculator.CalculationException;
import javaclasses.calculator.impl.ErrorHandler;
import javaclasses.calculator.impl.Function;

import java.util.List;
import java.util.Optional;

public class SumFunction extends Function {

    public SumFunction(int minArguments, int maxArguments) {
        super(minArguments, maxArguments);
    }

    @Override
    public Optional<Double> execute(List<Double> args, ErrorHandler errorHandler) throws CalculationException {

        validateArgumentsNumber(args.size(), errorHandler);

        double result = 0;
        for (double value: args) {
            result += value;
        }
        return Optional.of(result);
    }
}
