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
    private static final int YEAR = 2000;
    private static final int MONTH = 5;
    private static final int UNTIL_BEDTIME_RATE = 12;
    private static final int BEDTIME_UNTIL_MIDNIGHT_RATE = 8;

    private BabysitterExpenseCalculator calculator;

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        calculator = new BabysitterExpenseCalculator();
    }

    @Test
    public void calculateShouldGiveZeroWhenLessThanAnHourOfWork() throws Exception {
        LocalDateTime startTime = LocalDateTime.of(YEAR, MONTH, 20, 17, 0);
        LocalDateTime endTime = LocalDateTime.of(YEAR, MONTH, 20, 17, 59);
        LocalDateTime bedTime = endTime;

        int expense = calculator.calculateExpense(startTime, endTime, bedTime);

        assertEquals(0, expense);
    }

    @Test
    public void shouldThrowExceptionWhenStartTimeOutsideOf5pmTo4Am() throws Exception {
        LocalDateTime startTime = LocalDateTime.of(YEAR, MONTH,20,16,0);
        LocalDateTime endTime = LocalDateTime.of(YEAR, MONTH,20,18,0);
        LocalDateTime bedTime = endTime;

        exception.expect(IllegalArgumentException.class);
        calculator.calculateExpense(startTime, endTime, bedTime);
    }

    @Test
    public void shouldThrowExceptionWhenEndTimeOutsideOf5pmTo4Am() throws Exception {
        LocalDateTime startTime = LocalDateTime.of(YEAR, MONTH,20,18,0);
        LocalDateTime endTime = LocalDateTime.of(YEAR, MONTH,20,4,1);
        LocalDateTime bedTime = endTime;

        exception.expect(IllegalArgumentException.class);
        calculator.calculateExpense(startTime, endTime, bedTime);
    }

    @Test
    public void shouldThrowExceptionWhenEndTimeIsBeforeStartTime() throws Exception {
        LocalDateTime startTime = LocalDateTime.of(YEAR, MONTH,20,19,0);
        LocalDateTime endTime = LocalDateTime.of(YEAR, MONTH,20,18,0);
        LocalDateTime bedTime = endTime;

        exception.expect(IllegalArgumentException.class);
        calculator.calculateExpense(startTime, endTime, bedTime);
    }

    @Test
    public void shouldThrowExceptionWhenTimeWorkedLongerThanElevenHours() throws Exception {
        LocalDateTime startTime = LocalDateTime.of(YEAR, MONTH,20,17,0);
        LocalDateTime endTime = LocalDateTime.of(YEAR, MONTH,21,18,0);
        LocalDateTime bedTime = endTime;

        exception.expect(IllegalArgumentException.class);
        calculator.calculateExpense(startTime, endTime, bedTime);
    }

    @Test
    public void shouldMakeTwelveDollarsAnHourFromStartUntilBedtime() {
        int startingHour = 17;
        int endingHour = 0;
        LocalDateTime startTime = LocalDateTime.of(YEAR, MONTH, 20, startingHour, 0);
        LocalDateTime bedTime = LocalDateTime.of(YEAR, MONTH, 21, endingHour, 0);
        LocalDateTime endTime = bedTime;

        int expense = calculator.calculateExpense(startTime, endTime, bedTime);

        int expectedExpense = (24 - startingHour) * UNTIL_BEDTIME_RATE;
        assertEquals(expectedExpense, expense);
    }

    @Test
    public void shouldMake8DollarsAnHourFromBedtimeUntilMidnightWhenBedtimeIsBeforeMidnight() {
        int startingHour = 17;
        int midnightHour = 0;
        LocalDateTime startTime = LocalDateTime.of(YEAR, MONTH, 20, startingHour, 0);
        LocalDateTime bedTime = startTime;
        LocalDateTime endTime = LocalDateTime.of(YEAR, MONTH, 21, midnightHour, 0);

        int expense = calculator.calculateExpense(startTime, endTime, bedTime);

        int expectedExpense =  (24 - startingHour) * BEDTIME_UNTIL_MIDNIGHT_RATE;
        assertEquals(expectedExpense, expense);
    }

    @Test
    public void shouldMake16DollarsAnHourFromMidnightUntilEndTimeWhenEndTimeisAfterMidnight() {
        int midnightHour = 0;
        int endingHour = 3;
        LocalDateTime startTime = LocalDateTime.of(YEAR, MONTH, 20, midnightHour, 0);
        LocalDateTime bedTime = startTime;
        LocalDateTime endTime = LocalDateTime.of(YEAR, MONTH, 20, endingHour, 0);

        int expense = calculator.calculateExpense(startTime, endTime, bedTime);

        int expectedExpense =  (endingHour - midnightHour) * 16;
        assertEquals(expectedExpense, expense);
    }
}
