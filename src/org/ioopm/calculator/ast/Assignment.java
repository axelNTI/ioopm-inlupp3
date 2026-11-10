package org.ioopm.calculator.ast;

public class Assignment extends Binary {
    
    public Assignment(SymbolicExpression lhs, SymbolicExpression rhs) {
        super(lhs, rhs);
        this.priority = 10;
    }
    
    public String getName() {
        return "=";
    }

    public String toString() {
        return lhs + " = " + rhs;
    }
}
