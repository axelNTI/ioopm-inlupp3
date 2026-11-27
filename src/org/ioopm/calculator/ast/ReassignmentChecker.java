package org.ioopm.calculator.ast;

import java.util.LinkedList;
import java.util.Iterator;

public class ReassignmentChecker implements Visitor {
    protected LinkedList<LinkedList<String>> variables;
    protected boolean duplicates;

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
            this.variables.peek().push("Error, the variable %s is reassigned".formatted(n.rhs()));
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
        variables.push(new LinkedList<String>());
        n.getExpr().accept(this);
        LinkedList<String> scope = variables.pop();
        LinkedList<String> list = new LinkedList<String>();

        for (String str : scope) {
            if (list.contains(str)) {
                System.out.println(str);
                continue;
            }
            list.addLast(str);
        }

        if (!duplicates) {
            this.duplicates = scope.size() != list.size();
        }

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

    @Override
    public SymbolicExpression visit(FunctionDeclaration n) {
        n.getSequence().accept(this);
        return null;
    }

    @Override
    public SymbolicExpression visit(FunctionCall n) {
        return null;
    }

    @Override
    public SymbolicExpression visit(Sequence n) {
        LinkedList<SymbolicExpression> seq = n.getExpressions();

        for (Iterator<SymbolicExpression> iter = seq.iterator(); iter.hasNext();) {
            iter.next().accept(this);
        }

        return null;
    }
}
