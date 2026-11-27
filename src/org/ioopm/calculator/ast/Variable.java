package org.ioopm.calculator.ast;

public class Variable extends Atom implements Comparable<Variable> {
    private String identifier;

    public Variable(String identifier) {
        this.identifier = String.copyValueOf(identifier.toCharArray());
        this.priority = 150;
    }

    @Override 
    public String toString() {
        return this.identifier;
    }

    @Override 
    public boolean equals(Object other) {
        if (other instanceof Variable) {
            return this.equals((Variable) other);
        } else {
            return false;
        }
    }

    public boolean equals(Variable other) {
        return this.identifier.compareTo(other.identifier) == 0;
    }

    @Override 
    public int hashCode() {
        return identifier.hashCode();
    }

    @Override
    public SymbolicExpression accept(Visitor v) {
        return v.visit(this);
    }

    @Override
    public int compareTo(Variable other) {
        return this.identifier.compareTo(other.identifier);
    }

    @Override
    public boolean isVariable() {
        return true;
    }
}
