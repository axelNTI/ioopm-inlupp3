package org.ioopm.calculator.ast;

public class Conditional extends SymbolicExpression {
    private SymbolicExpression identifier1;
    private SymbolicExpression identifier2;
    private String op;
    private Scope scope1;
    private Scope scope2;

    public Conditional(SymbolicExpression expr1, SymbolicExpression expr2, String op, Scope scope1, Scope scope2) {
        this.identifier1 = expr1;
        this.identifier2 = expr2;
        this.op = op;
        this.scope1 = scope1;
        this.scope2 = scope2;
    }

    public SymbolicExpression getFirstIdentifier() {
        return identifier1;
    }
    
    public SymbolicExpression getSecondIdentifier() {
        return identifier2;
    }

    public String getOperator() {
        return op;
    }

    public Scope getFirstScope() {
        return scope1;
    }

    public Scope getSecondScope() {
        return scope2;
    }

    @Override
    public SymbolicExpression accept(Visitor v) {
        return v.visit(this);
    }
}
