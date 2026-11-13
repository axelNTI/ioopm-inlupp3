package org.ioopm.calculator.ast;

public class Log extends Unary implements FunctionInterface {

    public Log(SymbolicExpression primary) {
        super(primary);
        this.priority = 90;
    }

    public String getName() {
        return "Log";
    }

    public SymbolicExpression eval(Environment vars) {
        SymbolicExpression arg = this.primary.eval(vars);

        if (!arg.isConstant()) {
            return new Log(arg);
        }

        if (arg.getValue() <= 0) {
            throw new InvalidInputException("Can not log <= 0");
        }

        return new Constant(Math.log(arg.getValue()));
    }
}
