package javaclasses.calculator.impl.parser;

import javaclasses.calculator.impl.EvaluationContext;
import javaclasses.calculator.impl.ExpressionParser;
import javaclasses.calculator.impl.ExpressionReader;
import javaclasses.calculator.impl.function.FunctionFactory;

public class FunctionParser implements ExpressionParser {

    FunctionFactory factory = new FunctionFactory();

    @Override
    public boolean parse(ExpressionReader reader, EvaluationContext context) {

        final String expression = reader.getRemainingExpression();

        for (String name : factory.getFunctionNames()) {
            if (expression.startsWith(name)) {

                context.pushFunction(factory.getFunction(name));
                reader.incrementParsePosition(name.length());
                parseArguments();

                return true;
            }
        }

        return false;
    }

    private void parseArguments() {
    }
}
