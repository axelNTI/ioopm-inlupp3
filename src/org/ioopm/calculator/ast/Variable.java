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
}
