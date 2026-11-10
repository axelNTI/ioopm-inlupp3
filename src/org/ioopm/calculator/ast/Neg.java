package org.ioopm.calculator.ast;

public class Neg extends Unary {

    public Neg(SymbolicExpression primary) {
        super(primary);
        this.priority = 100;
    }

    public String getName() {
        return "-";
    }

    public String toString() {
        return "-%s".formatted(primary);
    }

    public SymbolicExpression eval() {
        SymbolicExpression arg = this.primary.eval();

        if (!arg.isConstant()) {
            return new Neg(arg);
        }

        return new Constant(-(arg.getValue()));
    }
}
