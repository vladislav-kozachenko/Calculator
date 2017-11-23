package javaclasses.calculator;

import javaclasses.calculator.impl.CalculatorImpl;
import org.junit.Assert;
import org.junit.Test;

public class CalculatorTest {

    final Calculator calculator = new CalculatorImpl();

    @Test
    public void testOneInteger() throws CalculationException {
        Assert.assertEquals(10, calculator.calculate("10"), 0.00001);
    }

    @Test
    public void testUnaryMinus() throws CalculationException {
        Assert.assertEquals(-10, calculator.calculate("-10"), 0.00001);
    }

    @Test
    public void testOneDouble() throws CalculationException {
        Assert.assertEquals(10.01, calculator.calculate("10.01"), 0.00001);
    }

    @Test
    public void testOperatorPlus() throws CalculationException {
        Assert.assertEquals(10, calculator.calculate("2+8"), 0.00001);
    }

    @Test
    public void testUnaryMinusInExpression() throws CalculationException {
        Assert.assertEquals(6, calculator.calculate("-2+8"), 0.00001);
    }

    @Test
    public void testOperatorMultiplication() throws CalculationException {
        Assert.assertEquals(16, calculator.calculate("2*8"), 0.00001);
    }

    @Test
    public void testOperatorDivision() throws CalculationException {
        Assert.assertEquals(4, calculator.calculate("8/2"), 0.00001);
    }

    @Test
    public void testSeveralMinus() throws CalculationException {
        Assert.assertEquals(6, calculator.calculate("10-2-2"), 0.00001);
    }

    @Test
    public void testOperatorsPriority() throws CalculationException {
        Assert.assertEquals(6, calculator.calculate("2+2*2"), 0.00001);
    }

    @Test
    public void testOperatorExponentiation() throws CalculationException {
        Assert.assertEquals(16, calculator.calculate("4^2"), 0.00001);
    }

    @Test
    public void testOperatorPlusAndExponentiation() throws CalculationException {
        Assert.assertEquals(21, calculator.calculate("5+4^2"), 0.00001);
    }

    @Test
    public void testBrackets() throws CalculationException {
        Assert.assertEquals(18, calculator.calculate("(5+4)*2"), 0.00001);
    }

    @Test
    public void testUnaryMinusInBrackets() throws CalculationException {
        Assert.assertEquals(-25, calculator.calculate("5*(-5)"), 0.00001);
    }

    @Test
    public void testPriorityInBrackets() throws CalculationException {
        Assert.assertEquals(20, calculator.calculate("2*(10+5*2)/2"), 0.00001);
    }

    @Test
    public void testInnerBrackets() throws CalculationException {
        Assert.assertEquals(10, calculator.calculate("2*(10+5*(2-2))/2"), 0.00001);
    }

    @Test(expected = CalculationException.class)
    public void testEmptyString() throws CalculationException {
        calculator.calculate("");
    }

    @Test(expected = CalculationException.class)
    public void testIllegalString() throws CalculationException {
        calculator.calculate("budsfgjsgd12");
    }

    @Test(expected = CalculationException.class)
    public void testIllegalExpression() throws CalculationException {
        calculator.calculate("5**5");
    }

    @Test(expected = CalculationException.class)
    public void testWrongNumberOfOpeningBrackets() throws CalculationException {
        System.out.println(calculator.calculate("5+((1+2)+3"));
    }

    @Test(expected = CalculationException.class)
    public void testWrongNumberOfClosingBrackets() throws CalculationException {
        System.out.println(calculator.calculate("5+(1))+1"));
    }

    @Test
    public void testMaxFunction() throws CalculationException {
        Assert.assertEquals(5, calculator.calculate("max(2,5)"), 0.00001);
    }

    @Test
    public void testInnerMaxFunctionWithExpression() throws CalculationException {
        Assert.assertEquals(11, calculator.calculate("1+max(2,5,max(6+4,5))"), 0.00001);
    }

    @Test
    public void testBracketsAfterMaxFunction() throws CalculationException {
        Assert.assertEquals(8, calculator.calculate("max(2,5)+(1+2)"), 0.00001);
    }

}
