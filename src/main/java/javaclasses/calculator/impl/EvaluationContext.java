package javaclasses.calculator.impl;

import javaclasses.calculator.CalculationException;

import java.util.*;

/**
 * Context for expression evaluation. Contains temporary storage needed for evaluation.
 */
public class EvaluationContext {

    private Function nextFunction;
    private ErrorHandler errorHandler;

    private final Deque<Double> operandStack = new ArrayDeque<>();
    private final Deque<BinaryOperator> operatorStack = new ArrayDeque<>();
    private final Deque<Integer> bracketStack = new ArrayDeque<>();
    private final Deque<Function> functionStack = new LinkedList<>();
    private final Deque<List<Double>> functionArguments = new ArrayDeque<>();

    public EvaluationContext(ErrorHandler handler){
        this.errorHandler = handler;
    }

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
    double getResult() throws CalculationException {

        while (!operatorStack.isEmpty()) {
            popTopOperator();
        }
        if (!bracketStack.isEmpty() || operandStack.size() > 1){
            errorHandler.raiseError("Missed closing bracket.");
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
     * Adds operator into operator stack.
     */
    public void pushBinaryOperator(BinaryOperator operator) {
        while (!operatorStack.isEmpty()
                && operator.compareTo(operatorStack.peek()) <= 0
                && (bracketStack.isEmpty() || isInBracket())){
            popTopOperator();
        }
        operatorStack.push(operator);
    }

    /**
     * Saves the position of opening bracket.
     */
    public void pushOpeningBracket() {
        bracketStack.push(operatorStack.size());
        functionStack.push(nextFunction);
        nextFunction = null;
    }

    /**
     * Evaluates the inner expression and add result into stack of operands.
     * @return true if evaluation is successful, false if expression doesn't have appropriate opening bracket.
     */
    public void pushClosingBracket() throws CalculationException {
        if (!bracketStack.isEmpty()) {
            while (isInBracket()) {
                popTopOperator();
            }
            bracketStack.pop();
            if (checkCurrentFunction()) {
                executeCurrentFunction();
            }
        } else {
            errorHandler.raiseError("Excess closing bracket.");
        }
    }

    private void executeCurrentFunction() throws CalculationException {
        addCurrentFunctionArgument(operandStack.pop());
        operandStack.push(functionStack.pop().execute(functionArguments.pop(), errorHandler));
    }

    public void pushFunction(Function function) {
        nextFunction = function;
        functionArguments.push(new ArrayList<>());
    }

    public void pushComma() throws CalculationException {
        if (checkCurrentFunction()){
            while (!operatorStack.isEmpty()
                    && isInBracket()){
                popTopOperator();
            }
            addCurrentFunctionArgument(operandStack.pop());
        } else {
            errorHandler.raiseError("It's imposible to use comma without function.");
        }

    }

    private boolean checkCurrentFunction(){
        return !functionStack.isEmpty() && functionStack.peek() != null;
    }

    private void addCurrentFunctionArgument(double argument){
        functionArguments.peek().add(argument);
    }

    private boolean isInBracket(){
        return !bracketStack.isEmpty() && operatorStack.size() > bracketStack.peek();
    }
}
