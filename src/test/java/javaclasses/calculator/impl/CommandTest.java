package javaclasses.calculator.impl;

import javaclasses.calculator.CalculationException;
import javaclasses.calculator.impl.command.*;
import javaclasses.calculator.impl.function.*;
import javaclasses.calculator.impl.operator.*;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class CommandTest {

    private EvaluationContext context;

    @Before
    public void setContext(){
        context = mock(EvaluationContext.class);
    }

    @Test
    public void testArgumentSeparatorCommand() throws CalculationException {
        new ArgumentSeparatorCommand().execute(context);
        verify(context).pushArgumentSeparator();
    }

    @Test
    public void testClosingBracketCommand() throws CalculationException {
        new ClosingBracketCommand().execute(context);
        verify(context).pushClosingBracket();
    }

    @Test
    public void testDelimiterCommand() throws CalculationException {
        new DelimiterCommand().execute(context);
        verify(context).pushDelimiter();
    }

    @Test
    public void testNumberCommand() throws CalculationException {
        new NumberCommand(12).execute(context);
        verify(context).pushNumber(12);
    }

    @Test
    public void testVariableGetterCommand() throws CalculationException {
        new VariableGetterCommand("var").execute(context);
        verify(context).pushVariable("var");
    }

    @Test
    public void testVariableSetterCommand() throws CalculationException {
        new VariableSetterCommand("var").execute(context);
        verify(context).declareVariable("var");
    }

    @Test
    public void testOpeningBracketCommand() throws CalculationException {
        new OpeningBracketCommand().execute(context);
        verify(context).pushOpeningBracket();
    }

    @Test
    public void testCommandOperatorPlus() throws CalculationException {
        testBinaryOperatorCommand("+", PlusOperator.class);
    }

    @Test
    public void testCommandOperatorMinus() throws CalculationException {
        testBinaryOperatorCommand("-", MinusOperator.class);
    }

    @Test
    public void testCommandOperatorMultiplication() throws CalculationException {
        testBinaryOperatorCommand("*", MultiplicationOperator.class);
    }

    @Test
    public void testCommandOperatorDivision() throws CalculationException {
        testBinaryOperatorCommand("/", DivisionOperator.class);
    }

    @Test
    public void testCommandOperatorExponentiation() throws CalculationException {
        testBinaryOperatorCommand("^", ExponentiationOperator.class);
    }

    @Test
    public void testCommandFunctionAverage() throws CalculationException {
        testFunctionCommand("avg", AverageFunction.class);
    }

    @Test
    public void testCommandFunctionLogarithm10() throws CalculationException {
        testFunctionCommand("log10", Logarithm10Function.class);
    }

    @Test
    public void testCommandFunctionMax() throws CalculationException {
        testFunctionCommand("max", MaxFunction.class);
    }

    @Test
    public void testCommandFunctionMin() throws CalculationException {
        testFunctionCommand("min", MinFunction.class);
    }

    @Test
    public void testCommandFunctionPi() throws CalculationException {
        testFunctionCommand("pi", PiFunction.class);
    }

    @Test
    public void testCommandFunctionPrint() throws CalculationException {
        testFunctionCommand("print", PrintFunction.class);
    }

    @Test
    public void testCommandFunctionSum() throws CalculationException {
        testFunctionCommand("sum", SumFunction.class);
    }


    private void testBinaryOperatorCommand(String operatorSign, Class<? extends BinaryOperator> operatorClass) throws CalculationException {
        new BinaryOperatorCommand(new BinaryOperatorFactory().getBinaryOperator(operatorSign)).execute(context);
        verify(context).pushBinaryOperator(any(operatorClass));
    }

    private void testFunctionCommand(String functionName, Class<? extends Function> functionClass) throws CalculationException {
        new FunctionCommand(new FunctionFactory().getFunction(functionName)).execute(context);
        verify(context).pushFunction(any(functionClass));
    }

}
