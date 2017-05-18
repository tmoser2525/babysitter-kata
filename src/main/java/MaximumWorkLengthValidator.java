import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Created by tmoser on 5/18/17.
 */
class MaximumWorkLengthValidator implements BabysittingExpenseCalculatorValidator {

    private static final int MAXIMUM_WORK_MINUTES = 660;

    @Override
    public void validateTimes(LocalDateTime startTime, LocalDateTime endTime) {
        if (startTime.until(endTime, ChronoUnit.MINUTES) > MAXIMUM_WORK_MINUTES) {
            throw new IllegalArgumentException("Time range must be eleven hours or less");
        }
    }
}
