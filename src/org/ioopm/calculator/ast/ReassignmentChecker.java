package org.ioopm.calculator.ast;

import java.util.LinkedList;

public class ReassignmentChecker implements Visitor {
    protected LinkedList<String> variables;

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

        if (n.rhs().isVariable()) {
            this.variables.addLast("Error, the variable %s is reassigned".formatted(n.rhs()));
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

}
