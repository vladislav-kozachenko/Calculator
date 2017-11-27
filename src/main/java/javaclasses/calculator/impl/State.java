package javaclasses.calculator.impl;

/**
 * Enumeration of possible states of calculator as a finite state machine.
 */
public enum State {

    START,
    NUMBER,
    BINARY_OPERATOR,
    OPENING_BRACKET,
    CLOSING_BRACKET,
    FUNCTION_NAME,
    ARGUMENT_SEPARATOR,
    VARIABLE_ASSIGNMENT,
    VARIABLE_READING,
    DELIMITER,
    FINISH;

}
