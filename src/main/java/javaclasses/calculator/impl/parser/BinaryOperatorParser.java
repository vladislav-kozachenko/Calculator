package javaclasses.calculator.impl.parser;

import javaclasses.calculator.impl.EvaluationContext;
import javaclasses.calculator.impl.ExpressionParser;
import javaclasses.calculator.impl.ExpressionReader;
import javaclasses.calculator.impl.operator.BinaryOperatorFactory;

public class BinaryOperatorParser implements ExpressionParser {

    private final BinaryOperatorFactory factory = new BinaryOperatorFactory();

    @Override
    public boolean parse(ExpressionReader reader, EvaluationContext context) {

        final String expression = reader.getRemainingExpression();

        for (String sign : factory.getOperatorSigns()) {
            if (expression.startsWith(sign)) {

                context.pushBinaryOperator(factory.getBinaryOperator(sign));
                reader.incrementParsePosition(sign.length());

                return true;
            }
        }

        return false;
    }
}
