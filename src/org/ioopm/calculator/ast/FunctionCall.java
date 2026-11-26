package org.ioopm.calculator.ast;

import java.util.LinkedList;

public class FunctionCall extends SymbolicExpression {
    private Variable name;
    private LinkedList<SymbolicExpression> arguments;

    public FunctionCall(Variable identifier, LinkedList<SymbolicExpression> args) {
        this.name = identifier;
        this.arguments = args;
    }

    public Variable getFunctionName() {
        return name;
    }

    public LinkedList<SymbolicExpression> getArguments() {
        return arguments;
    }

    @Override
    public SymbolicExpression accept(Visitor v) {
        return v.visit(this);
    }
}
