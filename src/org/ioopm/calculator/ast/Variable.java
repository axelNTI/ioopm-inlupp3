package org.ioopm.calculator.ast;

public class Variable extends Atom {
    private String identifier;
    private SymbolicExpression value;

    public Variable(String identifier) {
        this.identifier = identifier;
    }

    public String toString() {
        return this.identifier;
    }

    public void setValue(SymbolicExpression value) {
        this.value = value;
    }

    public SymbolicExpression getExpression() {
        return this.value;
    }

    public boolean equals(Object other) {
        if (other instanceof Constant) {
            return this.equals((Variable) other);
        } else {
            return false;
        }
    }

    public boolean equals(Variable other) {
        return this.identifier.compareTo(other.identifier) == 0;
    }

    public SymbolicExpression eval() {
        SymbolicExpression arg = this.value.eval();

        if (!arg.isConstant()) {
            return arg;
        }

        return new Constant(arg.getValue());
    }
}
