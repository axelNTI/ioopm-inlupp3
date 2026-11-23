package org.ioopm.calculator.ast;

public class Exp extends Unary implements FunctionInterface {

    public Exp(SymbolicExpression primary) {
        super(primary);
        this.priority = 90;
    }

    @Override 
    public String getName() {
        return "Exp";
    }

    @Override
    public SymbolicExpression accept(Visitor v) {
        return v.visit(this);
    }
}
