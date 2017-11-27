package javaclasses.calculator;

import javaclasses.calculator.impl.CalculatorImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;


public class CalculatorTest {

    private final Calculator calculator = new CalculatorImpl();

    @Test
    public void testOneInteger() throws Exception {
        assertEquals(10, calculator.calculate("10").get(), 0.00001);
    }

    @Test
    public void testUnaryMinus() throws Exception {
        assertEquals(-10, calculator.calculate("-10").get(), 0.00001);
    }

    @Test
    public void testOneDouble() throws Exception {
        assertEquals(10.01, calculator.calculate("10.01").get(), 0.00001);
    }

    @Test
    public void testOperatorPlus() throws Exception {
        assertEquals(10, calculator.calculate("2+8").get(), 0.00001);
    }

    @Test
    public void testOperatorMinus() throws Exception {
        assertEquals(-6, calculator.calculate("2-8").get(), 0.00001);
    }

    @Test
    public void testUnaryMinusInExpression() throws Exception {
        assertEquals(6, calculator.calculate("-2+8").get(), 0.00001);
    }

    @Test
    public void testOperatorMultiplication() throws Exception {
        assertEquals(16, calculator.calculate("2*8").get(), 0.00001);
    }

    @Test
    public void testOperatorDivision() throws Exception {
        assertEquals(4, calculator.calculate("8/2").get(), 0.00001);
    }

    @Test
    public void testSeveralMinus() throws Exception {
        assertEquals(6, calculator.calculate("10-2-2").get(), 0.00001);
    }

    @Test
    public void testOperatorsPriority() throws Exception {
        assertEquals(6, calculator.calculate("2+2*2").get(), 0.00001);
    }

    @Test
    public void testOperatorExponentiation() throws Exception {
        assertEquals(16, calculator.calculate("4^2").get(), 0.00001);
    }

    @Test
    public void testOperatorPlusAndExponentiation() throws Exception {
        assertEquals(21, calculator.calculate("5+4^2").get(), 0.00001);
    }

    @Test
    public void testBrackets() throws Exception {
        assertEquals(18, calculator.calculate("(5+4)*2").get(), 0.00001);
    }

    @Test
    public void testUnaryMinusInBrackets() throws Exception {
        assertEquals(-25, calculator.calculate("5*(-5)").get(), 0.00001);
    }

    @Test
    public void testPriorityInBrackets() throws Exception {
        assertEquals(20, calculator.calculate("2*(10+5*2)/2").get(), 0.00001);
    }

    @Test
    public void testInnerBrackets() throws Exception {
        assertEquals(10, calculator.calculate("2*(10+5*(2-2))/2").get(), 0.00001);
    }

    @Test
    public void testEmptyString() throws Exception {
        try {
            calculator.calculate("");
            Assert.fail();
        } catch (CalculationException e) {
            assertEquals(0, e.getErrorPosition());
        }
    }

    @Test
    public void testTwoOperatorsSequence() throws Exception {
        try {
            calculator.calculate("1+*2");
            Assert.fail();
        } catch (CalculationException e) {
            assertEquals(2, e.getErrorPosition());
        }
    }

    @Test
    public void testWrongNumberOfOpeningBrackets() throws Exception {
        try {
            calculator.calculate("5+((1+2)+3");
            Assert.fail();
        } catch (CalculationException e) {
            assertEquals(10, e.getErrorPosition());
        }
    }

    @Test
    public void testWrongNumberOfClosingBrackets() throws Exception {
        try {
            calculator.calculate("5+(1))+1");
            Assert.fail();
        } catch (CalculationException e) {
            assertEquals(5, e.getErrorPosition());
        }
    }

    @Test
    public void testClosingBracketAfterOperator() throws Exception {
        try {
            calculator.calculate("(5+2+)");
            Assert.fail();
        } catch (CalculationException e) {
            assertEquals(5, e.getErrorPosition());
        }
    }

    @Test
    public void testIllegalFunction() throws Exception {
        try {
            calculator.calculate("someFunction(1,2,3)");
            Assert.fail();
        } catch (CalculationException e) {
            assertEquals(0, e.getErrorPosition());
        }
    }

    @Test
    public void testMaxFunction() throws Exception {
        assertEquals(5, calculator.calculate("max(2,5)").get(), 0.00001);
    }

    @Test
    public void testSumFunction() throws Exception {
        assertEquals(7, calculator.calculate("sum(2,5)").get(), 0.00001);
    }

    @Test
    public void testAverageFunction() throws Exception {
        assertEquals(3, calculator.calculate("avg(1,5)").get(), 0.00001);
    }

    @Test
    public void testLog10Function() throws Exception {
        assertEquals(3, calculator.calculate("log10(1000)").get(), 0.00001);
    }

    @Test
    public void testLog10FunctionWithTwoArguments() throws Exception {
        try {
            calculator.calculate("log10(1000,50)");
            Assert.fail();
        } catch (CalculationException e) {
            assertEquals(13, e.getErrorPosition());
        }
    }

    @Test
    public void testCommaInGeneralBrackets() throws Exception {
        try {
            calculator.calculate("(10, 50)");
            Assert.fail();
        } catch (CalculationException e) {
            assertEquals(7, e.getErrorPosition());
        }
    }

    @Test
    public void testInnerMaxFunctionWithExpression() throws Exception {
        assertEquals(11, calculator.calculate("1+max(2,5,max(6+4,5))").get(), 0.00001);
    }

    @Test
    public void testBracketsAfterMaxFunction() throws Exception {
        assertEquals(8, calculator.calculate("max(2,5)+(1+2)").get(), 0.00001);
    }

    @Test
    public void testCaseSensitivity() throws Exception {
        assertEquals(0, calculator.calculate("MAX(2, 5)-SUM(2, 3)").get(), 0.00001);
    }

    @Test
    public void testWhiteSpaces() throws Exception {
        assertEquals(0, calculator.calculate("1 + 2 - 3").get(), 0.00001);
    }

    @Test
    public void testPiFunction() throws Exception {
        assertEquals(Math.PI, calculator.calculate("pi()").get(), 0.00001);
    }

    @Test
    public void testPiFunctionWithArgument() throws Exception {
        try {
            calculator.calculate("pi(10,50)");
            Assert.fail();
        } catch (CalculationException e) {
            assertEquals(8, e.getErrorPosition());
        }
    }

    @Test
    public void testVariable() throws Exception {
        assertEquals(2, calculator.calculate("a=1;b=1+a;b").get(), 0.00001);
    }

    @Test
    public void testVariableWithFunction() throws Exception {
        assertEquals(3, calculator.calculate("a=max(1,2,3);a").get(), 0.00001);
    }

    @Test
    public void testSecondAssignmentIntoVariable() throws Exception {
        assertEquals(5, calculator.calculate("a=max(1,2,3);a=5;a").get(), 0.00001);
    }

    @Test
    public void testDelimiter() throws Exception {
        assertEquals(1, calculator.calculate("a=1;2;a").get(), 0.00001);
    }

    @Test
    public void testPrintFunction() throws Exception {
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        calculator.calculate("a = 1; print(a); 1+2+2;");
        assertEquals("1.0", outContent.toString());
        System.setOut(System.out);
    }

    @Test
    public void testPrintInnerFunction() throws Exception {
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        calculator.calculate("a = 10; b = a * 2; print(min(a, b));");
        assertEquals("10.0", outContent.toString());
        System.setOut(System.out);
    }

    @Test
    public void testReturn() throws Exception {
        assertEquals(7, calculator.calculate("a = 1; print(a+2); 1-1; 5+2;").get(), 0.00001);
    }

    @Test
    public void testPrintWithoutArguments() throws Exception {
        try {
            calculator.calculate("print()");
            Assert.fail();
        } catch (CalculationException e) {
            assertEquals(6, e.getErrorPosition());
        }
    }



}
