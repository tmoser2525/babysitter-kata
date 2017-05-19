import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.time.LocalDateTime;

/**
 * Created by tmoser on 5/18/17.
 */
public class TimesInOrderValidatorTest {
    private static final int YEAR = 2000;
    private static final int MONTH = 5;

    private TimesInOrderValidator validator;

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        validator = new TimesInOrderValidator();
    }

    @Test
    public void shouldThrowExceptionWhenEndTimeIsBeforeStartTime() throws Exception {
        LocalDateTime startTime = LocalDateTime.of(YEAR, MONTH,20,19,0);
        LocalDateTime endTime = LocalDateTime.of(YEAR, MONTH,20,18,0);

        exception.expect(IllegalArgumentException.class);
        validator.validateTimes(startTime, endTime);
    }
}