package org.ioopm.calculator.ast;

public class Log extends Unary implements FunctionInterface {

    public Log(SymbolicExpression primary) {
        super(primary);
        this.priority = 90;
    }

    @Override 
    public String getName() {
        return "Log";
    }

    @Override
    public SymbolicExpression accept(Visitor v) {
        return v.visit(this);
    }
}
