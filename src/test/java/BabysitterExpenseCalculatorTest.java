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
//        exception = ExpectedException.none();
        calculator = new BabysitterExpenseCalculator();
    }

    @Test
    public void calculateShouldGiveZeroWhenLessThanAnHourOfWork() throws Exception {
        LocalDateTime startTime = LocalDateTime.of(2000, 5, 20, 17, 0);
        LocalDateTime endTime = LocalDateTime.of(2000, 5, 20, 17, 59);

        int expense = calculator.calculateExpense(startTime, endTime);

        assertEquals(0, expense);
    }

    @Test
    public void shouldThrowExceptionWhenStartTimeOutsideOf5pmTo4Am() throws Exception {
        LocalDateTime startTime = LocalDateTime.of(2000,5,20,16,0);
        LocalDateTime endTime = LocalDateTime.of(2000,5,20,18,0);

        exception.expect(IllegalArgumentException.class);
        calculator.calculateExpense(startTime, endTime);
    }

    @Test
    public void shouldThrowExceptionWhenEndTimeOutsideOf5pmTo4Am() throws Exception {
        LocalDateTime startTime = LocalDateTime.of(2000,5,20,18,0);
        LocalDateTime endTime = LocalDateTime.of(2000,5,20,4,1);

        exception.expect(IllegalArgumentException.class);
        calculator.calculateExpense(startTime, endTime);
    }
}
