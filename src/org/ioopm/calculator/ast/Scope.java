package org.ioopm.calculator.ast;

public class Scope extends SymbolicExpression {
    SymbolicExpression expr;

    public Scope(SymbolicExpression expr) {
        this.expr = expr;
    }

    public SymbolicExpression getExpr() {
        return expr;
    }

    @Override
    public SymbolicExpression accept(Visitor v) {
        return v.visit(this);
    }
}
