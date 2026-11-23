package org.ioopm.calculator.ast;

public class Division extends Binary implements ExpressionInterface {

    public Division(SymbolicExpression lhs, SymbolicExpression rhs) {
        super(lhs, rhs);
        this.priority = 60;
    }

    @Override 
    public String getName() {
        return "/";
    }   

    @Override 
    public String toString() {
        String lhsStr = "";
        String rhsStr = "";
        if (lhs.priority < this.priority) {
            lhsStr = "( " + lhs + " )";
        } else {
            lhsStr = lhs.toString();
        }

        if (rhs instanceof Binary) {
            rhsStr = "( " + rhs + " )";
        } else {
            rhsStr = rhs.toString();
        }

        return lhsStr + " / " + rhsStr;
    }

    @Override 
    public SymbolicExpression accept(Visitor v) {
        return v.visit(this);
    }
}
