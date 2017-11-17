package javaclasses.finiteStateMachine;

public abstract class AbstractStateMachine {

    public State getState() {
        return state;
    }

    public void changeState(State state) {
        this.state = state;
    }

    private State state;

}
