package org.ioopm.calculator.ast;

public class Exp extends Unary {

    public Exp(SymbolicExpression primary) {
        super(primary);
        this.priority = 90;
    }

    public String getName() {
        return "exp";
    }

    public String toString() {
        return "exp(%s)".formatted(primary);
    }
}
