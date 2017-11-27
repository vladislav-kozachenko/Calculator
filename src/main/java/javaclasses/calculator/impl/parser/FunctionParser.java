package javaclasses.calculator.impl.parser;

import javaclasses.calculator.impl.EvaluationContext;
import javaclasses.calculator.impl.ExpressionParser;
import javaclasses.calculator.impl.ExpressionReader;
import javaclasses.calculator.impl.function.FunctionFactory;

public class FunctionParser implements ExpressionParser {


    @Override
    public boolean parse(ExpressionReader reader, EvaluationContext context) {

        final FunctionFactory factory = new FunctionFactory();
        final String expression = reader.getRemainingExpression();

        for (String name : factory.getFunctionNames()) {
            if (expression.startsWith(name)) {

                context.pushFunction(factory.getFunction(name));
                reader.incrementParsePosition(name.length());

                return true;
            }
        }

        return false;
    }
}
