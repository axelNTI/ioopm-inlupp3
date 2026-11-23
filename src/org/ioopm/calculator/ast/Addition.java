package org.ioopm.calculator.ast;

public class Addition extends Binary implements  ExpressionInterface {

    public Addition(SymbolicExpression lhs, SymbolicExpression rhs) {
        super(lhs, rhs);
        this.priority = 30;
    }

    @Override 
    public String getName() {
        return "+";
    }

    @Override
    public SymbolicExpression accept(Visitor v) {
        return v.visit(this);
    }
}
