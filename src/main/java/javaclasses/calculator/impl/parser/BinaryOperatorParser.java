package javaclasses.calculator.impl.parser;

import javaclasses.calculator.CalculationException;
import javaclasses.calculator.impl.EvaluationContext;
import javaclasses.calculator.impl.ExpressionParser;
import javaclasses.calculator.impl.ExpressionReader;
import javaclasses.calculator.impl.command.BinaryOperatorCommand;
import javaclasses.calculator.impl.operator.BinaryOperatorFactory;

public class BinaryOperatorParser implements ExpressionParser {

    private final BinaryOperatorFactory factory = new BinaryOperatorFactory();

    @Override
    public boolean parse(ExpressionReader reader, EvaluationContext context) throws CalculationException {

        final String expression = reader.getRemainingExpression();

        for (String sign : factory.getOperatorSigns()) {
            if (expression.startsWith(sign)) {

                new BinaryOperatorCommand(factory.getBinaryOperator(sign)).execute(context);
                reader.incrementParsePosition(sign.length());

                return true;
            }
        }

        return false;
    }
}
