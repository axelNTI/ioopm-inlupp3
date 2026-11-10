package org.ioopm.calculator.ast;

public class Clear extends Command {
    private static final Clear theInstance = new Clear();
    private Clear() {}
    public static Clear instance() {
        return theInstance;
    }

    public void execute(Environment vars) {
        
        vars.clear();
        System.out.println("Cleared variables");
    }
}
