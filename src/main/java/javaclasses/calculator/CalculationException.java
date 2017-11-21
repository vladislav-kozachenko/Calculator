package javaclasses.calculator;

public class CalculationException extends Exception {

    private final int errorPosition;

    public CalculationException(String message, int errorPosition) {
        super(message);
        this.errorPosition = errorPosition;
    }

    public int getErrorPosition() {
        return errorPosition;
    }
}
