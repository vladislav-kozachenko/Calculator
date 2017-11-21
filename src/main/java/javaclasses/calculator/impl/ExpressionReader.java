package javaclasses.calculator.impl;

/**
 * Contains source expression and have pointer which points the position of last parsing.
 */
public class ExpressionReader {

    private final String expression;
    private int parsePosition = 0;

    /**
     *
     * @param expression expression which is needed to calculate.
     */
    public ExpressionReader(String expression) {
        this.expression = expression;
    }

    /**
     *
     * @return true if the pointer is on the end of expression.
     */
    public boolean endOfExpression() {
        return parsePosition >= expression.length();
    }

    /**
     *
     * @return position of pointer.
     */
    public int getParsePosition() {
        return parsePosition;
    }

    /**
     * Moves pointer right.
     * @param value used for moving on inputted number.
     */
    public void incrementParsePosition(int value) {
        parsePosition += value;
    }

    /**
     *
     * @return substring from current parsing position to end of expression.
     */
    public String getRemainingExpression() {
        return expression.substring(parsePosition);
    }
}
