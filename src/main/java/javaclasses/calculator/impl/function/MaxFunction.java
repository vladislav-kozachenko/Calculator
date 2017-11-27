package javaclasses.calculator.impl.function;

import javaclasses.calculator.CalculationException;
import javaclasses.calculator.impl.ErrorHandler;
import javaclasses.calculator.impl.Function;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class MaxFunction extends Function {

    public MaxFunction(int minArguments, int maxArguments) {
        super(minArguments, maxArguments);
    }

    @Override
    public Optional<Double> execute(List<Double> args, ErrorHandler errorHandler) throws CalculationException {
        validateArgumentsNumber(args.size(), errorHandler);
        return Optional.of(Collections.max(args));
    }
}
