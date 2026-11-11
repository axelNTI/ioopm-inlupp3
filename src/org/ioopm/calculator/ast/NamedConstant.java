package org.ioopm.calculator.ast;

public class NamedConstant extends Atom {
    private String name;
    private Double value;

    public NamedConstant(String name, Double value) {
        this.name = name;
        this.value = value;
        this.priority = 150;
    }

    public String toString() {
        return this.name;
    }

    public boolean equals(Object other) {
        if (other instanceof NamedConstant) {
            return this.equals((NamedConstant) other);
        } else {
            return false;
        }
    }

    public boolean equals(NamedConstant other) {
        return this.name.compareTo(other.name) == 0;
    }

    public SymbolicExpression eval(Environment vars) {
        return new Constant(value);
    }
}
