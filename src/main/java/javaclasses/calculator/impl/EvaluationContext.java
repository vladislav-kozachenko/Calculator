package javaclasses.calculator.impl;

import javaclasses.calculator.CalculationException;
import javaclasses.calculator.fsm.FiniteStateMachine;
import javaclasses.calculator.impl.function.BracketsFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Context for expression evaluation. Contains temporary storage needed for evaluation.
 */
public class EvaluationContext {

    private final static Logger LOG = LoggerFactory.getLogger(FiniteStateMachine.class.getName());

    private Function nextFunction;
    private String nextVariable;
    private ErrorHandler errorHandler;
    private Optional<Double> result;

    private final Deque<Double> operandStack = new ArrayDeque<>();
    private final Deque<BinaryOperator> operatorStack = new ArrayDeque<>();

    private final Deque<FunctionContext> functionStack = new ArrayDeque<>();
    private final Map<String, Double> variables = new HashMap<>();

    public EvaluationContext(ErrorHandler handler){
        this.errorHandler = handler;
        nextFunction = new BracketsFunction();
        result = Optional.empty();
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
    Optional<Double> getResult() throws CalculationException {

        while (!operatorStack.isEmpty()) {
            popTopOperator();
        }
        if (!functionStack.isEmpty()){
            errorHandler.raiseError("Missed closing bracket.");
        } else if (operandStack.size() > 1) {
            if (LOG.isErrorEnabled()){
                LOG.error("Operands remained on the stack.");
            }
            errorHandler.raiseError("Internal error.");
        }
        if (operandStack.isEmpty()){
            return result;
        } else {
            return Optional.of(operandStack.pop());
        }
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
                && (functionStack.isEmpty() || isInBrackets())){
            popTopOperator();
        }
        operatorStack.push(operator);
    }

    /**
     * Saves the position of opening bracket.
     * Adds last function to function stack.
     */
    public void pushOpeningBracket() {
        functionStack.push(new FunctionContext(nextFunction, operatorStack.size()));
        nextFunction = new BracketsFunction();
    }

    /**
     * Evaluates the inner expression and add result into stack of operands.
     */
    public void pushClosingBracket() throws CalculationException {
        if (!functionStack.isEmpty()) {
            while (isInBrackets()) {
                popTopOperator();
            }
            executeCurrentFunction();
        } else {
            errorHandler.raiseError("Missed opening bracket.");
        }
    }

    private void executeCurrentFunction() throws CalculationException {
        if (isFunctionWithArgument()) {
            addCurrentFunctionArgument(operandStack.pop());
        }
        functionStack.pop().execute(errorHandler).ifPresent(operandStack::push);
    }

    public void pushFunction(Function function) {
        nextFunction = function;
    }

    public void pushArgumentSeparator() throws CalculationException {
        while (!operatorStack.isEmpty()
                && isInBrackets()) {
            popTopOperator();
        }
        addCurrentFunctionArgument(operandStack.pop());

    }

    private void addCurrentFunctionArgument(double argument){
        functionStack.peek().addArgument(argument);
    }

    private boolean isInBrackets(){
        return !functionStack.isEmpty() && operatorStack.size() > functionStack.peek().getBracketPosition();
    }

    private boolean isFunctionWithArgument() {
        return operandStack.size() > operatorStack.size();
    }

    public void declareVariable(String variableName) {
        nextVariable = variableName;
    }

    /**
     * Assigns result of evaluation to variable if it was declared.
     * @throws CalculationException
     */
    public void pushDelimiter() throws CalculationException {
        if (nextVariable != null) {
            variables.put(nextVariable, getResult().get());
            nextVariable = null;
        } else {
            result = getResult();
        }
    }

    public Set<String> getVariableNames() {
        return variables.keySet();
    }

    public void pushVariable(String key) {
        pushNumber(variables.get(key));
    }
}
