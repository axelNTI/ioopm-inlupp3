all:
	javac -d classes -sourcepath src src/org/ioopm/calculator/Calculator.java

run:
	java -cp classes org.ioopm.calculator.Calculator

test:
	javac -d classes -cp lib/junit-platform-console-standalone-1.8.1.jar -sourcepath src src/tests/*.java
	java -jar lib/junit-platform-console-standalone-1.8.1.jar --class-path=classes --include-classname=.* --scan-classpath
	make all
	java -cp classes org.ioopm.calculator.Calculator < src/tests/input.txt > classes/output.txt
	diff classes/output.txt src/tests/expected.txt 

clean:
	rm -rf classes