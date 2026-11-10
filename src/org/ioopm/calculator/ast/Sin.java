package org.ioopm.calculator.ast;

public class Sin extends Unary {

    public Sin(SymbolicExpression primary) {
        super(primary);
        this.priority = 90;
    }

    public String getName() {
        return "sin";
    }

    public String toString() {
        return "sin(%s)".formatted(primary);
    }

    public SymbolicExpression eval(Environment vars) {
        SymbolicExpression arg = this.primary.eval(vars);

        if (!arg.isConstant()) {
            return new Sin(arg);
        }

        return new Constant(Math.sin(arg.getValue()));
    }
}
