package javaclasses.calculator.impl.parser;

import javaclasses.calculator.CalculationException;
import javaclasses.calculator.impl.EvaluationContext;
import javaclasses.calculator.impl.ExpressionParser;
import javaclasses.calculator.impl.ExpressionReader;
import javaclasses.calculator.impl.command.NumberCommand;

import java.text.DecimalFormat;
import java.text.ParsePosition;

public class NumberParser implements ExpressionParser {

    @Override
    public boolean parse(ExpressionReader reader, EvaluationContext context) throws CalculationException {


        final DecimalFormat format = new DecimalFormat("0.0");
        final ParsePosition parsePosition = new ParsePosition(0);

        final Number number = format.parse(reader.getRemainingExpression(), parsePosition);

        if (parsePosition.getErrorIndex() == -1) {

            final double doubleValue = number.doubleValue();
            new NumberCommand(doubleValue).execute(context);
            reader.incrementParsePosition(parsePosition.getIndex());

            return true;
        }

        return false;
    }
}
