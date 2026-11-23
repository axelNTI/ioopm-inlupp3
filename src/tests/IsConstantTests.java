package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import org.ioopm.calculator.ast.*;

public class IsConstantTests {

    @Test
    public void testIsConstant() {
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
        SymbolicExpression namedConstant = new NamedConstant("Answer", 42.0);
        SymbolicExpression variable = new Variable("y");
        SymbolicExpression clear = Clear.instance();
        SymbolicExpression quit = Quit.instance();
        SymbolicExpression vars = Vars.instance();

        SymbolicExpression constant = new Constant(5);

        assertFalse(assignment.isConstant());
        assertFalse(addition.isConstant());
        assertFalse(subtraction.isConstant());
        assertFalse(division.isConstant());
        assertFalse(multiplication.isConstant());
        assertFalse(cos.isConstant());
        assertFalse(exp.isConstant());
        assertFalse(log.isConstant());
        assertFalse(sin.isConstant());
        assertFalse(negation.isConstant());
        assertFalse(namedConstant.isConstant());
        assertFalse(variable.isConstant());
        assertFalse(clear.isConstant());
        assertFalse(quit.isConstant());
        assertFalse(vars.isConstant());

        assertTrue(constant.isConstant());
    }
}
