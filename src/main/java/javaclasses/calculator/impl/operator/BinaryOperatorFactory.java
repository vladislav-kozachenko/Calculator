package javaclasses.calculator.impl.operator;

import javaclasses.calculator.impl.BinaryOperator;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static javaclasses.calculator.impl.operator.AbstractBinaryOperator.Priority.HIGH;
import static javaclasses.calculator.impl.operator.AbstractBinaryOperator.Priority.LOW;
import static javaclasses.calculator.impl.operator.AbstractBinaryOperator.Priority.MEDIUM;

/**
 * Implements factory which can create instances of different binary operators.
 */
public class BinaryOperatorFactory {

    private final Map<String, BinaryOperator> operators = new HashMap<String, BinaryOperator>() {{
        put("+", new PlusOperator(LOW));
        put("-", new MinusOperator(LOW));
        put("*", new MultiplicationOperator(MEDIUM));
        put("/", new DivisionOperator(MEDIUM));
        put("^", new ExponentiationOperator(HIGH));
    }};

    /**
     * Creates operator by its symbolic designation.
     * @param sign is a String which contains symbolic designation of operator.
     * @return instance of suitable binary operator.
     */
    public BinaryOperator getBinaryOperator(String sign) {

        if (!operators.containsKey(sign)) {
            throw new IllegalStateException("Binary operator not found: " + sign);
        }

        return operators.get(sign);
    }

    /**
     *
     * @return Set of possible operators
     */
    public Set<String> getOperatorSigns() {
        return operators.keySet();
    }
}
