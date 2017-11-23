package javaclasses.calculator.impl;

import javaclasses.calculator.impl.function.FunctionFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class FunctionTest {

    final FunctionFactory factory = new FunctionFactory();
    private List<Double> arguments;

    @Before
    public void setArguments(){
        arguments = new ArrayList<>();
    }

    @Test
    public void testMaxFunction(){
        arguments.add(1.0);
        arguments.add(5.0);
        Assert.assertEquals(5, factory.getFunction("max").execute(arguments), 0.0001);
    }

    @Test
    public void testSumFunction(){
        arguments.add(1.0);
        arguments.add(5.0);
        arguments.add(5.0);
        Assert.assertEquals(11, factory.getFunction("sum").execute(arguments), 0.0001);
    }
}
