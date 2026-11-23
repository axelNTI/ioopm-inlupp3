package org.ioopm.calculator.ast;

public class Cos extends Unary implements FunctionInterface {

    public Cos(SymbolicExpression primary) {
        super(primary);
        this.priority = 90;
    }

    @Override 
    public String getName() {
        return "Cos";
    }

    @Override
    public SymbolicExpression accept(Visitor v) {
        return v.visit(this);
    }
}
