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

    public SymbolicExpression eval(Environment vars) {
        SymbolicExpression lhs = this.lhs.eval(vars);
        SymbolicExpression rhs = this.rhs.eval(vars);

        if (rhs instanceof Variable) {
            vars.put((Variable) rhs, lhs);
            return lhs;
        }

        return new Assignment(lhs, rhs);
    }
}
