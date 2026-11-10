package org.ioopm.calculator.ast;

public class Subtraction extends Binary {

    public Subtraction(SymbolicExpression lhs, SymbolicExpression rhs) {
        super(lhs, rhs);
        this.priority = 30;
    }

    public String getName() {
        return "-";
    }

    public String toString() {
        return lhs + " - " + rhs;
    }
}
