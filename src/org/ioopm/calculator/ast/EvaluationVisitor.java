package org.ioopm.calculator.ast;

import java.util.LinkedList;

public class EvaluationVisitor implements Visitor {
    private Environment env = null;
    private NamedConstantChecker checker = new NamedConstantChecker();

    public SymbolicExpression evaluate(SymbolicExpression topLevel, Environment env) {
        this.env = env;
        return topLevel.accept(this);
    }

    public boolean check(SymbolicExpression topLevel) {
        checker.exceptions = new LinkedList<String>();
        topLevel.accept(checker);

        if (checker.exceptions.size() == 0) {
            return true;
        }

        System.out.println("Error, assignments to named constants:");
        while (checker.exceptions.size() > 0) {
            System.out.println("    %s".formatted(checker.exceptions.removeFirst()));
        }

        return false;
    }

    public SymbolicExpression visit(Addition n) {

        SymbolicExpression left = n.lhs().accept(this);
        SymbolicExpression right = n.rhs().accept(this);

        if (left.isConstant() && right.isConstant()) {
            return new Constant(left.getValue() + right.getValue());
        } else {
            return new Addition(left, right);
        }
    }

    public SymbolicExpression visit(Subtraction n) {

        SymbolicExpression left = n.lhs().accept(this);
        SymbolicExpression right = n.rhs().accept(this);

        if (left.isConstant() && right.isConstant()) {
            return new Constant(left.getValue() - right.getValue());
        } else {
            return new Subtraction(left, right);
        }
    }

    public SymbolicExpression visit(Multiplication n) {

        SymbolicExpression left = n.lhs().accept(this);
        SymbolicExpression right = n.rhs().accept(this);

        if (left.isConstant() && right.isConstant()) {
            return new Constant(left.getValue() * right.getValue());
        } else {
            return new Multiplication(left, right);
        }
    }

    public SymbolicExpression visit(Division n) {

        SymbolicExpression left = n.lhs().accept(this);
        SymbolicExpression right = n.rhs().accept(this);

        if (left.isConstant() && right.isConstant()) {
            return new Constant(left.getValue() / right.getValue());
        } else {
            return new Division(left, right);
        }
    }

    public SymbolicExpression visit(Assignment n) {

        SymbolicExpression left = n.lhs().accept(this);
        SymbolicExpression right = n.rhs().accept(this);

        if (right instanceof Variable) {
            this.env.put((Variable) right, left);
            return left;
        }

        throw new IllegalAssignmentException("Can not assign to anything other than variables");
    }

    public SymbolicExpression visit(Sin n) {

        SymbolicExpression arg = n.primary().accept(this);

        if (!arg.isConstant()) {
            return new Sin(arg);
        }

        return new Constant(Math.sin(arg.getValue()));
    }

    public SymbolicExpression visit(Cos n) {

        SymbolicExpression arg = n.primary().accept(this);

        if (!arg.isConstant()) {
            return new Cos(arg);
        }

        return new Constant(Math.cos(arg.getValue()));
    }

    public SymbolicExpression visit(Exp n) {

        SymbolicExpression arg = n.primary().accept(this);

        if (!arg.isConstant()) {
            return new Exp(arg);
        }

        return new Constant(Math.exp(arg.getValue()));
    }

    public SymbolicExpression visit(Log n) {

        SymbolicExpression arg = n.primary().accept(this);

        if (!arg.isConstant()) {
            return new Log(arg);
        }

        return new Constant(Math.log(arg.getValue()));
    }

    public SymbolicExpression visit(Negation n) {

        SymbolicExpression arg = n.primary().accept(this);

        if (!arg.isConstant()) {
            return new Negation(arg);
        }

        return new Constant(-(arg.getValue()));
    }

    public SymbolicExpression visit(Variable n) {
        SymbolicExpression arg = this.env.get(n);

        if (arg == null) {
            return n;
        }

        if (!arg.isConstant()) {
            return arg;
        }

        return new Constant(arg.getValue());
    }

    public SymbolicExpression visit(Constant n) {
        return n;
    }

    public SymbolicExpression visit(NamedConstant n) {
        return new Constant(n.getValue());
    }

    public SymbolicExpression visit(Vars n) {
        return n.accept(this);
    }

    public SymbolicExpression visit(Clear n) {
        return n.accept(this);
    }

    public SymbolicExpression visit(Quit n) {
        return n.accept(this);
    }
}
