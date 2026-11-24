package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.ioopm.calculator.ast.*;

public class EvaluateTests {

    @Test
    public void testAddition() {
        Environment vars = new Environment();
        EvaluationVisitor visitor = new EvaluationVisitor();

        SymbolicExpression const1 = new Constant(1);
        SymbolicExpression const9 = new Constant(9);

        SymbolicExpression add1 = new Addition(const1, const9);
        double sum1 = visitor.evaluate(add1, vars).getValue();
        assertTrue(sum1 == 10, "Expected: 10, Got: " + sum1);

        SymbolicExpression constNeg = new Negation(const9);

        SymbolicExpression add2 = new Addition(const1, constNeg);
        double sum2 = visitor.evaluate(add2, vars).getValue();
        assertTrue(sum2 == -8, "Expected: -8, Got: " + sum2);
    }

    @Test
    public void testSubtraction() {
        Environment vars = new Environment();
        EvaluationVisitor visitor = new EvaluationVisitor();
        
        SymbolicExpression const1 = new Constant(64);
        SymbolicExpression const2 = new Constant(64);

        SymbolicExpression sub0 = new Subtraction(const1, const2);
        double diff0 = visitor.evaluate(sub0, vars).getValue();
        assertTrue(diff0 == 0, "Expected: 0, Got: " + diff0);

        SymbolicExpression constNeg = new Negation(const1);

        SymbolicExpression sub128 = new Subtraction(const1, constNeg);
        double diff128 = visitor.evaluate(sub128, vars).getValue();
        assertTrue(diff128 == 128, "Expected: 128, Got: " + diff128);
    }

    @Test
    public void testMultiplication() {
        Environment vars = new Environment();
        EvaluationVisitor visitor = new EvaluationVisitor();
    
        SymbolicExpression const0 = new Constant(0);
        SymbolicExpression const3 = new Constant(3);
        SymbolicExpression const7 = new Constant(7);

        SymbolicExpression mult0 = new Multiplication(const0, const7);
        SymbolicExpression mult21 = new Multiplication(const7, const3);
        double prod0 = visitor.evaluate(mult0, vars).getValue();
        double prod21 = visitor.evaluate(mult21, vars).getValue();
        assertTrue(prod0 == 0, "Expected: 0, Got: " + prod0);
        assertTrue(prod21 == 21, "Expected: 21, Got: " + prod21);

        SymbolicExpression constNeg = new Negation(const3);
        SymbolicExpression multNeg = new Multiplication(const7, constNeg);
        double prodNeg = visitor.evaluate(multNeg, vars).getValue();
        assertTrue(prodNeg == -21, "Expected: -21, Got: " + prodNeg);
    }

    @Test
    public void testDivision() {
        Environment vars = new Environment();
        EvaluationVisitor visitor = new EvaluationVisitor();

        SymbolicExpression const0 = new Constant(0);
        SymbolicExpression const9 = new Constant(9);
        SymbolicExpression const81 = new Constant(81);

        SymbolicExpression div0 = new Division(const0, const9);
        SymbolicExpression div9 = new Division(const81, const9);
        double quot0 = visitor.evaluate(div0, vars).getValue();
        double quot9 = visitor.evaluate(div9, vars).getValue();
        assertTrue(quot0 == 0, "Expected: 0, Got: " + quot0);
        assertTrue(quot9 == 9, "Expected: 9, Got: " + quot9);

        SymbolicExpression constNeg = new Negation(const9);
        SymbolicExpression divNeg = new Division(const81, constNeg);
        double quotNeg = visitor.evaluate(divNeg, vars).getValue();
        assertTrue(quotNeg == -9, "Expected: -9, Got: " + quotNeg);
    }

    @Test
    public void testAssignment() {
        Environment vars = new Environment();
        EvaluationVisitor visitor = new EvaluationVisitor();

        SymbolicExpression const5 = new Constant(5.001);
        SymbolicExpression var = new Variable("x");

        SymbolicExpression assign = new Assignment(const5, var);
        
        SymbolicExpression expr = visitor.evaluate(assign, vars);

        double result = expr.getValue();
        assertTrue(result == 5.001, "Expected: 5.001, Got: " + result);
    }

    @Test
    public void testSin() {
        Environment vars = new Environment();
        EvaluationVisitor visitor = new EvaluationVisitor();

        SymbolicExpression constPI = new Constant(Math.PI);
        SymbolicExpression const0 = new Constant(0);
        SymbolicExpression const2 = new Constant(2);

        SymbolicExpression sin0 = new Sin(const0);
        SymbolicExpression sin1 = new Sin(new Division(constPI, const2));
        SymbolicExpression sinNeg = new Sin(new Division(new Negation(constPI), const2));
        double result0 = visitor.evaluate(sin0, vars).getValue();
        double result1 = visitor.evaluate(sin1, vars).getValue();
        double resultNeg = visitor.evaluate(sinNeg, vars).getValue();
        
        assertTrue(result0 == 0, "Expected: 0, Got: " + result0);
        assertTrue(result1 == Math.sin(Math.PI/2), "Expected: 1, Got: " + result1);
        assertTrue(resultNeg == -Math.sin(Math.PI/2), "Expected: -1, Got: " + resultNeg);
    }

    @Test
    public void testCos() {
        Environment vars = new Environment();
        EvaluationVisitor visitor = new EvaluationVisitor();

        SymbolicExpression constPI = new Constant(Math.PI);
        SymbolicExpression const0 = new Constant(0);
        SymbolicExpression const2 = new Constant(2);

        SymbolicExpression cos0 = new Cos(new Division(constPI, const2));
        SymbolicExpression cos1 = new Cos(const0);
        SymbolicExpression cosNeg = new Cos(constPI);
        double result0 = visitor.evaluate(cos0, vars).getValue();
        double result1 = visitor.evaluate(cos1, vars).getValue();
        double resultNeg = visitor.evaluate(cosNeg, vars).getValue();

        assertTrue(result0 == Math.cos(Math.PI/2), "Expected: 0, Got: " + result0);
        assertTrue(result1 == 1, "Expected: 1, Got: " + result1);
        assertTrue(resultNeg == Math.cos(Math.PI), "Expected: -1, Got: " + resultNeg);
    }

    @Test
    public void testExponent() {
        Environment vars = new Environment();
        EvaluationVisitor visitor = new EvaluationVisitor();

        SymbolicExpression const0 = new Constant(0);
        SymbolicExpression const1 = new Constant(1);
        SymbolicExpression const7 = new Constant(7);
        SymbolicExpression ln7 = new Log(const7);

        SymbolicExpression e0 = new Exp(const0);
        SymbolicExpression e1 = new Exp(const1);
        SymbolicExpression e7 = new Exp(ln7);
        double result1 = visitor.evaluate(e0, vars).getValue();
        double resultE = visitor.evaluate(e1, vars).getValue();
        double result7 = visitor.evaluate(e7, vars).getValue();

        assertTrue(result1 == 1, "Expected: 1, Got: " + result1);
        assertTrue(resultE == Math.E, "Expected: " + Math.E + ", Got: " + resultE);
        assertTrue(result7 == Math.exp(Math.log(7)), "Expected: " + Math.exp(Math.log(7)) + ", Got: " + result7);
    }

    @Test
    public void testLog() {
        Environment vars = new Environment();
        EvaluationVisitor visitor = new EvaluationVisitor();
        
        SymbolicExpression const1 = new Constant(1);
        SymbolicExpression constE = new Constant(Math.E);
        SymbolicExpression const0 = new Constant(1/Math.E);

        SymbolicExpression log1 = new Log(const1);
        SymbolicExpression log0 = new Log(const0);
        SymbolicExpression logE = new Log(constE);
        double result0 = visitor.evaluate(log1, vars).getValue();
        double result1 = visitor.evaluate(logE, vars).getValue();
        double resultNeg = visitor.evaluate(log0, vars).getValue();

        assertTrue(result0 == 0, "Expected: 0, Got: " + result0);
        assertTrue(result1 == 1, "Expected: 1, Got: " + result1);
        assertTrue(resultNeg == -1, "Expected: -1, Got: " + resultNeg);
    }

    @Test
    public void testNegation() {
        Environment vars = new Environment();
        EvaluationVisitor visitor = new EvaluationVisitor();

        SymbolicExpression const0 = new Constant(0);
        SymbolicExpression const6 = new Constant(6);
        SymbolicExpression const9 = new Constant(-9);

        SymbolicExpression neg0 = new Negation(const0);
        SymbolicExpression neg6 = new Negation(const6);
        SymbolicExpression neg9 = new Negation(const9);
        double negation0 = visitor.evaluate(neg0, vars).getValue();
        double negation6 = visitor.evaluate(neg6, vars).getValue();
        double negation9 = visitor.evaluate(neg9, vars).getValue();
        
        assertTrue(negation0 == 0, "Expected: 0, Got: " + negation0);
        assertTrue(negation6 == -6, "Expected: -6, Got: " + negation6);
        assertTrue(negation9 == 9, "Expected: 9, Got: " + negation9);
    }

    @Test
    public void testVariable() {
        Environment vars = new Environment();
        EvaluationVisitor visitor = new EvaluationVisitor();

        Variable varX = new Variable("x");
        Variable varY = new Variable("y");
        Variable varZ = new Variable("z");
        SymbolicExpression const2 = new Constant(2);
        SymbolicExpression const6 = new Constant(6);
        SymbolicExpression const89 = new Constant(89);

        vars.put(varX, const2);
        vars.put(varY, const6);
        vars.put(varZ, const89);

        double resultX = visitor.evaluate(varX, vars).getValue();
        double resultY = visitor.evaluate(varY, vars).getValue();
        double resultZ = visitor.evaluate(varZ, vars).getValue();

        assertTrue(resultX == 2, "Expected: 2, Got: " + resultX);
        assertTrue(resultY == 6, "Expected: 6, Got: " + resultY);
        assertTrue(resultZ == 89, "Expected: 89, Got: " + resultZ);
    }

    @Test
    public void testConstant() {
        Environment vars = new Environment();
        EvaluationVisitor visitor = new EvaluationVisitor();

        SymbolicExpression const0 = new Constant(0);
        SymbolicExpression const73 = new Constant(73);
        SymbolicExpression const53 = new Constant(-53);
    
        double result0 = visitor.evaluate(const0, vars).getValue();
        double result73 = visitor.evaluate(const73, vars).getValue();
        double result53 = visitor.evaluate(const53, vars).getValue();

        assertTrue(result0 == 0, "Expected: 0, Got: " + result0);
        assertTrue(result73 == 73, "Expected: 73, Got: " + result73);
        assertTrue(result53 == -53, "Expected: -53, Got: " + result53);
    }

    @Test
    public void testLongExpression() {
        Environment vars = new Environment();
        EvaluationVisitor visitor = new EvaluationVisitor();

        SymbolicExpression const7 = new Constant(7);
        SymbolicExpression sin1 = new Sin((SymbolicExpression) new Constant(Math.PI/2));
        SymbolicExpression sub22 = new Subtraction(new Constant(32), new Constant(10));
        SymbolicExpression add15 = new Addition(new Constant(7), new Constant(8));
        SymbolicExpression mult = new Multiplication(add15, sub22);
        SymbolicExpression div = new Division(mult, sin1);

        SymbolicExpression sub = new Subtraction(div, const7);

        double result = visitor.evaluate(sub, vars).getValue();

        assertTrue(result == 323, "Expected: 323, Got: " + result); 
    }
}
