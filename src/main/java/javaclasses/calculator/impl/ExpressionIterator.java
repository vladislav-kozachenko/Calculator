package javaclasses.calculator.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExpressionIterator {

    private final String expression;

    private final String regex = "(\\d+\\.*\\d*)|\\+|\\*|\\/|((max|min|sum)*\\(.+\\))";

    private final Pattern pattern;

    private final Matcher matcher;

    public ExpressionIterator(String expression) {
        this.expression = expression;
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(expression);
    }

    public boolean hasNext(){
        return matcher.find();
    }

    public String next(){
        return matcher.group();
    }

}
