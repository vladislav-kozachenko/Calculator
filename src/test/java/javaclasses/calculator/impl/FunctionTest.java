package javaclasses.calculator.impl;

import javaclasses.calculator.impl.function.FunctionFactory;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FunctionTest {
    final FunctionFactory factory = new FunctionFactory();

    @Test
    public void testMaxFunction(){
        List<Double> argument = new ArrayList<>();
        argument.add(1.0);
        argument.add(5.0);
        Assert.assertEquals(5, factory.getFunction("max").execute(argument), 0.0001);
    }
}
