package org.ioopm.calculator.ast;

import java.util.LinkedList;

public class FunctionDeclaration extends SymbolicExpression {
    private Variable name;
    private LinkedList<Variable> parameters;
    private Sequence sequence;

    public FunctionDeclaration(Variable variableName, LinkedList<Variable> parameters, Sequence sequence) {
        this.name = variableName;
        this.sequence = sequence;
        this.parameters = new LinkedList<Variable>();

        for (Variable var : parameters) {
            this.parameters.addLast(var);
        }
    }

    public Variable getFunctionName() {
        return name;
    }

    public LinkedList<Variable> getParameters() {
        return parameters;
    }

    public Sequence getSequence() {
        return sequence;
    }

    @Override
    public SymbolicExpression accept(Visitor v) {
        return v.visit(this);
    }

    @Override
    public boolean isFunction() {
        return true;
    }
}
