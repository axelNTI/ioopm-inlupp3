package org.ioopm.calculator;

import org.ioopm.calculator.parser.*;
import org.ioopm.calculator.ast.*;

public class Calculator {
    private static final CalculatorParser parser = new CalculatorParser();
    private static final Environment vars = new Environment();

    public static void main(String[] args) {


        while(true) {
            
            try {
                System.out.print("Please enter an expression: ");
                String input = System.console().readLine();
                SymbolicExpression expr = parser.parse(input, vars);

                if (expr.isCommand()) {
                    expr.execute(vars);
                    continue;
                }
                SymbolicExpression evaluated = expr.eval(vars);
                vars.put(new Variable("ans"), evaluated);
                System.out.println(evaluated);

            }
            catch (Exception e) {
                System.err.println(e);
            }
            
        }
    }
}
