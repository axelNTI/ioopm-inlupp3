package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.ioopm.calculator.ast.*;

public class ReassignmentCheckTests {
    
    @Test
    public void testConditional() {
        EvaluationVisitor visitor = new EvaluationVisitor();
        Assignment x3 = new Assignment(new Constant(3), new Variable("x"));
        Scope scope1 = new Scope(new Addition(new Assignment(new Constant(0), new Variable("x")), x3));

        Conditional cond1 = new Conditional(new Constant(3), new Constant(5), "<", new Scope(x3), new Scope(x3));
        Conditional cond2 = new Conditional(new Constant(3), new Constant(5), "<", scope1, new Scope(x3));

        assertTrue(visitor.checkReassignment(cond1));
        assertFalse(visitor.checkReassignment(cond2));
    }

    // @Test
    // public void testScope() {
    //     EvaluationVisitor visitor = new EvaluationVisitor();

    //     Assignment assign1 = new Assignment()
    //     Addition add = new Addition();

    //     Scope scope1 = 
    // }
}
