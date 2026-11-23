package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import org.ioopm.calculator.ast.*;

public class GetValueTests {

    @Test
    public void testGetValue() {
        SymbolicExpression const1 = new Constant(1);
        SymbolicExpression const2 = new Constant(5);
        SymbolicExpression const3 = new Constant(1000);
        SymbolicExpression const4 = new Constant(1.523E5);

        assertEquals(const1.getValue(), 1);
        assertEquals(const2.getValue(), 5);
        assertEquals(const3.getValue(), 1000);
        assertEquals(const4.getValue(), 152300);
    }
}
