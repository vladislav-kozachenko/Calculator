package javaclasses.calculator.impl.parser;

import javaclasses.calculator.CalculationException;
import javaclasses.calculator.impl.EvaluationContext;
import javaclasses.calculator.impl.ExpressionParser;
import javaclasses.calculator.impl.ExpressionReader;
import javaclasses.calculator.impl.command.ClosingBracketCommand;

public class ClosingBracketParser implements ExpressionParser {
    @Override
    public boolean parse(ExpressionReader reader, EvaluationContext context) throws CalculationException {

        final String expression = reader.getRemainingExpression();

        if (expression.startsWith(")")) {

            new ClosingBracketCommand().execute(context);
            reader.incrementParsePosition(1);
            return true;

        }

        return false;
    }
}
