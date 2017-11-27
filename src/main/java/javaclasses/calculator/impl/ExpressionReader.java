package javaclasses.calculator.impl;

/**
 * Represents container for source expression and have pointer which points the position of last parsing.
 */
public class ExpressionReader {

    private final String expression;
    private int parsePosition = 0;

    /**
     * Creates new instance of reader which contains the expression.
     * @param expression is a string with expression which is needed to calculate.
     */
    public ExpressionReader(String expression) {
        this.expression = expression;
    }

    /**
     * Checks if the parsing pointer reached end of expression.
     * @return true if the pointer is on the end of expression.
     */
    public boolean endOfExpression() {
        return parsePosition >= expression.length();
    }

    int getParsePosition() {
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
     * Creates new string from the next token to the end of string. Skips the whitespaces.
     * @return substring from current parsing position to end of expression.
     */
    public String getRemainingExpression() {
        while (!endOfExpression()
                && Character.isWhitespace(expression.charAt(parsePosition))){
            parsePosition++;
        }
        return expression.substring(parsePosition);
    }
}
