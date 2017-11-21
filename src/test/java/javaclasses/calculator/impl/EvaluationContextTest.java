package javaclasses.calculator.impl;

import javaclasses.calculator.CalculationException;
import javaclasses.calculator.impl.operator.AbstractBinaryOperator;
import javaclasses.calculator.impl.operator.BinaryOperatorFactory;
import javaclasses.calculator.impl.operator.PlusOperator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class EvaluationContextTest {

    private EvaluationContext context;
    private BinaryOperatorFactory factory = new BinaryOperatorFactory();

    @Before
    public void createContext(){
        context = new EvaluationContext();
    }

    @Test
    public void testPushNumber() throws CalculationException {
        context.pushNumber(10.5);
        Assert.assertEquals(10.5, context.getResult(), 0.00001);
    }

    @Test
    public void testPushClosingBracket() throws CalculationException {
        Assert.assertFalse(context.pushClosingBracket());
    }

    @Test
    public void testPushBinaryOperator() throws CalculationException {
        context.pushNumber(10.5);
        context.pushBinaryOperator(factory.getBinaryOperator("+"));
        context.pushNumber(10.5);
        Assert.assertEquals(21, context.getResult(), 0.00001);
    }

    @Test(expected = CalculationException.class)
    public void testPushTwoNumbers() throws CalculationException {
        context.pushNumber(10.5);
        context.pushNumber(10.5);
        context.getResult();
    }

}