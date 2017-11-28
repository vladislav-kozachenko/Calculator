package javaclasses.calculator.impl.parser;

import javaclasses.calculator.CalculationException;
import javaclasses.calculator.impl.EvaluationContext;
import javaclasses.calculator.impl.ExpressionParser;
import javaclasses.calculator.impl.ExpressionReader;
import javaclasses.calculator.impl.command.VariableSetterCommand;

public class VariableSetterParser implements ExpressionParser {

    @Override
    public boolean parse(ExpressionReader reader, EvaluationContext context) throws CalculationException {

        final String expression = reader.getRemainingExpression();

        if (expression.matches("^\\w+\\s*=.+")) {
            final int endOfVariableName = expression.indexOf("=");
            new VariableSetterCommand(expression.substring(0, endOfVariableName)
                    .replaceAll("\\s", ""))
                    .execute(context);
            reader.incrementParsePosition(endOfVariableName + 1);
            return true;

        }

        return false;
    }
}
