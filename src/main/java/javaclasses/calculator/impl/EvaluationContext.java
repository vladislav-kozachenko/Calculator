package javaclasses.calculator.impl;

import javaclasses.calculator.CalculationException;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Context for expression evaluation. Contains temporary storage needed for evaluation.
 */
public class EvaluationContext {

    private final Deque<Double> operandStack = new ArrayDeque<>();
    private final Deque<BinaryOperator> operatorStack = new ArrayDeque<>();
    private final Deque<Integer> bracketStack = new ArrayDeque<>();


    /**
     * Added digit to operands stack.
     * @param value operand.
     */
    public void pushNumber(double value) {
        operandStack.push(value);
    }

    /**
     * Evaluates stored expression and returns the result.
     * @return result of expression evaluation (double).
     * @throws CalculationException if expression has wrong number of brackets or if it is internal error.
     */
    public double getResult() throws CalculationException {

        while (!operatorStack.isEmpty()) {
            popTopOperator();
        }
        if (!bracketStack.isEmpty() || operandStack.size() > 1){
            throw new CalculationException("Incorrect expression format.", -1);
        }
        return operandStack.pop();
    }

    /**
     * Evaluates last atomic operation. Adds result into stack of operands.
     */
    private void popTopOperator() {
        final BinaryOperator operator = operatorStack.pop();
        final Double rightOperand = operandStack.pop();
        final Double leftOperand = operandStack.pop();
        final double result = operator.evaluate(leftOperand, rightOperand);
        operandStack.push(result);
    }

    /**
     * Add
     * @param operator
     */
    public void pushBinaryOperator(BinaryOperator operator) {
        while (!operatorStack.isEmpty()
                && operator.compareTo(operatorStack.peek()) <= 0
                && (bracketStack.isEmpty() || operatorStack.size() > bracketStack.peek())){
            popTopOperator();
        }
        operatorStack.push(operator);
    }

    /**
     * Saves the position of opening bracket.
     */
    public void pushOpeningBracket() {
        bracketStack.push(operatorStack.size());
    }

    /**
     * Evaluates the inner expression and add result into stack of operands.
     * @return true if evaluation is successful, false if expression doesn't have appropriate opening bracket.
     */
    public boolean pushClosingBracket() {
        if (!bracketStack.isEmpty()) {
            while (operatorStack.size() > bracketStack.peek()) {
                popTopOperator();
            }
            bracketStack.pop();
            return true;
        } else {
            return false;
        }
    }
}
