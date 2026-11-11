all:
	javac -d classes -sourcepath src src/org/ioopm/calculator/Calculator.java

run:
	java -cp classes org.ioopm.calculator.Calculator

test:
	javac -d classes -sourcepath tests tests/org/ioopm/calculator/TestSuite.java
	java -ea -cp classes org.ioopm.calculator.TestSuite

clean:
	rm -rf classes