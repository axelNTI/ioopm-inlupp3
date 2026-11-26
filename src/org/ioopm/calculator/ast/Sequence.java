package org.ioopm.calculator.ast;

import java.util.LinkedList;

public class Sequence extends SymbolicExpression {
    private LinkedList<SymbolicExpression> sequence;

    public Sequence(LinkedList<SymbolicExpression> sequence) {
        this.sequence = sequence;
    }

    public LinkedList<SymbolicExpression> getExpressions() {
        return sequence;
    }

    @Override
    public SymbolicExpression accept(Visitor v) {
        return v.visit(this);
    }
}
