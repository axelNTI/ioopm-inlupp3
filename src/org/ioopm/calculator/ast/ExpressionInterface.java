package org.ioopm.calculator.ast;

public interface ExpressionInterface {

    static public String getFormat(SymbolicExpression current, SymbolicExpression[] args) {
        if (args.length == 1) {
            if (args[0] instanceof Binary) {
                return current.getName() + "( %s )".formatted(args[0]);
            }
            return current.getName() + "%s".formatted(args[0]);
        }
        
        String lhsStr = "";
        String rhsStr = "";
        if (args[0].priority < current.priority) {
            lhsStr = "( " + args[0] + " )";
        } else {
            lhsStr = args[0].toString();
        }

        if (args[1].priority < current.priority) {
            rhsStr = "( " + args[1] + " )";
        } else {
            rhsStr = args[1].toString();
        }

        return lhsStr + " " + current.getName() + " " + rhsStr;
    }
} 
