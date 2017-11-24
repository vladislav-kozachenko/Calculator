package javaclasses.calculator.impl.function;

import javaclasses.calculator.impl.ErrorHandler;
import javaclasses.calculator.impl.Function;

import java.util.List;

public class AverageFunction implements Function {

    @Override
    public double execute(List<Double> args, ErrorHandler errorHandler) {

        return new SumFunction().execute(args, errorHandler)/args.size();

    }
}
