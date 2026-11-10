package org.ioopm.calculator.ast;

public class Log extends Unary {

    public Log(SymbolicExpression primary) {
        super(primary);
        this.priority = 90;
    }

    public String getName() {
        return "log";
    }

    public String toString() {
        return "log(%s)".formatted(primary);
    }

    public SymbolicExpression eval() {
        SymbolicExpression arg = this.primary.eval();

        if (!arg.isConstant()) {
            return new Log(arg);
        }

        if (arg.getValue() <= 0) {
            throw new InvalidInputException("Can not log <= 0");
        }

        return new Constant(Math.log(arg.getValue()));
    }
}
