package javaclasses.calculator.impl;

import javaclasses.calculator.impl.EvaluationContext;
import javaclasses.calculator.impl.ExpressionReader;

/**
 * Parser for mathematical expression.
 */
public interface ExpressionParser {

    /**
     * Parses next token in expression.
     * @param reader is the expression reader which may be parsed.
     * @param context is the output where parsed token must be saved.
     * @return true if parsing was successful.
     */
    boolean parse(ExpressionReader reader, EvaluationContext context);
}
