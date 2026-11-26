package org.ioopm.calculator;

import org.ioopm.calculator.parser.*;
import org.ioopm.calculator.ast.*;
import java.util.Scanner;

public class Calculator {
    private static final CalculatorParser parser = new CalculatorParser();
    private static final Environment vars = new Environment();
    private static final Statistics stats = new Statistics();
    private static final EvaluationVisitor visitor = new EvaluationVisitor();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {

            try {
                System.out.print("? ");
                String input = sc.nextLine();
                SymbolicExpression expr = parser.parse(input, vars);

                if (expr.isCommand()) {
                    expr.execute(vars, stats, sc);
                    continue;
                }

                stats.addNoExpressions();

                if (!visitor.check(expr) || !visitor.checkReassignment(expr)) {
                    continue;
                }

                SymbolicExpression evaluated = visitor.evaluate(expr, vars);
                vars.put(new Variable("ans"), evaluated);
                System.out.println(evaluated);

                if (evaluated.isConstant()) {
                    stats.addNoFully();
                }
                stats.addNoSuccessful();
            } catch (Exception e) {
                System.err.println(e);
            }

        }
    }
}
