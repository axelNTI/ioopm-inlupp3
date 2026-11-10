package org.ioopm.calculator.ast;

public class Log extends Unary {

    public Log(SymbolicExpression primary) {
        super(primary);
        this.priority = 90;
    }

    public String getName() {
        return "log";
    }

    public String toString() {
        return "log(%s)".formatted(primary);
    }
}
