package javaclasses.calculator.impl.function;

import javaclasses.calculator.impl.ErrorHandler;
import javaclasses.calculator.impl.Function;

import java.util.List;

public class PiFunction implements Function {

    @Override
    public double execute(List<Double> args, ErrorHandler errorHandler) {
        return Math.PI;
    }
}
