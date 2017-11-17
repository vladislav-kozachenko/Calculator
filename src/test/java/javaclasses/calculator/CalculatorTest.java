package javaclasses.calculator;

import javaclasses.calculator.impl.CalculatorImpl;
import org.junit.Assert;
import org.junit.Test;

public class CalculatorTest {
    final Calculator calc = new CalculatorImpl();

    @Test
    public void testCalculate() {
        Assert.assertEquals(new Double(7), new Double(calc.calculate("5.0+5.0*2/(max(0,1)+2^2)+1")));
    }
}
