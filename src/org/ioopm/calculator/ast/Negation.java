package org.ioopm.calculator.ast;

public class Negation extends Unary {

    public Negation(SymbolicExpression primary) {
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
            return new Negation(arg);
        }

        return new Constant(-(arg.getValue()));
    }
}
