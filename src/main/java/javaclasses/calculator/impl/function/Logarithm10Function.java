package javaclasses.calculator.impl.function;

import javaclasses.calculator.CalculationException;
import javaclasses.calculator.impl.Function;

import java.util.Collections;
import java.util.List;

public class Logarithm10Function implements Function {

    @Override
    public double execute(List<Double> args) throws CalculationException {
        if (args.size() != 1){
            throw new CalculationException("Logarithm10Function error", -1);
        }
        return Math.log10(args.get(0));
    }
}
