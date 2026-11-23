all:
	javac -d classes -sourcepath src src/org/ioopm/calculator/Calculator.java

run:
	java -cp classes org.ioopm.calculator.Calculator

test:
	javac -d classes -sourcepath lib/junit-platform-console-standalone-1.8.1.jar:src src/tests/TestSuite.java
	java -enableassertions -jar lib/junit-platform-console-standalone-1.8.1.jar -cp classes -c tests/TestSuite

clean:
	rm -rf classes