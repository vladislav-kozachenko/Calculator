package javaclasses.calculator.impl.function;

import javaclasses.calculator.CalculationException;
import javaclasses.calculator.impl.ErrorHandler;
import javaclasses.calculator.impl.Function;

import java.util.List;

public class PiFunction implements Function {

    @Override
    public double execute(List<Double> args, ErrorHandler errorHandler) throws CalculationException {
        if (args.size() != 0){
            errorHandler.raiseError("Function PI gets no arguments.");
        }
        return Math.PI;
    }
}
