package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import org.ioopm.calculator.ast.*;

public class GetNameTests {

    @Test
    public void testGetName() {
        SymbolicExpression assignment = new Assignment(new Constant(3), new Variable("x"));
        SymbolicExpression addition = new Addition(new Constant(3), new Constant(3));
        SymbolicExpression subtraction = new Subtraction(new Constant(7), new Constant(4));
        SymbolicExpression division = new Division(new Constant(3), new Constant(9));
        SymbolicExpression multiplication = new Multiplication(new Constant(5), new Constant(4));
        SymbolicExpression cos = new Cos(new Constant(2));
        SymbolicExpression exp = new Exp(new Constant(3));
        SymbolicExpression log = new Log(new Constant(1));
        SymbolicExpression sin = new Sin(new Constant(8));
        SymbolicExpression negation = new Negation(new Constant(3));

        assertEquals(assignment.getName(), "=");
        assertEquals(addition.getName(), "+");
        assertEquals(subtraction.getName(), "-");
        assertEquals(division.getName(), "/");
        assertEquals(multiplication.getName(), "*");
        assertEquals(cos.getName(), "Cos");
        assertEquals(exp.getName(), "Exp");
        assertEquals(log.getName(), "Log");
        assertEquals(sin.getName(), "Sin");
        assertEquals(negation.getName(), "-");
    }
}
