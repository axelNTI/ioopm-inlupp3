package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import org.ioopm.calculator.ast.*;

public class PriorityTests {

    @Test
    public void testPriority() {
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

        SymbolicExpression constant = new Constant(5);
        SymbolicExpression namedConstant = new NamedConstant("Answer", 42.0);
        SymbolicExpression variable = new Variable("y");

        assertTrue(assignment.getPriority() < addition.getPriority());

        assertTrue(addition.getPriority() == subtraction.getPriority());

        assertTrue(subtraction.getPriority() < division.getPriority());

        assertTrue(division.getPriority() == multiplication.getPriority());

        assertTrue(multiplication.getPriority() < cos.getPriority());

        assertTrue(cos.getPriority() == exp.getPriority());

        assertTrue(exp.getPriority() == log.getPriority());

        assertTrue(log.getPriority() == sin.getPriority());

        assertTrue(log.getPriority() < negation.getPriority());

        assertTrue(negation.getPriority() < constant.getPriority());

        assertTrue(constant.getPriority() == namedConstant.getPriority());

        assertTrue(namedConstant.getPriority() == variable.getPriority());

    }

}
