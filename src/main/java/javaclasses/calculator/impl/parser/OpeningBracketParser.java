package javaclasses.calculator.impl.parser;

import javaclasses.calculator.CalculationException;
import javaclasses.calculator.impl.EvaluationContext;
import javaclasses.calculator.impl.ExpressionParser;
import javaclasses.calculator.impl.ExpressionReader;
import javaclasses.calculator.impl.command.OpeningBracketCommand;

public class OpeningBracketParser implements ExpressionParser {
    @Override
    public boolean parse(ExpressionReader reader, EvaluationContext context) throws CalculationException {

        String expression = reader.getRemainingExpression();

        if (expression.startsWith("(")) {

            new OpeningBracketCommand().execute(context);
            reader.incrementParsePosition(1);

            return true;
        }


        return false;
    }
}
