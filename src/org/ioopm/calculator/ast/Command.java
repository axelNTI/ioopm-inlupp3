package org.ioopm.calculator.ast;

public abstract class Command extends SymbolicExpression {
    protected String command;

    public Command(String command) {
        this.command = command;
    }

    public String toString() {
        return command;
    }

    public boolean equals(Object other) {
        if (other instanceof Command) {
            return this.equals((Command) other);
        }
        return false;
    }

    public boolean equals(Command other) {
        return this.command.compareTo(other.command) == 0;
    }
}
