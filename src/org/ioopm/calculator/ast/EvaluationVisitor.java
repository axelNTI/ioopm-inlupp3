package org.ioopm.calculator.ast;

public class EvaluationVisitor implements Visitor {
   private Environment env = null;

    public SymbolicExpression evaluate(SymbolicExpression topLevel, Environment env) {
        this.env = env;
        return topLevel.accept(this);
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
        SymbolicExpression arg = this.env.get(n).accept(this);

        if (arg == null) {
            return n;
        }

        if (!arg.isConstant()) {
            return arg;
        }

        return new Constant(arg.getValue());
    }

    public SymbolicExpression visit(Constant n) {
        SymbolicExpression arg = n.accept(this);

        return arg;
    }

    public SymbolicExpression visit(NamedConstant n) {
        SymbolicExpression arg = n.accept(this);

        return new Constant(arg.getValue());
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
