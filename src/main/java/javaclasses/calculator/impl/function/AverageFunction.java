package javaclasses.calculator.impl.function;

import javaclasses.calculator.CalculationException;
import javaclasses.calculator.impl.ErrorHandler;
import javaclasses.calculator.impl.Function;

import java.util.List;

public class AverageFunction implements Function {

    @Override
    public double execute(List<Double> args, ErrorHandler errorHandler) throws CalculationException {
        if (args.size() < 2){
            errorHandler.raiseError("Function average gets as least 2 arguments.");
        }

        return new SumFunction().execute(args, errorHandler)/args.size();

    }
}
