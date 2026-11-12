package tests;

public class TestSuite {

    public static void main(String[] args) {
        System.out.println("Starting tests");
        EvalTests.testAddition();
        EvalTests.testSubtraction();
        EvalTests.testMultiplication();
        EvalTests.testDivision();
        EvalTests.testAssignment();
        EvalTests.testSin();
        EvalTests.testCos();
        EvalTests.testExponent();
        EvalTests.testLog();
        EvalTests.testNegation();
        EvalTests.testVariable();
        EvalTests.testConstant();
        EvalTests.testLongExpression();
        System.out.println("Tests done");
    }
}
