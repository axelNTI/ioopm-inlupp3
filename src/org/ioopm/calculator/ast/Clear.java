package org.ioopm.calculator.ast;

import org.ioopm.calculator.Statistics;
import java.util.Scanner;
public class Clear extends Command {
    private static final Clear theInstance = new Clear();
    private Clear() {}
    public static Clear instance() {
        return theInstance;
    }

    @Override 
    public void execute(Environment vars, Statistics stats, Scanner sc) {
        
        vars.clear();
        System.out.println("Cleared variables");
    }

    @Override 
    public SymbolicExpression accept(Visitor v) {
        return v.visit(this);
    }
}
