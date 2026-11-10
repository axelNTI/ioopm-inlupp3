package org.ioopm.calculator.ast;

public class Cos extends Unary {

    public Cos(SymbolicExpression primary) {
        super(primary);
        this.priority = 90;
    }

    public String getName() {
        return "cos";
    }

    public String toString() {
        return "cos(%s)".formatted(primary);
    }

    public SymbolicExpression eval() {
        SymbolicExpression arg = this.primary.eval();

        if (!arg.isConstant()) {
            return new Cos(arg);
        }

        return new Constant(Math.cos(arg.getValue()));
    }
}
