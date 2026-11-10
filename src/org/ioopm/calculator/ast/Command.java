package org.ioopm.calculator.ast;

public abstract class Command extends SymbolicExpression {
    protected String command;

    public Command(String command) {
        this.command = command;
    }

    public String toString() {
        return command;
    }
}
