package javaclasses.calculator.impl;

import javaclasses.calculator.CalculationException;
import javaclasses.calculator.impl.function.AverageFunction;
import javaclasses.calculator.impl.function.MaxFunction;
import javaclasses.calculator.impl.function.SumFunction;
import javaclasses.calculator.impl.operator.*;
import javaclasses.calculator.impl.parser.ParserFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ParserTest {

    private ParserFactory factory = new ParserFactory();
    private EvaluationContext context;

    @Before
    public void setContext(){
        context = new EvaluationContext();
    }

    @Test
    public void testNumberParsing() throws CalculationException {
        ExpressionParser parser = factory.getParser(State.NUMBER);
        ExpressionReader reader = new ExpressionReader("123.456");
        Assert.assertTrue(parser.parse(reader, context));
        Assert.assertEquals(123.456, context.getResult(), 0.0001);
    }

    @Test
    public void testIllegalNumberParsing() throws CalculationException {
        ExpressionParser parser = factory.getParser(State.NUMBER);
        ExpressionReader reader = new ExpressionReader("aaa");
        Assert.assertFalse(parser.parse(reader, context));
    }

    @Test
    public void testOperatorPlusParsing() throws CalculationException {
        ExpressionParser parser = factory.getParser(State.BINARY_OPERATOR);
        ExpressionReader reader = new ExpressionReader("+");
        EvaluationContext evaluationContext = mock(EvaluationContext.class);
        parser.parse(reader, evaluationContext);
        verify(evaluationContext).pushBinaryOperator(any(PlusOperator.class));
    }

    @Test
    public void testOperatorMinusParsing() throws CalculationException {
        ExpressionParser parser = factory.getParser(State.BINARY_OPERATOR);
        ExpressionReader reader = new ExpressionReader("-");
        EvaluationContext evaluationContext = mock(EvaluationContext.class);
        parser.parse(reader, evaluationContext);
        verify(evaluationContext).pushBinaryOperator(any(MinusOperator.class));
    }

    @Test
    public void testOperatorMultiplicationParsing() throws CalculationException {
        ExpressionParser parser = factory.getParser(State.BINARY_OPERATOR);
        ExpressionReader reader = new ExpressionReader("*");
        EvaluationContext evaluationContext = mock(EvaluationContext.class);
        parser.parse(reader, evaluationContext);
        verify(evaluationContext).pushBinaryOperator(any(MultiplicationOperator.class));
    }

    @Test
    public void testOperatorDivisionParsing() throws CalculationException {
        ExpressionParser parser = factory.getParser(State.BINARY_OPERATOR);
        ExpressionReader reader = new ExpressionReader("/");
        EvaluationContext evaluationContext = mock(EvaluationContext.class);
        parser.parse(reader, evaluationContext);
        verify(evaluationContext).pushBinaryOperator(any(DivisionOperator.class));
    }

    @Test
    public void testOperatorExponentiationParsing() throws CalculationException {
        ExpressionParser parser = factory.getParser(State.BINARY_OPERATOR);
        ExpressionReader reader = new ExpressionReader("^");
        EvaluationContext evaluationContext = mock(EvaluationContext.class);
        parser.parse(reader, evaluationContext);
        verify(evaluationContext).pushBinaryOperator(any(ExponentiationOperator.class));
    }

    @Test
    public void testIllegalOperatorParsing() throws CalculationException {
        ExpressionParser parser = factory.getParser(State.BINARY_OPERATOR);
        ExpressionReader reader = new ExpressionReader("123");
        Assert.assertFalse(parser.parse(reader, context));
    }

    @Test
    public void testOpeningBracketParsing() throws CalculationException {
        ExpressionParser parser = factory.getParser(State.OPENING_BRACKET);
        ExpressionReader reader = new ExpressionReader("(");
        EvaluationContext evaluationContext = mock(EvaluationContext.class);
        parser.parse(reader, evaluationContext);
        verify(evaluationContext).pushOpeningBracket();
    }

    @Test
    public void testClosingBracketParsing() throws CalculationException {
        ExpressionParser parser = factory.getParser(State.CLOSING_BRACKET);
        ExpressionReader reader = new ExpressionReader(")");
        EvaluationContext evaluationContext = mock(EvaluationContext.class);
        parser.parse(reader, evaluationContext);
        verify(evaluationContext).pushClosingBracket();
    }

    @Test(expected = IllegalStateException.class)
    public void testStart() throws CalculationException {
        factory.getParser(State.START);
    }

    @Test
    public void testSumFunctionParsing() throws CalculationException {
        ExpressionParser parser = factory.getParser(State.FUNCTION_NAME);
        ExpressionReader reader = new ExpressionReader("sum");
        EvaluationContext evaluationContext = mock(EvaluationContext.class);
        parser.parse(reader, evaluationContext);
        verify(evaluationContext).pushFunction(any(SumFunction.class));
    }

    @Test
    public void testMaxFunctionParsing() throws CalculationException {
        ExpressionParser parser = factory.getParser(State.FUNCTION_NAME);
        ExpressionReader reader = new ExpressionReader("max");
        EvaluationContext evaluationContext = mock(EvaluationContext.class);
        parser.parse(reader, evaluationContext);
        verify(evaluationContext).pushFunction(any(MaxFunction.class));
    }

    @Test
    public void testAverageFunctionParsing() throws CalculationException {
        ExpressionParser parser = factory.getParser(State.FUNCTION_NAME);
        ExpressionReader reader = new ExpressionReader("avg");
        EvaluationContext evaluationContext = mock(EvaluationContext.class);
        parser.parse(reader, evaluationContext);
        verify(evaluationContext).pushFunction(any(AverageFunction.class));
    }


}
