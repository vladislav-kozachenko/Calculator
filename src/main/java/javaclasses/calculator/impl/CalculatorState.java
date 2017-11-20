package javaclasses.calculator.impl;

import javaclasses.finiteStateMachine.State;

public interface CalculatorState extends State {

    CalculatorState onNumber();

    CalculatorState onBinaryOperator();

    CalculatorState onOpenBrace();

    CalculatorState onCloseBrace();

    CalculatorState onFunction();

    CalculatorState onFinish();

}
