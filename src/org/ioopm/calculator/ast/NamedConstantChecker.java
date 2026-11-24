package org.ioopm.calculator.ast;

import java.util.LinkedList;

public class NamedConstantChecker implements Visitor {
    protected LinkedList<String> exceptions;

    @Override
    public SymbolicExpression visit(Addition n) {
        n.lhs().accept(this);
        n.rhs().accept(this);

        return null;
    }

    @Override
    public SymbolicExpression visit(Assignment n) {
        n.lhs().accept(this);
        n.rhs().accept(this);

        if (n.rhs().isNamedConstant()) {
            this.exceptions.addLast("%s = %s".formatted(n.lhs(), n.rhs()));
        }

        return null;
    }

    @Override
    public SymbolicExpression visit(Clear n) {
        return null;
    }

    @Override
    public SymbolicExpression visit(Constant n) {
        return null;
    }

    @Override
    public SymbolicExpression visit(Cos n) {
        n.primary().accept(this);

        return null;
    }

    @Override
    public SymbolicExpression visit(Division n) {
        n.lhs().accept(this);
        n.rhs().accept(this);

        return null;
    }

    @Override
    public SymbolicExpression visit(Exp n) {
        n.primary().accept(this);

        return null;
    }

    @Override
    public SymbolicExpression visit(Log n) {
        n.primary().accept(this);

        return null;
    }

    @Override
    public SymbolicExpression visit(Multiplication n) {
        n.lhs().accept(this);
        n.rhs().accept(this);

        return null;
    }

    @Override
    public SymbolicExpression visit(Negation n) {
        n.primary().accept(this);

        return null;
    }

    @Override
    public SymbolicExpression visit(Quit n) {
        return null;
    }

    @Override
    public SymbolicExpression visit(Sin n) {
        n.primary().accept(this);

        return null;
    }

    @Override
    public SymbolicExpression visit(Subtraction n) {
        n.lhs().accept(this);
        n.rhs().accept(this);

        return null;
    }

    @Override
    public SymbolicExpression visit(Variable n) {
        return null;
    }

    @Override
    public SymbolicExpression visit(Vars n) {
        return null;
    }

    @Override
    public SymbolicExpression visit(NamedConstant n) {
        return null;
    }

    @Override
    public SymbolicExpression visit(Scope n) {
        n.getExpr().accept(this);

        return null;
    }

    @Override 
    public SymbolicExpression visit(Conditional n) {
        n.getFirstIdentifier().accept(this);
        n.getSecondIdentifier().accept(this);
        n.getFirstScope().accept(this);
        n.getSecondScope().accept(this);

        return null;
    }

}
