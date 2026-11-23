package org.ioopm.calculator.ast;

public class Multiplication extends Binary implements ExpressionInterface {
    
    public Multiplication(SymbolicExpression lhs, SymbolicExpression rhs) {
        super(lhs, rhs);
        this.priority = 60;
    }

    @Override 
    public String getName() {
        return "*";
    }

    @Override 
    public SymbolicExpression accept(Visitor v) {
        return v.visit(this);
    }

}

