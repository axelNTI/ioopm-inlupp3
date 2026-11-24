package org.ioopm.calculator.ast;

import java.util.Iterator;
import java.util.LinkedList;

public class EnvironmentStack extends Environment {
    private LinkedList<Environment> stack = new LinkedList<Environment>();

    public EnvironmentStack(Environment root) {
        stack.push(root);
    }

    @Override
    public SymbolicExpression put(Variable var, SymbolicExpression expr) {
        return stack.peek().put(var, expr);
    }

    @Override
    public SymbolicExpression get(Object o) {
        
        for (Iterator<Environment> iter = stack.iterator(); iter.hasNext(); ) {
            Environment v = iter.next();
    
            if (v.get(o) != null) {
                return v.get(o);
            }
        }
        return null;
    }

    public void pushEnvironment() {
        stack.push(new Environment());
    }

    public void popEnvironment() {
        stack.pop();
    }
}
