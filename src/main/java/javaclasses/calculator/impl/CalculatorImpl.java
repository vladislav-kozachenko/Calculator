package javaclasses.calculator.impl;

import javaclasses.calculator.Calculator;
import javaclasses.finiteStateMachine.AbstractStateMachine;

public class CalculatorImpl extends AbstractStateMachine implements Calculator {
    @Override
    public double calculate(String expression) {

        final ExpressionIterator expressionIterator = new ExpressionIterator(expression);

        while (expressionIterator.hasNext()){
            String token = expressionIterator.next();
            System.out.println(token);
        }

        return 0;
    }
}
