all:
	javac -d classes -sourcepath src src/org/ioopm/calculator/Calculator.java

run:
	java -cp classes org.ioopm.calculator.Calculator

test:
	javac -d classes -cp lib/junit-platform-console-standalone-1.8.1.jar -sourcepath src src/tests/*.java
	java -jar lib/junit-platform-console-standalone-1.8.1.jar --class-path=classes --include-classname=.* --scan-classpath

clean:
	rm -rf classes