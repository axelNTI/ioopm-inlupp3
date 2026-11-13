package org.ioopm.calculator.ast;

public interface FunctionInterface {

    static public String getFormat(String name, String[] args) {
        return name + "(%s)".formatted(String.join(", ", args));
    }
    
}
