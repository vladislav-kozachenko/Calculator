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
    public void testOperatorMinus() throws CalculationException {
        Assert.assertEquals(-6, calculator.calculate("2-8"), 0.00001);
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

    @Test
    public void testEmptyString() throws CalculationException {
        try {
            calculator.calculate("");
            Assert.fail();
        } catch (CalculationException e) {
            Assert.assertEquals(0, e.getErrorPosition());
        }
    }

    @Test
    public void testTwoOperatorsSequence() throws CalculationException {
        try {
            calculator.calculate("1+*2");
            Assert.fail();
        } catch (CalculationException e) {
            Assert.assertEquals(2, e.getErrorPosition());
        }
    }

    @Test
    public void testWrongNumberOfOpeningBrackets() throws CalculationException {
        try {
            calculator.calculate("5+((1+2)+3");
            Assert.fail();
        } catch (CalculationException e) {
            Assert.assertEquals(9, e.getErrorPosition());
        }
    }

    @Test
    public void testWrongNumberOfClosingBrackets() throws CalculationException {
        try {
            calculator.calculate("5+(1))+1");
            Assert.fail();
        } catch (CalculationException e) {
            Assert.assertEquals(5, e.getErrorPosition());
        }
    }

    @Test
    public void testClosingBracketAfterOperator() throws CalculationException {
        try {
            calculator.calculate("(5+2+)");
            Assert.fail();
        } catch (CalculationException e) {
            Assert.assertEquals(5, e.getErrorPosition());
        }
    }

    @Test
    public void testIllegalFunction() throws CalculationException {
        try {
            calculator.calculate("someFunction(1,2,3)");
            Assert.fail();
        } catch (CalculationException e) {
            Assert.assertEquals(0, e.getErrorPosition());
        }
    }

    @Test
    public void testMaxFunction() throws CalculationException {
        Assert.assertEquals(5, calculator.calculate("max(2,5)"), 0.00001);
    }

    @Test
    public void testSumFunction() throws CalculationException {
        Assert.assertEquals(7, calculator.calculate("sum(2,5)"), 0.00001);
    }

    @Test
    public void testAverageFunction() throws CalculationException {
        Assert.assertEquals(3, calculator.calculate("avg(1,5)"), 0.00001);
    }

    @Test
    public void testLog10Function() throws CalculationException {
        Assert.assertEquals(3, calculator.calculate("log10(1000)"), 0.00001);
    }

    @Test
    public void testLog10FunctionWithTwoArguments() throws CalculationException {
        try {
            calculator.calculate("log10(1000,50)");
            Assert.fail();
        } catch (CalculationException e) {
            Assert.assertEquals(13, e.getErrorPosition());
        }
    }

    @Test
    public void testInnerMaxFunctionWithExpression() throws CalculationException {
        Assert.assertEquals(11, calculator.calculate("1+max(2,5,max(6+4,5))"), 0.00001);
    }

    @Test
    public void testBracketsAfterMaxFunction() throws CalculationException {
        Assert.assertEquals(8, calculator.calculate("max(2,5)+(1+2)"), 0.00001);
    }

    @Test
    public void testCaseSensitivity() throws CalculationException {
        Assert.assertEquals(0, calculator.calculate("MAX(2,5)-SUM(2+3)"), 0.00001);
    }

    @Test
    public void testWhiteSpaces() throws CalculationException {
        Assert.assertEquals(0, calculator.calculate("1 + 2 - 3"), 0.00001);
    }

}
