package org.ioopm.calculator.ast;

import org.ioopm.calculator.Statistics;
import java.util.Scanner;
public class Vars extends Command {
    private static final Vars theInstance = new Vars();

    private Vars() {
    }

    public static Vars instance() {
        return theInstance;
    }

    @Override 
    public void execute(Environment vars, Statistics stats, Scanner sc) {
        System.out.println(vars);
    }

    @Override 
    public SymbolicExpression accept(Visitor v) {
        return v.visit(this);
    }
}
