package javaclasses.calculator.impl.function;

import javaclasses.calculator.CalculationException;
import javaclasses.calculator.impl.ErrorHandler;
import javaclasses.calculator.impl.Function;

import java.util.List;
import java.util.Optional;

public class AverageFunction extends Function {

    public AverageFunction(int minArguments, int maxArguments) {
        super(minArguments, maxArguments);
    }

    @Override
    public Optional<Double> execute(List<Double> args, ErrorHandler errorHandler) throws CalculationException {
        validateArgumentsNumber(args.size(), errorHandler);
        return Optional.of(new SumFunction(2, Integer.MAX_VALUE).execute(args, errorHandler).get()/args.size());

    }
}
