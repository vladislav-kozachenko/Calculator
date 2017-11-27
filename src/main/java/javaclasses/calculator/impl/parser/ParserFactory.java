package javaclasses.calculator.impl.parser;

import javaclasses.calculator.impl.ExpressionParser;
import javaclasses.calculator.impl.State;

import java.util.HashMap;
import java.util.Map;

import static javaclasses.calculator.impl.State.*;

public class ParserFactory {

    private final Map<State, ExpressionParser> parsers = new HashMap<State, ExpressionParser>() {{
        put(NUMBER, new NumberParser());
        put(BINARY_OPERATOR, new BinaryOperatorParser());
        put(OPENING_BRACKET, new OpeningBracketParser());
        put(CLOSING_BRACKET, new ClosingBracketParser());
        put(FUNCTION_NAME, new FunctionParser());
        put(ARGUMENT_SEPARATOR, new ArgumentSeparatorParser());
        put(VARIABLE_ASSIGNMENT, new VariableAssignmentParser());
        put(DELIMITER, new DelimiterParser());
        put(VARIABLE_READING, new VariableReadingParser());
        put(FINISH, new EndOfExpressionParser());
    }};

    public ExpressionParser getParser(State state) {

        if (!parsers.containsKey(state)) {
            throw new IllegalStateException("Parser not found for state: " + state);
        }

        return parsers.get(state);
    }
}
