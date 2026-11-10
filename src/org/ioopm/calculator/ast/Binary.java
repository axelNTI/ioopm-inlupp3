package org.ioopm.calculator.ast;

public abstract class Binary extends SymbolicExpression {
    protected SymbolicExpression lhs = null;
    protected SymbolicExpression rhs = null;

    public Binary(SymbolicExpression lhs, SymbolicExpression rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    public boolean equals(Object other) {
        if (other instanceof Binary) {
            return this.equals((Binary) other);
        } else {
            return false;
        }
    }

    public boolean equals(Binary other) {
        boolean lhs = this.lhs.equals(other.lhs);
        boolean rhs = this.rhs.equals(other.rhs);
        boolean name = this.getName().compareTo(other.getName()) == 0;

        return lhs && rhs && name;
    }
}
