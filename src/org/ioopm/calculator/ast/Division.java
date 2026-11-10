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
        return lhs + " / " + rhs;
    }

    public SymbolicExpression eval() {
        SymbolicExpression lhs = this.lhs.eval();
        SymbolicExpression rhs = this.rhs.eval();

        if (lhs.isConstant() && rhs.isConstant()) {
            if (rhs.getValue() == 0) throw new InvalidInputException("Can't divide by 0");

            return new Constant(lhs.getValue() / rhs.getValue());
        }

        return new Division(lhs, rhs);
    }
}
