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
        String lhsStr = "";
        String rhsStr = "";
        if (lhs.priority < this.priority) {
            lhsStr = "( " + lhs + " )";
        } else {
            lhsStr = lhs.toString();
        }

        if (rhs.priority < this.priority) {
            rhsStr = "( " + rhs + " )";
        } else {
            rhsStr = rhs.toString();
        }

        return lhsStr + " + " + rhsStr;
    }

    public SymbolicExpression eval(Environment vars) {
        SymbolicExpression lhs = this.lhs.eval(vars);
        SymbolicExpression rhs = this.rhs.eval(vars);

        if (lhs.isConstant() && rhs.isConstant()) {
            return new Constant(lhs.getValue() + rhs.getValue());
        }

        return new Addition(lhs, rhs);
    }
}
