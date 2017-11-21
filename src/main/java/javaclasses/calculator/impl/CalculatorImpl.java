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
 * Implementation of calculator.
 */
public class CalculatorImpl extends FiniteStateMachine<EvaluationContext, ExpressionReader, State, CalculationException> implements Calculator {

    private final ParserFactory parserFactory = new ParserFactory();

    private final Map<State, Set<State>> transitions = new HashMap<State, Set<State>>() {{
        put(START, of(NUMBER, OPENING_BRACKET));
        put(NUMBER, of(BINARY_OPERATOR, CLOSING_BRACKET, FINISH));
        put(BINARY_OPERATOR, of(NUMBER, OPENING_BRACKET));
        put(OPENING_BRACKET, of(NUMBER, OPENING_BRACKET));
        put(CLOSING_BRACKET, of(BINARY_OPERATOR, CLOSING_BRACKET, FINISH));
    }};

    /**
     * Calculates mathematical expression from string.
     * Input can contain: numbers, brackets, mathematical operators.
     * Operators can be used:
     * + plus
     * - minus
     * * multiplication
     * / division
     * ^ exponentiation
     * @param expression is a math expression may be solved.
     * @return the result of expression evaluation (double).
     * @throws CalculationException if expression is in incorrect format.
     */
    @Override
    public double calculate(String expression) throws CalculationException {
        final EvaluationContext context = new EvaluationContext();
        start(State.START, new ExpressionReader(expression), context);
        try {
            return context.getResult();
        } catch (CalculationException e){
            if (e.getErrorPosition() == -1){
                throw new CalculationException("Incorrect expression format.", expression.length()-1);
            } else {
                throw e;
            }
        }
    }

    @Override
    protected boolean acceptState(ExpressionReader reader, EvaluationContext context, State nextState) {
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
}
