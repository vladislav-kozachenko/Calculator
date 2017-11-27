package javaclasses.calculator.impl;

import javaclasses.calculator.CalculationException;
import javaclasses.calculator.impl.operator.BinaryOperatorFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BinaryOperatorTest {

    private BinaryOperatorFactory factory = new BinaryOperatorFactory();


    @Test
    public void testOperatorPlus(){
        Assert.assertEquals(4.0, factory.getBinaryOperator("+").evaluate(2, 2), 0.00001);
    }

    @Test
    public void testOperatorMinus(){
        Assert.assertEquals(0, factory.getBinaryOperator("-").evaluate(2, 2), 0.00001);
    }

    @Test
    public void testOperatorDivision(){
        Assert.assertEquals(1, factory.getBinaryOperator("/").evaluate(2, 2), 0.00001);
    }

    @Test
    public void testOperatorMultyplication(){
        Assert.assertEquals(6, factory.getBinaryOperator("*").evaluate(2, 3), 0.00001);
    }

    @Test
    public void testOperatorExponentiation(){
        Assert.assertEquals(8, factory.getBinaryOperator("^").evaluate(2, 3), 0.00001);
    }

}
