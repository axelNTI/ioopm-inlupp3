package org.ioopm.calculator.ast;

public class Negation extends Unary implements ExpressionInterface {

    public Negation(SymbolicExpression primary) {
        super(primary);
        this.priority = 100;
    }

    @Override 
    public String getName() {
        return "-";
    }

    @Override
    public SymbolicExpression accept(Visitor v) {
        return v.visit(this);
    }
}
