package javaclasses.calculator.impl;

import javaclasses.calculator.CalculationException;
import javaclasses.calculator.impl.parser.ParserFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
        Assert.assertTrue(parser.parse(reader, context));
    }

    @Test
    public void testOperatorMinusParsing() throws CalculationException {
        ExpressionParser parser = factory.getParser(State.BINARY_OPERATOR);
        ExpressionReader reader = new ExpressionReader("-");
        Assert.assertTrue(parser.parse(reader, context));
    }

    @Test
    public void testOperatorMultiplicationParsing() throws CalculationException {
        ExpressionParser parser = factory.getParser(State.BINARY_OPERATOR);
        ExpressionReader reader = new ExpressionReader("*");
        Assert.assertTrue(parser.parse(reader, context));
    }

    @Test
    public void testOperatorDivisionParsing() throws CalculationException {
        ExpressionParser parser = factory.getParser(State.BINARY_OPERATOR);
        ExpressionReader reader = new ExpressionReader("/");
        Assert.assertTrue(parser.parse(reader, context));
    }

    @Test
    public void testOperatorExponentiationParsing() throws CalculationException {
        ExpressionParser parser = factory.getParser(State.BINARY_OPERATOR);
        ExpressionReader reader = new ExpressionReader("^");
        Assert.assertTrue(parser.parse(reader, context));
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
        Assert.assertTrue(parser.parse(reader, context));
    }

    @Test
    public void testClosingBracketParsing() throws CalculationException {
        ExpressionParser parser = factory.getParser(State.CLOSING_BRACKET);
        context.pushOpeningBracket();
        context.pushNumber(1);
        ExpressionReader reader = new ExpressionReader(")");
        Assert.assertTrue(parser.parse(reader, context));
    }

    @Test(expected = IllegalStateException.class)
    public void testStart() throws CalculationException {
        ExpressionParser parser = factory.getParser(State.START);
    }

}
