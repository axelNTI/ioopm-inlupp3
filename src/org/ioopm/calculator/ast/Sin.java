package org.ioopm.calculator.ast;

public class Sin extends Unary {

    public Sin(SymbolicExpression primary) {
        super(primary);
        this.priority = 90;
    }

    public String getName() {
        return "sin";
    }

    public String toString() {
        return "sin(%s)".formatted(primary);
    }
}
