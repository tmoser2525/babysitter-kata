# babysitter-kata
<table>
<tr>
<td>
  Simple project to calculate babysitter expense for a night.
</td>
</tr>
</table>

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

To build the project and run all of the tests, follow these steps:
1. Fork this repository
2. Clone your fork locally
```
git clone https://github.com/{your-github-username}/babysitter-kata.git
```
3. Execute Gradle Test Task
```
./gradlew clean test
```

## Built with 

- [jUnit4](http://junit.org/junit4/) - JUnit is a simple framework to write repeatable tests. It is an instance of the xUnit architecture for unit testing frameworks.
- [Gradle](https://gradle.org/) - Fancy Java build tool.
