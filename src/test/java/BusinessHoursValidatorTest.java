import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.time.LocalDateTime;

/**
 * Created by tmoser on 5/18/17.
 */
public class BusinessHoursValidatorTest {
    private static final int YEAR = 2000;
    private static final int MONTH = 5;

    private BusinessHoursValidator validator;

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        validator = new BusinessHoursValidator();
    }

    @Test
    public void shouldThrowExceptionWhenStartTimeOutsideOf5pmTo4Am() throws Exception {
        LocalDateTime startTime = LocalDateTime.of(YEAR, MONTH,20, 16,0);
        LocalDateTime endTime = LocalDateTime.of(YEAR, MONTH,20,18,0);

        exception.expect(IllegalArgumentException.class);
        validator.validateTimes(startTime, endTime);
    }

    @Test
    public void shouldThrowExceptionWhenEndTimeOutsideOf5pmTo4Am() throws Exception {
        LocalDateTime startTime = LocalDateTime.of(YEAR, MONTH,20,18,0);
        LocalDateTime endTime = LocalDateTime.of(YEAR, MONTH,20,4,1);

        exception.expect(IllegalArgumentException.class);
        validator.validateTimes(startTime, endTime);
    }
}