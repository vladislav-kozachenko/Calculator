package javaclasses.calculator;

import javaclasses.calculator.impl.CalculatorImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Locale;

public class CalculatorTest {

    private final Calculator calculator = new CalculatorImpl();

    @Before
    public void setDefaultLocale() {
        Locale.setDefault(new Locale("en", "US"));
    }

    @Test
    public void testOneInteger() throws Exception {
        Assert.assertEquals(10, calculator.calculate("10"), 0.00001);
    }

    @Test
    public void testUnaryMinus() throws Exception {
        Assert.assertEquals(-10, calculator.calculate("-10"), 0.00001);
    }

    @Test
    public void testOneDouble() throws Exception {
        Assert.assertEquals(10.01, calculator.calculate("10.01"), 0.00001);
    }

    @Test
    public void testOperatorPlus() throws Exception {
        Assert.assertEquals(10, calculator.calculate("2+8"), 0.00001);
    }

    @Test
    public void testOperatorMinus() throws Exception {
        Assert.assertEquals(-6, calculator.calculate("2-8"), 0.00001);
    }

    @Test
    public void testUnaryMinusInExpression() throws Exception {
        Assert.assertEquals(6, calculator.calculate("-2+8"), 0.00001);
    }

    @Test
    public void testOperatorMultiplication() throws Exception {
        Assert.assertEquals(16, calculator.calculate("2*8"), 0.00001);
    }

    @Test
    public void testOperatorDivision() throws Exception {
        Assert.assertEquals(4, calculator.calculate("8/2"), 0.00001);
    }

    @Test
    public void testSeveralMinus() throws Exception {
        Assert.assertEquals(6, calculator.calculate("10-2-2"), 0.00001);
    }

    @Test
    public void testOperatorsPriority() throws Exception {
        Assert.assertEquals(6, calculator.calculate("2+2*2"), 0.00001);
    }

    @Test
    public void testOperatorExponentiation() throws Exception {
        Assert.assertEquals(16, calculator.calculate("4^2"), 0.00001);
    }

    @Test
    public void testOperatorPlusAndExponentiation() throws Exception {
        Assert.assertEquals(21, calculator.calculate("5+4^2"), 0.00001);
    }

    @Test
    public void testBrackets() throws Exception {
        Assert.assertEquals(18, calculator.calculate("(5+4)*2"), 0.00001);
    }

    @Test
    public void testUnaryMinusInBrackets() throws Exception {
        Assert.assertEquals(-25, calculator.calculate("5*(-5)"), 0.00001);
    }

    @Test
    public void testPriorityInBrackets() throws Exception {
        Assert.assertEquals(20, calculator.calculate("2*(10+5*2)/2"), 0.00001);
    }

    @Test
    public void testInnerBrackets() throws Exception {
        Assert.assertEquals(10, calculator.calculate("2*(10+5*(2-2))/2"), 0.00001);
    }

    @Test
    public void testEmptyString() throws Exception {
        try {
            calculator.calculate("");
            Assert.fail();
        } catch (CalculationException e) {
            Assert.assertEquals(0, e.getErrorPosition());
        }
    }

    @Test
    public void testTwoOperatorsSequence() throws Exception {
        try {
            calculator.calculate("1+*2");
            Assert.fail();
        } catch (CalculationException e) {
            Assert.assertEquals(2, e.getErrorPosition());
        }
    }

    @Test
    public void testWrongNumberOfOpeningBrackets() throws Exception {
        try {
            calculator.calculate("5+((1+2)+3");
            Assert.fail();
        } catch (CalculationException e) {
            Assert.assertEquals(10, e.getErrorPosition());
        }
    }

    @Test
    public void testWrongNumberOfClosingBrackets() throws Exception {
        try {
            calculator.calculate("5+(1))+1");
            Assert.fail();
        } catch (CalculationException e) {
            Assert.assertEquals(5, e.getErrorPosition());
        }
    }

    @Test
    public void testClosingBracketAfterOperator() throws Exception {
        try {
            calculator.calculate("(5+2+)");
            Assert.fail();
        } catch (CalculationException e) {
            Assert.assertEquals(5, e.getErrorPosition());
        }
    }

    @Test
    public void testIllegalFunction() throws Exception {
        try {
            calculator.calculate("someFunction(1,2,3)");
            Assert.fail();
        } catch (CalculationException e) {
            Assert.assertEquals(0, e.getErrorPosition());
        }
    }

    @Test
    public void testMaxFunction() throws Exception {
        Assert.assertEquals(5, calculator.calculate("max(2,5)"), 0.00001);
    }

    @Test
    public void testSumFunction() throws Exception {
        Assert.assertEquals(7, calculator.calculate("sum(2,5)"), 0.00001);
    }

    @Test
    public void testAverageFunction() throws Exception {
        Assert.assertEquals(3, calculator.calculate("avg(1,5)"), 0.00001);
    }

    @Test
    public void testLog10Function() throws Exception {
        Assert.assertEquals(3, calculator.calculate("log10(1000)"), 0.00001);
    }

    @Test
    public void testLog10FunctionWithTwoArguments() throws Exception {
        try {
            calculator.calculate("log10(1000,50)");
            Assert.fail();
        } catch (CalculationException e) {
            Assert.assertEquals(13, e.getErrorPosition());
        }
    }

    @Test
    public void testCommaInGeneralBrackets() throws Exception {
        try {
            calculator.calculate("(10, 50)");
            Assert.fail();
        } catch (CalculationException e) {
            Assert.assertEquals(7, e.getErrorPosition());
        }
    }

    @Test
    public void testInnerMaxFunctionWithExpression() throws Exception {
        Assert.assertEquals(11, calculator.calculate("1+max(2,5,max(6+4,5))"), 0.00001);
    }

    @Test
    public void testBracketsAfterMaxFunction() throws Exception {
        Assert.assertEquals(8, calculator.calculate("max(2,5)+(1+2)"), 0.00001);
    }

    @Test
    public void testCaseSensitivity() throws Exception {
        Assert.assertEquals(0, calculator.calculate("MAX(2,5)-SUM(2,3)"), 0.00001);
    }

    @Test
    public void testWhiteSpaces() throws Exception {
        Assert.assertEquals(0, calculator.calculate("1 + 2 - 3"), 0.00001);
    }

    @Test
    public void testPiFunction() throws Exception {
        Assert.assertEquals(Math.PI, calculator.calculate("pi()"), 0.00001);
    }

    @Test
    public void testPiFunctionWithArgument() throws Exception {
        try {
            calculator.calculate("pi(10,50)");
            Assert.fail();
        } catch (CalculationException e) {
            Assert.assertEquals(8, e.getErrorPosition());
        }
    }

}
