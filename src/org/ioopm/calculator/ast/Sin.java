package org.ioopm.calculator.ast;

public class Sin extends Unary implements FunctionInterface {

    public Sin(SymbolicExpression primary) {
        super(primary);
        this.priority = 90;
    }

    @Override 
    public String getName() {
        return "Sin";
    }

    @Override
    public SymbolicExpression accept(Visitor v) {
        return v.visit(this);
    }
}
