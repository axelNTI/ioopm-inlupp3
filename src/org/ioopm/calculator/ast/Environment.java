package org.ioopm.calculator.ast;

import java.util.Iterator;
import java.util.HashMap;
import java.util.TreeSet;

public class Environment extends HashMap<Variable, SymbolicExpression> {

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Variables: ");
        TreeSet<Variable> vars = new TreeSet<>(this.keySet());

        for (Iterator<Variable> iter = vars.iterator(); iter.hasNext(); ) {
            Variable v = iter.next();
    
            sb.append(v.toString());
            sb.append(" = ");
            sb.append(this.get(v));

            if (iter.hasNext()) {
                sb.append(", ");
            }
        }

        return sb.toString();
    }
}
