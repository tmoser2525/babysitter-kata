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
    public static final int MIDNIGHT_TO_END_TIME_RATE = 16;

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
    public void shouldMakeTwelveDollarsAnHourFromStartUntilBedtime() {
        int startingHour = 17;
        int hoursUntilBedtime = 7;
        LocalDateTime startTime = LocalDateTime.of(YEAR, MONTH, 20, startingHour, 0);
        LocalDateTime bedTime = startTime.plusHours(hoursUntilBedtime);
        LocalDateTime endTime = bedTime;

        int expense = calculator.calculateExpense(startTime, endTime, bedTime);

        int expectedExpense = hoursUntilBedtime * UNTIL_BEDTIME_RATE;
        assertEquals(expectedExpense, expense);
    }

    @Test
    public void shouldMake8DollarsAnHourFromBedtimeUntilMidnightWhenBedtimeIsBeforeMidnight() {
        int startingHour = 17;
        int hoursUntilMidnight = 7;
        LocalDateTime startTime = LocalDateTime.of(YEAR, MONTH, 20, startingHour, 0);
        LocalDateTime bedTime = startTime;
        LocalDateTime endTime = bedTime.plusHours(hoursUntilMidnight);

        int expense = calculator.calculateExpense(startTime, endTime, bedTime);

        int expectedExpense =  hoursUntilMidnight * BEDTIME_UNTIL_MIDNIGHT_RATE;
        assertEquals(expectedExpense, expense);
    }

    @Test
    public void shouldMake16DollarsAnHourFromMidnightUntilEndTimeWhenEndTimeisAfterMidnight() {
        int midnightHour = 0;
        LocalDateTime startTime = LocalDateTime.of(YEAR, MONTH, 20, midnightHour, 0);
        LocalDateTime bedTime = startTime;
        int hoursPastMidnight = 3;
        LocalDateTime endTime = startTime.plusHours(hoursPastMidnight);

        int expense = calculator.calculateExpense(startTime, endTime, bedTime);

        int expectedExpense =  hoursPastMidnight * MIDNIGHT_TO_END_TIME_RATE;
        assertEquals(expectedExpense, expense);
    }

    @Test
    public void shouldCalculateExpenseWhenBedtimeIsAfterMidnight() {
        int startingHour = 18;
        LocalDateTime startTime = LocalDateTime.of(YEAR, MONTH, 20, startingHour, 0);
        int startToBedtimeHours = 7;
        LocalDateTime bedTime = startTime.plusHours(startToBedtimeHours);
        int bedtimeToEndHours = 2;
        LocalDateTime endTime = bedTime.plusHours(bedtimeToEndHours);

        int expense = calculator.calculateExpense(startTime, endTime, bedTime);

        int expectedExpense =  startToBedtimeHours * UNTIL_BEDTIME_RATE + bedtimeToEndHours * MIDNIGHT_TO_END_TIME_RATE;
        assertEquals(expectedExpense, expense);
    }
}
