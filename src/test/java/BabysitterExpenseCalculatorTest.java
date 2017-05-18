import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

/**
 * Created by tmoser on 5/17/17.
 */
public class BabysitterExpenseCalculatorTest {

    @Test
    public void calculateShouldGiveZeroWhenLessThanAnHourOfWork() {
        BabysitterExpenseCalculator calculator = new BabysitterExpenseCalculator();

        LocalDateTime startTime = LocalDateTime.of(2000, 5, 20, 17, 0);
        LocalDateTime endTime = LocalDateTime.of(2000, 5, 20, 17, 59);

        int expense = calculator.calculateExpense(startTime, endTime);

        assertEquals(0, expense);
    }
}
