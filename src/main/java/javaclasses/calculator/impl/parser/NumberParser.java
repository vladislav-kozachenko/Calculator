package javaclasses.calculator.impl.parser;

import javaclasses.calculator.impl.EvaluationContext;
import javaclasses.calculator.impl.ExpressionParser;
import javaclasses.calculator.impl.ExpressionReader;

import java.text.DecimalFormat;
import java.text.ParsePosition;

public class NumberParser implements ExpressionParser {

    @Override
    public boolean parse(ExpressionReader reader, EvaluationContext context) {


        final DecimalFormat format = new DecimalFormat("0.0");
        final ParsePosition parsePosition = new ParsePosition(0);

        final Number number = format.parse(reader.getRemainingExpression(), parsePosition);

        if (parsePosition.getErrorIndex() == -1) {

            final double doubleValue = number.doubleValue();
            context.pushNumber(doubleValue);
            reader.incrementParsePosition(parsePosition.getIndex());

            return true;
        }

        return false;
    }
}
