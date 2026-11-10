package org.ioopm.calculator.ast;

public class Variable extends Atom {
    private String identifier;

    public Variable(String identifier) {
        this.identifier = identifier;
    }

    public String toString() {
        return this.identifier;
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

    public SymbolicExpression eval(Environment vars) {
        SymbolicExpression arg = vars.get(this);

        if (arg == null) {
            return this;
        }

        if (!arg.isConstant()) {
            return arg;
        }

        return new Constant(arg.getValue());
    }

    public int hashCode() {
        int result = 0;
        char[] ch = identifier.toCharArray();

        for (char c : ch) {
            result = result * 31 + Character.getNumericValue(c);
        }

        return result;
    }
}
