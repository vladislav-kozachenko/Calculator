package javaclasses.calculator.impl.parser;

import javaclasses.calculator.CalculationException;
import javaclasses.calculator.impl.EvaluationContext;
import javaclasses.calculator.impl.ExpressionParser;
import javaclasses.calculator.impl.ExpressionReader;
import javaclasses.calculator.impl.command.DelimiterCommand;

public class DelimiterParser implements ExpressionParser {

    @Override
    public boolean parse(ExpressionReader reader, EvaluationContext context) throws CalculationException {

        final String expression = reader.getRemainingExpression();

        if (expression.startsWith(";")) {
            new DelimiterCommand().execute(context);
            reader.incrementParsePosition(1);
            return true;

        }

        return false;
    }
}
