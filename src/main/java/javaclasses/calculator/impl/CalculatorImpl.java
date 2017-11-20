package javaclasses.calculator.impl;

import javaclasses.calculator.Calculator;
import javaclasses.calculator.fsm.FiniteStateMachine;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CalculatorImpl extends FiniteStateMachine<Double, String, State, RuntimeException> implements Calculator {

    @Override
    public double calculate(String expression) {

        final ExpressionIterator expressionIterator = new ExpressionIterator(expression);

        while (expressionIterator.hasNext()){
            String token = expressionIterator.next();
            System.out.println(token);
        }

        return 0;
    }

    @Override
    protected boolean acceptState(String s, Double aDouble, State nextState) {
        return false;
    }

    @Override
    protected boolean isFinishState(State currentState) {
        return currentState == State.FINISH;
    }

    @Override
    protected Set<State> getPossibleTransitions(State currentState) {
        return new HashSet<State>(Arrays.asList(currentState.getPossibleStates()));
    }

    @Override
    protected void raiseDeadlockError(State state, String s) throws RuntimeException {

    }
}
