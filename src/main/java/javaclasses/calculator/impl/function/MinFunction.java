package javaclasses.calculator.impl.function;

import javaclasses.calculator.CalculationException;
import javaclasses.calculator.impl.ErrorHandler;
import javaclasses.calculator.impl.Function;

import java.util.Collections;
import java.util.List;

public class MinFunction implements Function {

    @Override
    public double execute(List<Double> args, ErrorHandler errorHandler) throws CalculationException {
        if (args.size() < 2){
            errorHandler.raiseError("Function MIN gets as least 2 arguments.");
        }
        return Collections.min(args);
    }
}
