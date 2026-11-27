package org.ioopm.calculator.ast;

import org.ioopm.calculator.Statistics;
import java.util.Scanner;

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

    public boolean isCommand() {
        return false;
    }

    public void execute(Environment vars, Statistics stats, Scanner sc) {
        throw new RuntimeException("Tried to run execute on non-commands");
    } 

    public abstract SymbolicExpression accept(Visitor v);

    public boolean isNamedConstant() {
        return false;
    }

    public boolean isVariable() {
        return false;
    }

    public boolean isFunction() {
        return false;
    }
}
