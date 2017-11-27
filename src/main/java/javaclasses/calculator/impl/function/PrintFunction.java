package javaclasses.calculator.impl.function;

import javaclasses.calculator.CalculationException;
import javaclasses.calculator.impl.ErrorHandler;
import javaclasses.calculator.impl.Function;

import java.util.List;
import java.util.Optional;

public class PrintFunction extends Function {

    public PrintFunction(int minArguments, int maxArguments) {
        super(minArguments, maxArguments);
    }

    @Override
    public Optional<Double> execute(List<Double> args, ErrorHandler errorHandler) throws CalculationException {
        validateArgumentsNumber(args.size(), errorHandler);
        System.out.print(args.get(0));

        return Optional.empty();
    }
}
