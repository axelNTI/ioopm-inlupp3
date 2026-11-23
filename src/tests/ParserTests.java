package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.ioopm.calculator.ast.*;
import org.ioopm.calculator.parser.*;

public class ParserTests {

    @Test
    public void testAtomExpression() {
        try {
            CalculatorParser parser = new CalculatorParser();
            Environment vars = new Environment();
            String input;
            SymbolicExpression output;

            input = "3";

            output = parser.parse(input, vars);

            assertTrue(output instanceof Constant, "Output not constant");

            assertTrue(output.getValue() == 3, "Incorrect value");

            input = "x";

            output = parser.parse(input, vars);

            assertTrue(output instanceof Variable, "Output not variable");

            assertTrue(output.toString().compareTo(input) == 0, "Variable identifier incorrect");

            input = "pi";

            output = parser.parse(input, vars);

            assertTrue(output instanceof NamedConstant, "Output not Named Constant");

            assertTrue(output.toString().compareTo(input) == 0, "Named constant name incorrect");
        } catch (Exception e) {
            assert false : e;
        }
    }

    @Test
    public void testCommandExpression() {
        try {
            CalculatorParser parser = new CalculatorParser();
            Environment vars = new Environment();
            String input;
            SymbolicExpression output;

            input = "Quit";

            output = parser.parse(input, vars);

            assertTrue(output.isCommand(), "Output not command");

            assertTrue(output instanceof Quit, "Output not quit command");

            input = "Vars";

            output = parser.parse(input, vars);

            assertTrue(output.isCommand(), "Output not command");

            assertTrue(output instanceof Vars, "Output not vars command");

            input = "Clear";

            output = parser.parse(input, vars);

            assertTrue(output.isCommand(), "Output not command");

            assertTrue(output instanceof Clear, "Output not clear command");
        } catch (Exception e) {
            assert false : e;
        }
    }

    @Test
    public void testUnaryExpression() {
        try {
            CalculatorParser parser = new CalculatorParser();
            Environment vars = new Environment();
            String input;
            SymbolicExpression output;

            input = "Sin(3)";

            output = parser.parse(input, vars);

            assertTrue(output instanceof Sin, "Output not Sin");

            input = "Cos(3)";

            output = parser.parse(input, vars);

            assertTrue(output instanceof Cos, "Output not Cos");

            input = "Log(3)";

            output = parser.parse(input, vars);

            assertTrue(output instanceof Log, "Output not Log");

            input = "Exp(3)";

            output = parser.parse(input, vars);

            assertTrue(output instanceof Exp, "Output not Exp");

            input = "Neg(3)";

            output = parser.parse(input, vars);

            assertTrue(output instanceof Negation, "Output not Negation");

            input = "-3";

            output = parser.parse(input, vars);

            assertTrue(output instanceof Negation, "Output not Negation");
        } catch (Exception e) {
            assert false : e;
        }
    }

    @Test
    public void testBinaryExpression() {
        try {
            CalculatorParser parser = new CalculatorParser();
            Environment vars = new Environment();
            String input;
            SymbolicExpression output;

            input = "2 + 3";

            output = parser.parse(input, vars);

            assertTrue(output instanceof Addition, "Output not Addition");

            input = "3 - 2";

            output = parser.parse(input, vars);

            assertTrue(output instanceof Subtraction, "Output not Subtraction");

            input = "3 * 4";

            output = parser.parse(input, vars);

            assertTrue(output instanceof Multiplication, "Output not Multiplication");

            input = "7 / 4";

            output = parser.parse(input, vars);

            assertTrue(output instanceof Division, "Output not Division");

            input = "3 = x";

            output = parser.parse(input, vars);

            assertTrue(output instanceof Assignment, "Output not Assignment");
        } catch (Exception e) {
            assert false : e;
        }
    }
}
