package javaclasses.calculator.impl;

public enum State {
    START,
    NUMBER,
    BINARY_OPERATOR,
    OPEN_BRACE,
    CLOSE_BRACE,
    FUNCTION,
    FINISH;

    private State[] possibleStates;

    static {
        START.possibleStates = new State[]{State.NUMBER, State.FUNCTION, State.OPEN_BRACE};
        NUMBER.possibleStates = new State[]{State.BINARY_OPERATOR, State.CLOSE_BRACE, State.FINISH};
        BINARY_OPERATOR.possibleStates = new State[]{State.NUMBER, State.FUNCTION, State.OPEN_BRACE};
        OPEN_BRACE.possibleStates = new State[]{State.NUMBER, State.FUNCTION, State.OPEN_BRACE};
        CLOSE_BRACE.possibleStates = new State[]{State.BINARY_OPERATOR, State.CLOSE_BRACE, State.FINISH};
        FUNCTION.possibleStates = new State[]{State.BINARY_OPERATOR, State.CLOSE_BRACE, State.FINISH};
        FINISH.possibleStates = new State[]{};
    }

    public State[] getPossibleStates() {
        return possibleStates;
    }
}
