package org.ioopm.calculator.ast;

import java.util.HashMap;

public abstract class SymbolicExpression {
    protected int priority = 0;

    public boolean isConstant() {
        return false;
    }

    public String getName() {
        throw new RuntimeException("getName() called on expression with no operator");
    }

    public int getPriority() {
        return priority;
    }

    public double getValue() {
        throw new RuntimeException("getValue() called on expression that is not a constant");
    }

    public SymbolicExpression eval(HashMap<Variable, SymbolicExpression> vars) {
        throw new InvalidInputException("Can't Evalutate");
    }
}
