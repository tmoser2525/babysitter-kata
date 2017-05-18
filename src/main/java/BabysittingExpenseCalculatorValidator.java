import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Created by tmoser on 5/18/17.
 */
interface BabysittingExpenseCalculatorValidator {
    LocalTime START_TIME_CUTOFF = LocalTime.of(17, 0);
    LocalTime END_TIME_CUTOFF = LocalTime.of(4, 0);

    void validateTimes(LocalDateTime startTime, LocalDateTime endTime);
}
