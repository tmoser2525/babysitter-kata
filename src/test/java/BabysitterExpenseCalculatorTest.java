import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

/**
 * Created by tmoser on 5/17/17.
 */
public class BabysitterExpenseCalculatorTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    private BabysitterExpenseCalculator calculator;

    @Before
    public void setUp() throws Exception {
        calculator = new BabysitterExpenseCalculator();
    }

    @Test
    public void calculateShouldGiveZeroWhenLessThanAnHourOfWork() throws Exception {
        LocalDateTime startTime = LocalDateTime.of(2000, 5, 20, 17, 0);
        LocalDateTime endTime = LocalDateTime.of(2000, 5, 20, 17, 59);
        LocalDateTime bedTime = endTime;

        int expense = calculator.calculateExpense(startTime, endTime, bedTime);

        assertEquals(0, expense);
    }

    @Test
    public void shouldThrowExceptionWhenStartTimeOutsideOf5pmTo4Am() throws Exception {
        LocalDateTime startTime = LocalDateTime.of(2000,5,20,16,0);
        LocalDateTime endTime = LocalDateTime.of(2000,5,20,18,0);
        LocalDateTime bedTime = endTime;

        exception.expect(IllegalArgumentException.class);
        calculator.calculateExpense(startTime, endTime, bedTime);
    }

    @Test
    public void shouldThrowExceptionWhenEndTimeOutsideOf5pmTo4Am() throws Exception {
        LocalDateTime startTime = LocalDateTime.of(2000,5,20,18,0);
        LocalDateTime endTime = LocalDateTime.of(2000,5,20,4,1);
        LocalDateTime bedTime = endTime;

        exception.expect(IllegalArgumentException.class);
        calculator.calculateExpense(startTime, endTime, bedTime);
    }

    @Test
    public void shouldThrowExceptionWhenEndTimeIsBeforeStartTime() throws Exception {
        LocalDateTime startTime = LocalDateTime.of(2000,5,20,19,0);
        LocalDateTime endTime = LocalDateTime.of(2000,5,20,18,0);
        LocalDateTime bedTime = endTime;

        exception.expect(IllegalArgumentException.class);
        calculator.calculateExpense(startTime, endTime, bedTime);
    }

    @Test
    public void shouldThrowExceptionWhenTimeWorkedLongerThanElevenHours() throws Exception {
        LocalDateTime startTime = LocalDateTime.of(2000,5,20,17,0);
        LocalDateTime endTime = LocalDateTime.of(2000,5,21,18,0);
        LocalDateTime bedTime = endTime;

        exception.expect(IllegalArgumentException.class);
        calculator.calculateExpense(startTime, endTime, bedTime);
    }

    @Test
    public void shouldMakeTwelveDollarsAnHourFromStartUntilBedtime() {
        int startingHour = 17;
        int endingHour = 23;
        LocalDateTime startTime = LocalDateTime.of(2000, 5, 20, startingHour, 0);
        LocalDateTime bedTime = LocalDateTime.of(2000, 5, 20, endingHour, 0);
        LocalDateTime endTime = bedTime;

        int expense = calculator.calculateExpense(startTime, endTime, bedTime);

        int untilBedtimeRate = 12;
        int expectedExpense = (endingHour - startingHour) * untilBedtimeRate;
        assertEquals(expectedExpense, expense);
    }
}
