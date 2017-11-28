package javaclasses.calculator.impl.parser;

import javaclasses.calculator.CalculationException;
import javaclasses.calculator.impl.EvaluationContext;
import javaclasses.calculator.impl.ExpressionParser;
import javaclasses.calculator.impl.ExpressionReader;
import javaclasses.calculator.impl.command.VariableGetterCommand;

public class VariableGetterParser implements ExpressionParser {

    @Override
    public boolean parse(ExpressionReader reader, EvaluationContext context) throws CalculationException {

        final String expression = reader.getRemainingExpression();

        for (String name : context.getVariableNames()) {
            if (expression.startsWith(name)) {

                new VariableGetterCommand(name).execute(context);
                reader.incrementParsePosition(name.length());

                return true;
            }
        }

        return false;
    }
}
