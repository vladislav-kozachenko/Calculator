package javaclasses.calculator.impl;

import javaclasses.calculator.CalculationException;
import javaclasses.calculator.Calculator;
import javaclasses.calculator.fsm.FiniteStateMachine;
import javaclasses.calculator.impl.parser.ParserFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static java.util.EnumSet.of;
import static javaclasses.calculator.impl.State.*;
import static javaclasses.calculator.impl.State.BINARY_OPERATOR;
import static javaclasses.calculator.impl.State.NUMBER;

/**
 * Implements a calculator that accepts String argument and produces a double result.
 */
public class CalculatorImpl extends FiniteStateMachine<EvaluationContext, ExpressionReader, State, CalculationException> implements Calculator {

    private final ParserFactory parserFactory = new ParserFactory();

    private final Map<State, Set<State>> transitions = new HashMap<State, Set<State>>() {{
        put(START, of(NUMBER, OPENING_BRACKET, FUNCTION_NAME));
        put(NUMBER, of(BINARY_OPERATOR, CLOSING_BRACKET, ARGUMENT_SEPARATOR, FINISH));
        put(BINARY_OPERATOR, of(NUMBER, OPENING_BRACKET, FUNCTION_NAME));
        put(FUNCTION_NAME, of(OPENING_BRACKET));
        put(ARGUMENT_SEPARATOR, of(NUMBER, OPENING_BRACKET, FUNCTION_NAME));
        put(OPENING_BRACKET, of(NUMBER, OPENING_BRACKET, FUNCTION_NAME));
        put(CLOSING_BRACKET, of(BINARY_OPERATOR, CLOSING_BRACKET, ARGUMENT_SEPARATOR, FINISH));
    }};

    /**
     * Calculates mathematical expression from string.
     * Input can contain: numbers, brackets, mathematical operators.
     * Whitespaces are allowed. Calculator is not case sensitive.
     * Operators can be used:
     * <li>
     * <ul>+ plus</ul>
     * <ul>- minus</ul>
     * <ul>* multiplication</ul>
     * <ul>/ division</ul>
     * <ul>^ exponentiation</ul>
     * </li>
     * Functions are allowed:
     * <li>
     * <ul>max();</ul>
     * <ul>sum();</ul>
     * <ul>avg();</ul>
     * </li>
     * @param expression is a math expression may be solved.
     * @return the result of expression evaluation (double).
     * @throws CalculationException if expression is in incorrect format.
     */
    @Override
    public double calculate(String expression) throws Exception {

        ExpressionReader reader = new ExpressionReader(prepareString(expression));

        final EvaluationContext context = new EvaluationContext(new ErrorHandler() {
            @Override
            public void raiseError(String message) throws CalculationException {
                throw new CalculationException(message, reader.getParsePosition());
            }
        });
        start(State.START, reader, context);

        return context.getResult();
    }

    @Override
    protected boolean acceptState(ExpressionReader reader, EvaluationContext context, State nextState) throws CalculationException {
        final ExpressionParser parser = parserFactory.getParser(nextState);
        return parser.parse(reader, context);
    }

    @Override
    protected boolean isFinishState(State currentState) {
        return currentState == State.FINISH;
    }

    @Override
    protected Set<State> getPossibleTransitions(State currentState) {
        return transitions.get(currentState);
    }

    @Override
    protected void raiseDeadlockError(State state, ExpressionReader reader) throws CalculationException {
        throw new CalculationException("Incorrect expression format.", reader.getParsePosition());
    }

    private String prepareString(String source){
        return source.replaceAll("\\s+","").toLowerCase();
    }
}
