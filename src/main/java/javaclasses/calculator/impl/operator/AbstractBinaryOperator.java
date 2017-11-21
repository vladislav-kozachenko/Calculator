package javaclasses.calculator.impl.operator;

import javaclasses.calculator.impl.BinaryOperator;

/**
 * Abstract mathematical operator which considers priority of operators.
 */
public abstract class AbstractBinaryOperator implements BinaryOperator<AbstractBinaryOperator> {

    enum Priority {
        LOW, MEDIUM, HIGH
    }

    private final Priority priority;

    /**
     * Creates instance of operator and set its priority.
     * @param priority
     */
    public AbstractBinaryOperator(Priority priority) {
        this.priority = priority;
    }

    /**
     * Compares current operator with another using their priority.
     * @param o operator which may be compared with the current one.
     * @return 0 if priorities is the same,
     * integer > 0 if the first operator is more priority,
     * integer < 0 if the first operator is less priority.
     */
    @Override
    public int compareTo(AbstractBinaryOperator o) {
        return this.priority.compareTo(o.priority);
    }
}
