# babysitter-kata
Simple project to calculate babysitter expense for a night.
  
  Acceptance Criteria:
  * A babysitter
  * starts no earlier than 5:00PM
  * leaves no later than 4:00AM
  * gets paid $12/hour from start-time to bedtime
  * gets paid $8/hour from bedtime to midnight
  * gets paid $16/hour from midnight to end of job
  * gets paid for full hours (no fractional hours)
  
  NOTE: The code in this repo does not meet the criteria to pay for only full hours. There were some questions about how the requirement should be implemented.

## Usage

```java
int year = 2017;
int month = 5;
LocalDateTime startTime = LocalDateTime.of(year, month, 20, 17, 0);
LocalDateTime endTime = LocalDateTime.of(year, month, 21, 1, 0);
LocalDateTime bedTime = LocalDateTime.of(year, month, 20, 22, 0);
BabysitterExpenseCalculator calculator = new BabysitterExpenseCalculator();
int expense = calculator.calculateExpense(startTime, endTime, bedTime);
```

## Development
Want to contribute? Great!

To build the project and run all of the tests using bash, follow these steps:
1. Fork this repository
2. Clone your fork locally
```
git clone https://github.com/{your-github-username}/babysitter-kata.git
```
3. Ensure Gradle Wrapper is Executable (only if clone fails to read correct file mode)
```
chmod +x gradlew
```
3. Execute Gradle Test Task
```
./gradlew clean test
```

## Built with 

- [jUnit4](http://junit.org/junit4/) - JUnit is a simple framework to write repeatable tests. It is an instance of the xUnit architecture for unit testing frameworks.
- [Gradle](https://gradle.org/) - Fancy Java build tool.
