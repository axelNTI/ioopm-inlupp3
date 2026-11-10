package org.ioopm.calculator.ast;

public class Addition extends Binary {

    public Addition(SymbolicExpression lhs, SymbolicExpression rhs) {
        super(lhs, rhs);
        this.priority = 30;
    }

    public String getName() {
        return "+";
    }

    public String toString() {
        return lhs + " + " + rhs;
    }

    public SymbolicExpression eval() {
        SymbolicExpression lhs = this.lhs.eval();
        SymbolicExpression rhs = this.rhs.eval();

        if (lhs.isConstant() && rhs.isConstant()) {
            return new Constant(lhs.getValue() + rhs.getValue());
        }

        return new Addition(lhs, rhs);
    }
}
