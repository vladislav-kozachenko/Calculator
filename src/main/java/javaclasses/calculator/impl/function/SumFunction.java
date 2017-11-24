package javaclasses.calculator.impl.function;

import javaclasses.calculator.CalculationException;
import javaclasses.calculator.impl.ErrorHandler;
import javaclasses.calculator.impl.Function;

import java.util.Collections;
import java.util.List;

public class SumFunction implements Function {

    @Override
    public double execute(List<Double> args, ErrorHandler errorHandler) throws CalculationException {

        if (args.size() < 2){
            errorHandler.raiseError("Function SUM requires at least 2 arguments.");
        }


        double result = 0;
        for (double value: args) {
            result += value;
        }
        return result;
    }
}
