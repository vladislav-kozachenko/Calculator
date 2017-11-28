package javaclasses.calculator.impl.parser;

import javaclasses.calculator.CalculationException;
import javaclasses.calculator.impl.EvaluationContext;
import javaclasses.calculator.impl.ExpressionParser;
import javaclasses.calculator.impl.ExpressionReader;
import javaclasses.calculator.impl.command.FunctionCommand;
import javaclasses.calculator.impl.function.FunctionFactory;

public class FunctionParser implements ExpressionParser {


    @Override
    public boolean parse(ExpressionReader reader, EvaluationContext context) throws CalculationException {

        final FunctionFactory factory = new FunctionFactory();
        final String expression = reader.getRemainingExpression();

        for (String name : factory.getFunctionNames()) {
            if (expression.startsWith(name)) {

                new FunctionCommand(factory.getFunction(name)).execute(context);
                reader.incrementParsePosition(name.length());

                return true;
            }
        }

        return false;
    }
}
