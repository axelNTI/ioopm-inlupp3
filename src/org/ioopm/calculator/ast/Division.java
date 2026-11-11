package org.ioopm.calculator.ast;

public class Division extends Binary {

    public Division(SymbolicExpression lhs, SymbolicExpression rhs) {
        super(lhs, rhs);
        this.priority = 60;
    }

    public String getName() {
        return "/";
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

        return lhsStr + " / " + rhsStr;
    }

    public SymbolicExpression eval(Environment vars) {
        SymbolicExpression lhs = this.lhs.eval(vars);
        SymbolicExpression rhs = this.rhs.eval(vars);

        if (lhs.isConstant() && rhs.isConstant()) {
            if (rhs.getValue() == 0) throw new InvalidInputException("Can't divide by 0");

            return new Constant(lhs.getValue() / rhs.getValue());
        }

        return new Division(lhs, rhs);
    }
}
