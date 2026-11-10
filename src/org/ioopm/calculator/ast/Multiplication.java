package org.ioopm.calculator.ast;

public class Multiplication extends Binary {
    
    public Multiplication(SymbolicExpression lhs, SymbolicExpression rhs) {
        super(lhs, rhs);
        this.priority = 60;
    }

    public String getName() {
        return "*";
    }

    public String toString() {
        return lhs + " * " + rhs;
    }
}
