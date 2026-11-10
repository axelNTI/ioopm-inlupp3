package org.ioopm.calculator.ast;

public class Vars extends Command {
    private static final Vars theInstance = new Vars();

    private Vars() {
    }

    public static Vars instance() {
        return theInstance;
    }

    public void execute(Environment vars) {
        for (Variable key : vars.keySet()) {
            System.out.println("%s = %s".formatted(key, vars.get(key)));
        }
    }
}
