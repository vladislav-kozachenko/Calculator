package javaclasses.calculator.impl;

import javaclasses.calculator.CalculationException;
import javaclasses.calculator.impl.function.FunctionFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class FunctionTest {

    private final FunctionFactory factory = new FunctionFactory();
    private List<Double> arguments;
    private final ErrorHandler errorHandler = message -> {
        throw new CalculationException(message, -1);
    };

    @Before
    public void setArguments(){
        arguments = new ArrayList<>();
    }

    @Test
    public void testMaxFunction() throws CalculationException {
        arguments.add(1.0);
        arguments.add(5.0);
        Assert.assertEquals(5, factory.getFunction("max").execute(arguments, errorHandler), 0.0001);
    }

    @Test
    public void testSumFunction() throws CalculationException {
        arguments.add(1.0);
        arguments.add(5.0);
        arguments.add(5.0);
        Assert.assertEquals(11, factory.getFunction("sum").execute(arguments, errorHandler), 0.0001);
    }

    @Test
    public void testAverageFunction() throws CalculationException {
        arguments.add(1.0);
        arguments.add(2.0);
        arguments.add(3.0);
        Assert.assertEquals(2, factory.getFunction("avg").execute(arguments, errorHandler), 0.0001);
    }

    @Test(expected = CalculationException.class)
    public void testAverageFunctionWithOneArgument() throws CalculationException {
        arguments.add(1.0);
        factory.getFunction("avg").execute(arguments, errorHandler);
    }

    @Test
    public void testLog10Function() throws CalculationException {
        arguments.add(100.0);
        Assert.assertEquals(2, factory.getFunction("log10").execute(arguments, errorHandler), 0.0001);
    }

    @Test
    public void testPiFunction() throws CalculationException {
        Assert.assertEquals(Math.PI, factory.getFunction("pi").execute(arguments, errorHandler), 0.0001);
    }

    @Test(expected = CalculationException.class)
    public void testPiFunctionWithArgument() throws CalculationException {
        arguments.add(100.0);
        factory.getFunction("pi").execute(arguments, errorHandler);
    }

    @Test
    public void testLog10FunctionWithSeveralArguments() throws CalculationException {
        arguments.add(100.0);
        arguments.add(100.0);
        try {
            factory.getFunction("log10").execute(arguments, errorHandler);
            Assert.fail();
        } catch (CalculationException e) {
            Assert.assertEquals(-1, e.getErrorPosition());
        }
    }
}
