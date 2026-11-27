package org.ioopm.calculator.ast;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

public class FunctionChecker implements Visitor {

    private boolean checkingFunctionDeclaration = false;
    private boolean checkingFunctionCall = false;
    private EnvironmentStack definedVariables = new EnvironmentStack(new Environment());
    private Stack<FunctionDeclaration> currentlyEvaluatingFunction = new Stack<FunctionDeclaration>();

    protected LinkedList<Sequence> checkedSequences;
    protected boolean error;
    protected EnvironmentStack env;

    @Override
    public SymbolicExpression visit(Addition n) {
        n.lhs().accept(this);
        n.rhs().accept(this);

        return null;
    }

    @Override
    public SymbolicExpression visit(Assignment n) {
        if (checkingFunctionCall) {
            this.definedVariables.put((Variable) n.rhs(), null);
        }

        n.lhs().accept(this);
        n.rhs().accept(this);

        return null;
    }

    @Override
    public SymbolicExpression visit(Clear n) {
        if (checkingFunctionDeclaration) {
            System.out.println("Can not execute commands within the body of a function");
            this.error = true;
        }

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
        if (checkingFunctionDeclaration) {
            System.out.println("Can not execute commands within the body of a function");
            this.error = true;
        }

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
        if (checkingFunctionCall && !this.env.containsKey(n) && !this.definedVariables.containsKey(n)) {
            System.out.println("Undefined variable in function body");
            this.error = true;
        }
        return null;
    }

    @Override
    public SymbolicExpression visit(Vars n) {
        if (checkingFunctionDeclaration) {
            System.out.println("Can not execute commands within the body of a function");
            this.error = true;
        }

        return null;
    }

    @Override
    public SymbolicExpression visit(NamedConstant n) {
        return null;
    }

    @Override
    public SymbolicExpression visit(Scope n) {
        this.definedVariables.pushEnvironment();
        n.getExpr().accept(this);
        this.definedVariables.popEnvironment();
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
        this.checkingFunctionDeclaration = true;
        this.currentlyEvaluatingFunction.push(n);

        n.getSequence().accept(this);

        this.currentlyEvaluatingFunction.pop();
        this.checkingFunctionDeclaration = false;

        return null;
    }

    private boolean isExistingFunction(FunctionCall n, SymbolicExpression expr) {
        if (expr == null) {
            return n.getFunctionName().equals(this.currentlyEvaluatingFunction.peek().getFunctionName());
        }

        return expr.isFunction();
    }

    @Override
    public SymbolicExpression visit(FunctionCall n) {
        this.checkingFunctionCall = true;
        this.definedVariables.pushEnvironment();

        Variable name = n.getFunctionName();
        SymbolicExpression expr = this.env.get(name);
        if (!isExistingFunction(n, expr)) {
            System.out.println("Function undefined");
            this.error = true;
            return null;
        }

        FunctionDeclaration function = expr == null ? this.currentlyEvaluatingFunction.peek() : (FunctionDeclaration) expr;

        int argc = n.getArguments().size();
        int paramc = function.getParameters().size();

        if (argc > paramc) {
            System.out.println("Error, function '%s' called with too many arguments. Expected %d, got %d."
                    .formatted(name, paramc, argc));
            this.error = true;
        } else if (argc < paramc) {
            System.out.println("Error, function '%s' called with too few arguments. Expected %d, got %d."
                    .formatted(name, paramc, argc));
            this.error = true;
        }

        for (Iterator<Variable> iter = function.getParameters().iterator(); iter.hasNext();) {
            this.definedVariables.put(iter.next(), null);
        }

        function.getSequence().accept(this);

        this.definedVariables.popEnvironment();

        this.checkingFunctionCall = false;

        return null;
    }

    @Override
    public SymbolicExpression visit(Sequence n) {
        if (this.checkedSequences.contains(n)) {
            return null;
        }
        
        this.checkedSequences.add(n);
        
        for (Iterator<SymbolicExpression> iter = n.getExpressions().iterator(); iter.hasNext();) {
            iter.next().accept(this);
        }
        
        return null;
    }

}
