package javaclasses.calculator.impl.function;

import javaclasses.calculator.CalculationException;
import javaclasses.calculator.impl.ErrorHandler;
import javaclasses.calculator.impl.Function;

import java.util.List;
import java.util.Optional;

public class BracketsFunction extends Function {

    public BracketsFunction() {
        super(1, 1);
    }

    @Override
    public Optional<Double> execute(List<Double> args, ErrorHandler errorHandler) throws CalculationException {
        if (args.size() != 1){
            errorHandler.raiseError("Argument separators are forbidden in a common brackets.");
        }
        return Optional.of(args.get(0));
    }
}
