package org.ioopm.calculator.ast;

public abstract class Unary extends SymbolicExpression {
    protected SymbolicExpression primary = null;

    public Unary(SymbolicExpression primary) {
        this.primary = primary;
    }

}
